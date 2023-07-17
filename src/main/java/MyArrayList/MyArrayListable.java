package MyArrayList;

import java.util.Collection;
import java.util.Comparator;

public interface MyArrayListable<E> {
    int size();
    void add(int index, E element);

    /*
    Добавление объекта в список
    */
    boolean add(Object o);
    /*
    Добавление коллекций в список
    */
    boolean addAll(Collection<? extends E> c);
    /*
    Очиска списка
    */
    void clear();
    /*
    Получение элемента списка по индексу
    */
    E get(int index);
    /*
    Изменение элемента списка по индексу
    */
    void set(E e, int index);
    /*
    Проверка не пустой ли список
    */
    boolean isEmpty();
    /*
    Удаление элемента списка по индексу
    */
    E remove(int index);
    /*
    Удаление объекта списка по индексу
    */
    boolean remove(Object o);
    /*
    Быстрая сортировка списка
    */
    void sort(MyArrayList <E> array, Comparator<? super E> c);
}
