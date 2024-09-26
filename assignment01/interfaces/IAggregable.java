package pjwstk.edu.pl.s27619.assignmnet01.interfaces;

public interface IAggregable<TElement extends IAggregable<TElement, TResult>, TResult> {

    TResult aggregate(TResult intermediateResult);
}
