package brandonjf.com.searchsy.domain

import brandonjf.com.searchsy.base.BaseViewState
import brandonjf.com.searchsy.data.models.ActiveListing

/**
 * Created by Brandon on 3/11/18.
 */
data class SearchViewState(val inProgress: Boolean = false,
                           val listings: List<ActiveListing> = emptyList(),
                           val isErrorState: Boolean = false) : BaseViewState {
    companion object {
        val Idle: SearchViewState = SearchViewState()
    }
}