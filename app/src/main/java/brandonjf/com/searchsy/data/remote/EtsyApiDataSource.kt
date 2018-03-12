package brandonjf.com.searchsy.data.remote

import brandonjf.com.searchsy.data.models.ActiveListing
import brandonjf.com.searchsy.data.repository.ListingDataSource
import com.facebook.stetho.okhttp3.StethoInterceptor
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory



/**
 * Created by Brandon on 3/11/18.
 */
class EtsyApiDataSource : ListingDataSource {
    private val API_URL = "https://api.etsy.com/v2/"
    val api: EtsyApi = getApiInstance()

    fun getApiInstance(): EtsyApi {
        return getRetrofitInstance().create(EtsyApi::class.java)
    }

    private fun getRetrofitInstance(): Retrofit {

        val okHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .build()

        return Retrofit.Builder()
                .baseUrl(API_URL)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    override fun getListings(searchTerms: String): Single<List<ActiveListing>> {
        //Need to extract just the listings from the overall network response
        return api.getListings(searchTerms).map { response ->
            response.results
        }
    }
}