package brandonjf.com.searchsy.presentation.search

import brandonjf.com.searchsy.base.BaseIntent

/**
 * Created by Brandon on 3/11/18.
 */
sealed class SearchIntent : BaseIntent {
    object InitialIntent: SearchIntent()
    class LoadListingsIntent(val searchTerm: String): SearchIntent()
    object RefreshListingsIntent: SearchIntent()
}   