package brandonjf.com.searchsy.domain

import brandonjf.com.searchsy.data.remote.EtsyRemoteDataSource
import brandonjf.com.searchsy.data.repository.ListingRepository
import brandonjf.com.searchsy.data.repository.ListingService
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

/**
 * Created by brandon on 3/15/18.
 */
class ListingsInteractor(private val listingRepository: ListingRepository) : ListingsUseCases {
    override fun getListingsBySearchTerm(searchTerm: String): Observable<out SearchResult> {
        return Observable.fromCallable { searchTerm }
            .filter { it.length > 2 } //todo- figure out how to merge these so that we can return a blank state when there are too few characters
            .debounce(300, TimeUnit.MILLISECONDS) //Give a little time between loads
            .distinctUntilChanged() //Never load the exact same term as previous
            .switchMap {
                ListingService(EtsyRemoteDataSource())
                    .getListings(searchTerm)
                    .map {
                        when {
                            it.isEmpty() -> SearchResult.LoadListingsResult.Empty
                            else -> SearchResult.LoadListingsResult.Success(it)
                        }
                    }
                    .onErrorReturn {
                        SearchResult.LoadListingsResult.Failure(it)
                    }
                    .startWith(SearchResult.LoadListingsResult.InProgress)
            }
    }
}