package brandonjf.com.searchsy.presentation.search

import brandonjf.com.searchsy.base.BaseAction

/**
 * Created by brandon on 3/14/18.
 */
sealed class SearchAction : BaseAction {
    data class LoadListings(val searchTerm: String): SearchAction()
}