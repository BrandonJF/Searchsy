package brandonjf.com.searchsy.presentation.search

import brandonjf.com.searchsy.domain.ListingsUseCases
import brandonjf.com.searchsy.domain.SearchResult
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

/**
 * Created by brandon on 3/14/18.
 */
class ListingsActionProcessorManager(useCases: ListingsUseCases) {

    private val loadListingsProcessor: ObservableTransformer<SearchAction.LoadListings, SearchResult> = ObservableTransformer {

        it.switchMap { useCases.getListingsBySearchTerm(it.searchTerm) }
    }

    var actionProcessor: ObservableTransformer<SearchAction, SearchResult> = ObservableTransformer {
        it.publish {
            it.ofType(SearchAction.LoadListings::class.java)
                .compose(loadListingsProcessor)
                .mergeWith(it.filter({ it !is SearchAction.LoadListings })
                    .flatMap {
                        Observable.error<SearchResult>(
                            IllegalArgumentException("Unknown Action type"))
                    })
        }
    }
}