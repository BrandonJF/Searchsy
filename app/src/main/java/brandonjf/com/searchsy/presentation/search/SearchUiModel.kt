package brandonjf.com.searchsy.presentation.search

import brandonjf.com.searchsy.presentation.base.BaseViewState
import brandonjf.com.searchsy.presentation.search.model.ListingView

/**
 * Created by Brandon on 3/11/18.
 */
sealed class SearchUiModel(val inProgress: Boolean = false,
                           val listings: List<ListingView>? = null)
    : BaseViewState {

    object InProgress : SearchUiModel(true, null)

    object Failed : SearchUiModel()

    data class Success(private val result: List<ListingView>?) : SearchUiModel(false, result)

    class Idle : SearchUiModel(false, null)

}