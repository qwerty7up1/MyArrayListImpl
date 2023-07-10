package MyArrayList;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public class MyArrayList<E> implements MyArrayListable<E> {

    private E[] values;

    MyArrayList () {
        values = (E[]) new Object();
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public void addAll(Collection<? extends E> c) {

    }

    @Override
    public void clear() {

    }

    @Override
    public void get(int index) {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void remove(Object o) {

    }

    @Override
    public void sort(Comparator<? super E> c) {

    }

    @Override
    public Iterator<E> iterator() {
        return new MyArrayIterator(values);
    }
}