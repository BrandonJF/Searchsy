package brandonjf.com.searchsy.presentation.search.view


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import brandonjf.com.searchsy.R
import brandonjf.com.searchsy.base.BaseFragment
import brandonjf.com.searchsy.base.BaseView
import brandonjf.com.searchsy.data.remote.EtsyRemoteDataSource
import brandonjf.com.searchsy.domain.ListingsInteractor
import brandonjf.com.searchsy.domain.SearchViewState
import brandonjf.com.searchsy.presentation.search.SearchIntent
import brandonjf.com.searchsy.presentation.search.SearchViewModel
import brandonjf.com.searchsy.ui.SearchViewModelFactory
import brandonjf.com.searchsy.ui.adapter.ListingAdapter
import brandonjf.com.searchsy.ui.applySchedulers
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.layout_fragment_search.*
import timber.log.Timber


/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : BaseFragment(), BaseView<SearchIntent, SearchViewState> {


    private val loadListingsIntentPublisher = BehaviorSubject.create<SearchIntent.LoadListingsIntent>()
    private val listingAdapter = ListingAdapter()
    private val viewModel: SearchViewModel by lazy(LazyThreadSafetyMode.NONE) {
        val repository = EtsyRemoteDataSource()
        ViewModelProviders
                .of(this, SearchViewModelFactory(ListingsInteractor(repository)))
                .get(SearchViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.layout_fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListingsRecycler()
        setupMVIObservables()
    }

    fun setupListingsRecycler() {
        rv_listings.adapter = listingAdapter
        rv_listings.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
//        rv_listings.layoutManager = GridLayoutManager(this.context, 2, GridLayoutManager.VERTICAL, false)
    }

    override fun onDestroyView() {
        compositeDisposable.clear()
        super.onDestroyView()
    }

    private fun setupMVIObservables() {
        viewModel.states()
                .compose(applySchedulers())
                .subscribe(this::render)
                .addTo(compositeDisposable)
                 //render view when state changes
        viewModel.processIntents(intents()) //hand the viewmodel an observable stream of user intents.
    }

    override fun onStart() {
        super.onStart()
        setupListeners()
    }

    override fun intents(): Observable<SearchIntent> {
        return Observable.merge( // Merge the individual intent publishers into one observable stream for the viewmodel
                initialIntent(),
                loadListingsIntentPublisher
        )
    }

    override fun render(state: SearchViewState) {
        Timber.d("SearchFragment Rendering State: $state")
//        button_getListings.text = when {
//            state.isErrorState -> "Error."
//            state.inProgress -> "Loading...."
//            else -> "Search"
//        }
        when {
            state.listings.isNotEmpty() -> {
                listingAdapter.listings = state.listings
            }
        }
    }

    fun initialIntent(): Observable<SearchIntent.InitialIntent> {
        return Observable.just(SearchIntent.InitialIntent)
    }

    fun setupListeners() {
        RxTextView.textChanges(et_search)
            .subscribe {
                loadListingsIntentPublisher.onNext(SearchIntent.LoadListingsIntent("$it"))
            }
            .addTo(compositeDisposable)
    }

    companion object {
        fun newInstance(): SearchFragment {
            val fragment = SearchFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

}
