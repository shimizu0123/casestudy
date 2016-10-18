package IteratorPattern;

public class ConcreteIterator implements Iterator {

    private ConcreteAggregate concreteAggregate;
    private int index = 0;
    public ConcreteIterator(ConcreteAggregate concreteAggregate) {
        this.concreteAggregate = concreteAggregate;
    }

    @Override
    public boolean hasNext() {
        if (index < concreteAggregate.getSize()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object next() {
        return concreteAggregate.getData(index++);
    }

}
