package brandonjf.com.searchsy.data.repository

import brandonjf.com.searchsy.data.models.ActiveListing
import brandonjf.com.searchsy.ui.applySchedulers
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * A repository for retrieving [ActiveListing] object from remote, local, or other sources through
 * injection.
 *
 * @property remoteRepository the source of the repository will go to for listings bu default.
 * @constructor Creates a listing repository.
 */
class ListingService (private val remoteRepository: ListingRepository): ListingRepository {

    /**
     * Retrieves a list of [ActiveListing] from the data source
     * @return a list containing the listings.
     */
    override fun getListings(searchTerms: String): Observable<List<ActiveListing>> {
        //TODO - convert the response
        return if (isConnectedToInternet()) getRemoteListings(searchTerms) else Observable.fromCallable { emptyList<ActiveListing>() }
    }

    private fun isConnectedToInternet(): Boolean {
        return true //TODO - actually implement connectivity check.
    }

    private fun getRemoteListings(searchTerms: String): Observable<List<ActiveListing>> {
        return remoteRepository
                .getListings(searchTerms)
                .compose (applySchedulers())
                .doOnComplete { Timber.d("SUCCESS: getRemoteListings") }
    }
}