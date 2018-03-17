package brandonjf.com.searchsy.ui

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers



/**
 * Created by brandon on 3/15/18.
 */
fun <T> applySchedulers(): ObservableTransformer<T, T> {
    return ObservableTransformer<T, T> { upstream ->
        upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}