package MyArrayList;

import java.util.Collection;
import java.util.Comparator;

public interface MyArrayListable<E> extends Iterable<E> {
    void add(int index, E element);
    void addAll(Collection<? extends E> c);
    void clear();
    void get(int index);
    boolean isEmpty();
    void remove(Object o);
    void sort(Comparator<? super E> c);
}
