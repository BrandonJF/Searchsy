package brandonjf.com.searchsy.presentation.search

import android.arch.lifecycle.ViewModel
import brandonjf.com.searchsy.base.BaseViewModel
import brandonjf.com.searchsy.domain.ListingsUseCases
import brandonjf.com.searchsy.domain.SearchResult
import brandonjf.com.searchsy.domain.SearchViewState
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

/**
 * Created by Brandon on 3/11/18.
 */
class SearchViewModel(listingsUseCases: ListingsUseCases) : ViewModel(), BaseViewModel<SearchIntent, SearchViewState> {

    private val publisherForInternalEventHandling: PublishSubject<SearchIntent> = PublishSubject.create()
    private val compositeDisposable = CompositeDisposable()
    private val listingsActionProcessorManager: ListingsActionProcessorManager = ListingsActionProcessorManager(listingsUseCases)

    override fun processIntents(intents: Observable<SearchIntent>) {
        val disposable = intents
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { Timber.d("Processing Intent: ${it::class.java.simpleName}") }
                .subscribe({
                    publisherForInternalEventHandling.onNext(it)
                }, {}, {})
                .addTo(compositeDisposable)
    }


    override fun states(): Observable<out SearchViewState> {
        return composeStateObservable()
                .doOnNext { Timber.d("Received new state: $it") }
    }

    private fun composeStateObservable(): Observable<out SearchViewState> {
        return publisherForInternalEventHandling
                .map(this::convertIntentToAction)
                .compose(listingsActionProcessorManager.actionProcessor)
                .distinctUntilChanged()
                .scan(SearchViewState.Idle, reducer)
    }

    private fun convertIntentToAction(intent: SearchIntent): SearchAction {
        return when (intent) {
            is SearchIntent.InitialIntent -> SearchAction.LoadListings("longboard")
            is SearchIntent.LoadListingsIntent -> SearchAction.LoadListings(intent.searchTerm)
            is SearchIntent.RefreshListingsIntent -> SearchAction.LoadListings("refreshing") //todo - make an actual action for this
        }
    }

    private val reducer = BiFunction({previousState: SearchViewState, result: SearchResult ->
        when (result) {
            is SearchResult.LoadListingsResult -> when (result) {
                is SearchResult.LoadListingsResult.Success -> {
                    previousState.copy(
                            inProgress = false,
                            listings = result.listings
                    )
                }
                is SearchResult.LoadListingsResult.Failure -> {
                    previousState.copy(
                            inProgress = false,
                            isErrorState = true
                    )
                }
                is SearchResult.LoadListingsResult.InProgress -> {
                    previousState.copy(inProgress = true)
                }
                is SearchResult.LoadListingsResult.Empty -> {
                    SearchViewState.Idle
                }
            }
        }
    })

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}