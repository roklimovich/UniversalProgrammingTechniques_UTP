package pjwstk.edu.pl.s27619.assignmnet01.interfaces;

public interface IDeeplyCloneable<TElement extends IDeeplyCloneable<TElement>> {

    TElement deepClone();
}