package brandonjf.com.searchsy.presentation.search

import android.arch.lifecycle.ViewModel
import brandonjf.com.searchsy.data.remote.EtsyApiDataSource
import brandonjf.com.searchsy.data.repository.ListingRepository
import brandonjf.com.searchsy.presentation.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by Brandon on 3/11/18.
 */
class SearchViewModel: ViewModel(), BaseViewModel<SearchIntent, SearchUiModel> {
    override fun processIntents(intents: Observable<SearchIntent>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun states(): Observable<SearchUiModel> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun testNetwork() {
        Timber.d("TESTING NETWORK")
        ListingRepository(EtsyApiDataSource()).getListings("longboard")
                .subscribeOn(Schedulers.io())
                .subscribe({
                    Timber.d("SUCCESS: getListings with: ${it.map { it.title }}")
                },{
                    Timber.e("FAILURE: getListings because: $it")
                })
    }
}