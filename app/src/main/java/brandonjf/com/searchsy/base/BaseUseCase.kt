package brandonjf.com.searchsy.base

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by brandon on 3/15/18.
 */
interface BaseUseCase<T>{
    fun buildUseCaseObservable(): Single<T>
}