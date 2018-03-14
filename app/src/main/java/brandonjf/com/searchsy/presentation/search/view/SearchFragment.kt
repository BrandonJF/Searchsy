package brandonjf.com.searchsy.presentation.search.view


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import brandonjf.com.searchsy.R
import brandonjf.com.searchsy.base.BaseView
import brandonjf.com.searchsy.presentation.search.SearchIntent
import brandonjf.com.searchsy.presentation.search.SearchViewModel
import brandonjf.com.searchsy.presentation.search.SearchViewState
import io.reactivex.Observable
import kotlinx.android.synthetic.main.layout_fragment_search.*
import timber.log.Timber


/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment(), BaseView<SearchIntent, SearchViewState> {

    private val viewModel: SearchViewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this)
                .get(SearchViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.layout_fragment_search, container, false)
    }

    override fun onStart() {
        super.onStart()
        setupListeners()
    }

    override fun intents(): Observable<SearchIntent> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun render(state: SearchViewState) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun setupListeners() {
        button_getListings.setOnClickListener { v ->
            Timber.d("CLICKED: getListings button")
            viewModel.testNetwork()
        }
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
