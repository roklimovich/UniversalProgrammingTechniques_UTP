package pjwstk.edu.pl.s27619.assignmnet01.interfaces;

import java.util.List;

public interface IContainer<TElement extends IAggregable<TElement, TAggregateResult> & IDeeplyCloneable<TElement>,
        TAggregateResult> {

    List<TElement> elements();

    TAggregateResult aggregateAllElements();

    TElement cloneElementAtIndex(int index);
}
