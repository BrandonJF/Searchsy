package brandonjf.com.searchsy.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import brandonjf.com.searchsy.domain.ListingsUseCases
import brandonjf.com.searchsy.presentation.search.SearchViewModel

/**
 * Created by brandon on 3/15/18.
 */
class SearchViewModelFactory(private val useCases: ListingsUseCases): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(useCases) as T
    }
}