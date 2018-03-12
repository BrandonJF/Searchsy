package brandonjf.com.searchsy.presentation.base

import io.reactivex.Observable

/**
 * Created by Brandon on 3/11/18.
 */
interface BaseViewModel<I: BaseIntent, S : BaseViewState>{
    fun processIntents(intents: Observable<I>)
    fun states(): Observable<S>
}