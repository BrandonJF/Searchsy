package brandonjf.com.searchsy.data.remote

import brandonjf.com.searchsy.data.models.ActiveListingResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Describes the Etsy API interface for retrieving listings from the server. This interface is meant
 * to be implemented by Retrofit.
 */
interface EtsyApi {

    /*
    * Hardcoding the API key for now as there is only one API call and proper handling of credentials
    * and auth tokens feels out of scope for this assignment.
    * Todo- Remove hardcoded API key
    * */

    @GET("listings/active/?api_key=ob5cp17p7q8hvw37wv4nopuf&includes=MainImage}")
    fun getListings(@Query("keywords") searchTerms: String): Single<ActiveListingResponse>
}