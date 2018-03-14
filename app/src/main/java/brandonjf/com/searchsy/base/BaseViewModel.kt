package brandonjf.com.searchsy.base

import io.reactivex.Observable

/**
 * Object that will subscribes to a [BaseView]'s [BaseIntent]s,
 * process it and emit a [BaseViewState] back.
 *
 * @param I Top class of the [MviIntent] that the [BaseViewModel] will be subscribing
 * to.
 * @param S Top class of the [BaseViewState] the [BaseViewModel] will be emitting.
 */
interface BaseViewModel<I: BaseIntent, S : BaseViewState>{
    fun processIntents(intents: Observable<I>)
    fun states(): Observable<S>
}