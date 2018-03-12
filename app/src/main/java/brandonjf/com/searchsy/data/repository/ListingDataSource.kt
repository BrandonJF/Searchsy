package brandonjf.com.searchsy.data.repository

import brandonjf.com.searchsy.data.models.ActiveListing
import io.reactivex.Single


/**
 * Interface defining methods for the data operations related to Listing sources.
 * Implemented by external data sources, like a remote server, local persistent DB or in-memory cache.
 */
interface ListingDataSource {
    fun getListings(searchTerms: String): Single<List<ActiveListing>>
}
