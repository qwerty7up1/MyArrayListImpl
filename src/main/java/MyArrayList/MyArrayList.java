package MyArrayList;

import java.util.*;

public class MyArrayList<E> implements MyArrayListable<E> {

    /*
    Размер списка ArrayList (количество содержащихся в нем элементов)
    */
    private int size;
    private int capacity;
    private static final int CAPACITY = 10; // Начальная емкость по умолчанию.

    /*
    Общий пустой экземпляр массива, используемый для пустых экземпляров размера по умолчанию.
    Мы отличаем это от EMPTY_ARRAY, чтобы знать, насколько увеличиться при добавлении первого элемента.
    */
    private Object[] array;

    /*
    Общий пустой экземпляр массива, используемый для пустых экземпляров.
    */
    private static final Object[] EMPTY_ARRAY = {};

    /*
    Создает пустой список с начальной емкостью 10 (десять).
    */
    public MyArrayList() {
        capacity = CAPACITY;
        array = new Object[capacity];
    }

    /*
    Создает пустой список с указанной начальной емкостью.
    Параметры:
    capacity — начальная емкость списка
    Выбрасывает исключение:
    IllegalArgumentException – если указанная начальная емкость отрицательна.
    */
    public MyArrayList(int capacity) {
        if (capacity > 0) {
            array = new Object[capacity];
        } else if (capacity == 0) {
            array = EMPTY_ARRAY;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }
    }

    /*
    Увеличивает кол-во элементов списка в 2 раза
    */
    private void increaseCapacity() {
        capacity = capacity * 2;
        Object[] newArray = new Object[capacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    /*
    Возвращает размер списка
    */
    @Override
    public int size() {
        return size;
    }

    /*
    Вставляет указанный элемент в указанную позицию в этом списке.
    Сдвигает элемент, находящийся в данный момент в этой позиции (если есть),
    и любые последующие элементы вправо (добавляет единицу к их индексам).
    Параметры:
    index – индекс, по которому нужно вставить указанный элемент
    element – элемент, который нужно вставить
    */
    @Override
    public void add(int index, E element) {
        if (index >= 0 || index <= size) {
            if (size >= capacity) {
                increaseCapacity();
            }
            System.arraycopy(array, index, array, index + 1, size + 1 - index);
            array[index] = element;
            size++;
        } else {
            System.out.println("index: " + index + " out of range");
        }
    }

    /*
    Добавляет новый объект в список
    */
    @Override
    public boolean add(Object o) {
        if (size >= capacity) {
            increaseCapacity();
        }
        array[size++] = o;
        return true;
    }

    /*
    Добавляет в список коллекцию
    */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c == null) {
            return false;
        }
        if (c.isEmpty()) {
            return false;
        }
        for (Object o : c) {
            add(o);
        }
        return true;
    }

    /*
    Вывод списка в строковой форме
    */
    @Override
    public String toString() {
        return "MyArrayList{" +
                "array=" + Arrays.toString(array) +
                '}';
    }

    /*
    Смещение элементов списка влево на 1 элемент
    */
    private void shiftLeft(int start) {
        size--;
        if (size <= 0) {
            return;
        }
        if (size != start) {
            System.arraycopy(array, start + 1, array, start, size - start);
        }
        array[size] = null;
    }

    /*
    Очистка списка
    */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    /*
    Получение элемента списка по индексу
    */
    @Override
    public E get(int index) {
        if ((index < size) && (index >= 0)) {
            return (E) array[index];
        }
        return null;
    }

    /*
    Изменение элемента списка по индексу
    */
    @Override
    public void set(E e, int index) {
        checkIndex(index);
        array[index] = e;
    }

    /*
    Проверка ввода индекса списка, если вводимый индекс меньше 0 и
    больше размера списка - вывод исключения IndexOutOfBoundsException
    */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    /*
    Проверка не пустой ли список
    */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /*
    Удаление элемента списка по индексу и смещение элементов справа от удаляемого - влево на 1 элемент
    */
    @Override
    public E remove(int index) {
        Object o = null;
        if ((index < size) && (index >= 0)) {
            o = get(index);
            shiftLeft(index);
        }
        return (E) o;
    }

    /*
    Удаление объекта списка по индексу и смещение элементов справа от удаляемого - влево на 1 элемент
    */
    @Override
    public boolean remove(Object o) {
        if ((size == 0)) {
            return false;
        }
        int i;
        for (i = 0; i < size; i++) {
            if (array[i] == null && o == null) {
                break;
            }
            if ((array[i] != null) && (array[i].equals(o))) {
                break;
            }
        }
        if (i < size) {
            shiftLeft(i);
            return true;
        }
        return false;
    }

    /*
    Сортировка списка методом быстрой сортировки
    */
    @Override
    public void sort(MyArrayList<E> array, Comparator<? super E> c) {
        int low = 0;
        int high = array.size - 1;

        quickSort(array, c, low, high);
    }


    /*
    Алгоритм быстрой сортировки
    */
    private <E> void quickSort(MyArrayList<E> array, Comparator<? super E> comparator, int leftIndex, int rightIndex) {

        // Завершить, если массив пуст или уже нечего делить (выход из рекурсии)
        if (array.size == 0 || leftIndex >= rightIndex) return;

        //1. Выбираем опорный элемент
        E support = (E) array.get((leftIndex + rightIndex) / 2);
        int leftMarkerIndex = leftIndex;
        int rightMarkerIndex = rightIndex;

        while (leftMarkerIndex < rightMarkerIndex) {
            // 2. Перекладываем все элементы влево либо вправо от опорного элемента
            // Двигаем левый маркер слева на право, если элемент меньше, чем pivot
            while (comparator.compare((E) array.get(leftMarkerIndex), support) < 0) leftMarkerIndex++;
            // Двигаем рпавый маркер, если элемент больше, чем pivot
            while (comparator.compare((E) array.get(rightMarkerIndex), support) > 0) rightMarkerIndex--;
            // Слева упёрлись в несоответствие и справа упёрлись в несоответствие
            // Если левый маркер все ещё меньше правого, то нужно менять элементы,
            // Потому что до этого не отработало два while
            if (leftMarkerIndex <= rightMarkerIndex) {
                E swap = array.get(leftMarkerIndex);
                array.set(array.get(rightMarkerIndex), leftMarkerIndex);
                array.set(swap, rightMarkerIndex);
                // Сдвигаем маркеры, чтобы получить новые границы
                leftMarkerIndex++;
                rightMarkerIndex--;
            }
        }
        // 3. Рекурсия для сортировки левой и правой части
        // Если у нас есть левый подмассив (от начала до правого маркера пришедшего в середину)
        if (leftIndex < rightMarkerIndex) quickSort(array, comparator, leftIndex, rightMarkerIndex);
        // Если у нас есть правый подмассив (от конца до левого маркера пришедшего в середину)
        if (rightIndex > leftMarkerIndex) quickSort(array, comparator, leftMarkerIndex, rightIndex);
    }

}