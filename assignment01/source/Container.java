package pjwstk.edu.pl.s27619.assignmnet01.source;

import pjwstk.edu.pl.s27619.assignmnet01.interfaces.IAggregable;
import pjwstk.edu.pl.s27619.assignmnet01.interfaces.IContainer;
import pjwstk.edu.pl.s27619.assignmnet01.interfaces.IDeeplyCloneable;

import java.util.ArrayList;
import java.util.List;

public class Container<TElement extends IAggregable<TElement, TAggregateResult> & IDeeplyCloneable<TElement>,
        TAggregateResult> implements IContainer<TElement, TAggregateResult> {

    private List<TElement> elements;

    public Container() {
        elements = new ArrayList<>();
    }

    /**
     * Method returns list of elements
     */
    @Override
    public List<TElement> elements() {
        return elements;
    }

    /**
     * Method aggregate all elements from the list
     *
     * @return result of aggregating
     */
    @Override
    public TAggregateResult aggregateAllElements() {
        TAggregateResult intermediateResult = null; //variable contains intermediate result of aggregating

        //check elements in our list, if we don't have elements, we return null
        if (elements == null) {
            return null;
        }

        //using for-each loop iterate all elements from the list and call method aggregate for each element
        for (TElement element : elements) {
            intermediateResult = element.aggregate(intermediateResult);
        }

        return intermediateResult;
    }


    /**
     * This method returns element from the list using given index and clone this element
     *
     * @param index - contains position of element in the list
     * @return cloned element from the list using given index
     */
    @Override
    public TElement cloneElementAtIndex(int index) {

        //check if index less than 0 or index grater than size of our list, we return null
        if (index < 0 || index > elements.size() - 1) {
            return null;
        }

        return elements.get(index).deepClone();
    }
}

