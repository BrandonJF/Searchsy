package brandonjf.com.searchsy.domain

import brandonjf.com.searchsy.base.BaseResult
import brandonjf.com.searchsy.data.models.ActiveListing

/**
 * Created by brandon on 3/15/18.
 */
sealed class SearchResult : BaseResult {
    sealed class LoadListingsResult : SearchResult() {
        data class Success(val listings: List<ActiveListing>) : LoadListingsResult()
        data class Failure(val error: Throwable) : LoadListingsResult()
        object Empty : LoadListingsResult()
        object InProgress : LoadListingsResult()
    }
}