package brandonjf.com.searchsy.base

import android.support.v4.app.Fragment
import io.reactivex.disposables.CompositeDisposable

/**
 * A base fragment to handle basic things like CompositeDisposables and other lifecycle methods.
 */
open class BaseFragment: Fragment() {
    protected val compositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

}