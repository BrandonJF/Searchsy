package brandonjf.com.searchsy.domain

import io.reactivex.Observable

/**
 * Created by brandon on 3/15/18.
 */
interface ListingsUseCases {
    fun getListingsBySearchTerm(searchTerm: String): Observable<out SearchResult>
//    fun getListingsBySearchTerm(searchTerm: String): Observable<out SearchResult>
}
