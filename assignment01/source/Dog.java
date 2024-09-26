package pjwstk.edu.pl.s27619.assignmnet01.source;

import pjwstk.edu.pl.s27619.assignmnet01.interfaces.IAggregable;
import pjwstk.edu.pl.s27619.assignmnet01.interfaces.IDeeplyCloneable;

public class Dog implements IAggregable<Dog, Double>, IDeeplyCloneable<Dog> {

    private String name;
    private double weight;

    public Dog(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    /**
     * This method finds average value of weight and intermediate result
     *
     * @param intermediateResult - contains information about intermediate result of aggregating
     * @return average of two values
     */
    @Override
    public Double aggregate(Double intermediateResult) {

        //if intermediate result equals null, we just return weight without any manipulations
        if (intermediateResult == null) {
            return weight;
        }

        return (weight + intermediateResult) / 2;
    }

    /**
     * Method clones current object
     *
     * @return cloned object of class Dog
     */
    @Override
    public Dog deepClone() {
        return new Dog(name, weight);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
