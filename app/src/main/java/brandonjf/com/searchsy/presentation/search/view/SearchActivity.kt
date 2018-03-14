package brandonjf.com.searchsy.presentation.search.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import brandonjf.com.searchsy.R
import brandonjf.com.searchsy.base.BaseView
import brandonjf.com.searchsy.base.BaseViewState
import brandonjf.com.searchsy.presentation.search.SearchIntent
import brandonjf.com.searchsy.presentation.search.SearchViewModel
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_search.*
import timber.log.Timber

class SearchActivity : AppCompatActivity(), BaseView<SearchIntent, BaseViewState> {

    private val viewModel: SearchViewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this)
                .get(SearchViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
    }

    override fun onStart() {
        super.onStart()
        setupListeners()
    }

    override fun intents(): Observable<SearchIntent> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun render(state: BaseViewState) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun setupListeners() {
        button_getListings.setOnClickListener { v ->
            Timber.d("CLICKED: getListings button")
            viewModel.testNetwork()
        }
    }


}
