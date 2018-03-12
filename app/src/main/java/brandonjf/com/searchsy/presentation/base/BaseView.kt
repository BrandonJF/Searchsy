package brandonjf.com.searchsy.presentation.base

import io.reactivex.Observable

/**
 * Created by Brandon on 3/11/18.
 */
interface BaseView<I : BaseIntent, in S : BaseViewState> {
    fun intents(): Observable<I>
    fun render(state: S)
}