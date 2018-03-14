package brandonjf.com.searchsy.presentation.search.view

import android.os.Bundle
import brandonjf.com.searchsy.base.BaseActivity

class SearchActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(SearchFragment.newInstance())
    }
}
