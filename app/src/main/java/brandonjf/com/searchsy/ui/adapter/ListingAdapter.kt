package brandonjf.com.searchsy.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import brandonjf.com.searchsy.R
import brandonjf.com.searchsy.data.models.ActiveListing
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.layout_listing_item.view.*

/**
 * Created by brandon on 3/16/18.
 */
class ListingAdapter(listings: List<ActiveListing> = emptyList()) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var listings = listings
    set(value) { field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return LayoutInflater
                .from(parent.context)
                .inflate(R.layout.layout_listing_item, parent, false)
                .let (::ListingViewHolder)
    }

    override fun getItemCount() = listings.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ListingViewHolder)?.bind(listings[position])
    }

    class ListingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val options = RequestOptions().apply {
            centerCrop()
        }
        fun bind(listing: ActiveListing) {
            with(itemView) {
                tv_title.text = listing.title
                Glide.with(context).load(listing.mainImage.url570xN)
                        .apply(options).into(iv_main)
            }
        }
    }
}

