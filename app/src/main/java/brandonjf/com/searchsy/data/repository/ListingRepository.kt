package brandonjf.com.searchsy.data.repository

import brandonjf.com.searchsy.data.models.ActiveListing
import io.reactivex.Single
import timber.log.Timber

/**
 * A repository for retrieving [ActiveListing] object from remote, local, or other sources through
 * injection.
 *
 * @property remoteDataSource the source of the repository will go to for listings bu default.
 * @constructor Creates a listing repository.
 */
class ListingRepository (private val remoteDataSource: ListingDataSource): ListingDataSource {


    /**
     * Retrieves a list of [ActiveListing] from the data source
     * @return a list containing the listings.
     */
    override fun getListings(searchTerms: String): Single<List<ActiveListing>> {
        //TODO - convert the response
        return if (isConnectedToInternet()) getRemoteListings(searchTerms) else Single.fromCallable { emptyList<ActiveListing>() }
    }

    private fun isConnectedToInternet(): Boolean {
        return true //TODO - actually implement connectivity check.
    }

    private fun getRemoteListings(searchTerms: String): Single<List<ActiveListing>> {
        return remoteDataSource
                .getListings(searchTerms)
                .doOnSuccess { Timber.d("SUCCESS: getRemoteListings") }
    }
}