package brandonjf.com.searchsy

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import brandonjf.com.searchsy.presentation.search.SearchViewModel
import kotlinx.android.synthetic.main.activity_search.*
import timber.log.Timber

class SearchActivity : AppCompatActivity() {

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

    fun setupListeners() {
        button_getListings.setOnClickListener { v ->
            Timber.d("CLICKED: getListings button")
            viewModel.testNetwork()
        }
    }


}
