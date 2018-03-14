package brandonjf.com.searchsy.presentation.search

import brandonjf.com.searchsy.base.BaseViewState
import brandonjf.com.searchsy.presentation.search.model.ListingView

/**
 * Created by Brandon on 3/11/18.
 */
sealed class SearchViewState(val inProgress: Boolean = false,
                             val listings: List<ListingView>? = null,
                             val isErrorState: Boolean = false)
    : BaseViewState {

    object InProgress : SearchViewState(
            inProgress = true,
            listings = null
    )

    object Failed : SearchViewState(
            isErrorState = true
    )

    data class Success(private val result: List<ListingView>?) : SearchViewState(
            inProgress = false,
            isErrorState = false,
            listings = result
    )

    class Idle : SearchViewState(
            inProgress = false,
            listings = null
    )

}