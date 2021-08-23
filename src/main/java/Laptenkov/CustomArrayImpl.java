package Laptenkov;

import java.util.Collection;

/**
 * Класс {@link CustomArrayImpl<T>}, представляет динамический массив,
 * реализует интерфейс {@link CustomArray<T>}.
 * Может хранить объекты любого типа и может динамически расширяться.
 *
 * @author habatoo
 */
public class CustomArrayImpl<T> implements CustomArray<T>  {

    /**
     * Основной контейнер для храниения {@link CustomArrayImpl<T>}.
     */
    private Object[] data;
    private int size;

    private final int DEFAULT_CAPACITY = 10;

    /**
     * Конструктор объекта {@link CustomArrayImpl<T>}.
     * Формирует объект с вместимостью по умолчанию 10 элементов.
     * Размер объекта {@link CustomArrayImpl<T>#size} нулевой.
     */
    public CustomArrayImpl() {
        data = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Конструктор объекта {@link CustomArrayImpl<T>}.
     * Формирует объект с вместимостью {@link CustomArrayImpl<T>#capacity} элементов.
     * Размер объекта {@link CustomArrayImpl<T>#size} нулевой.
     *
     * @throws IllegalArgumentException если величина вместимости capacity отрицательная
     */
    public CustomArrayImpl(int capacity) {
        if (capacity >= 0) {
            data = new Object[capacity];
            size = 0;
        } else {
            throw new IllegalArgumentException(
                    "Вместимость не может быть отрицательной: " + capacity);
        }
    }

    /**
     * Конструктор объекта динамического массива {@link CustomArrayImpl<T>}.
     * Формирует объект с вместимостью и размером равным переданному объекту
     * типа Collection.
     *
     * @param c объект коллекции который будет использован для создания массива.
     * @throws IllegalArgumentException если в качестве параметра передан null.
     */
    public CustomArrayImpl(Collection<T> c) {
        if (null == c) {
            throw new IllegalArgumentException("Объект коллекции не может быть null!");
        }
        data = c.toArray();
        size = data.length;
    }

    /**
     * Метод {@link CustomArrayImpl#size()} возвращает размер динамического массива
     * {@link CustomArrayImpl}
     *
     * @return целое число типа {@link int}
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Метод {@link CustomArrayImpl#isEmpty()} возвращает булево значение в зависимости от размера массива
     *
     * @return <code>true</code> если размер не нулевой,
     * <code>false</code> если размер нулевой.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Метод {@link CustomArrayImpl#add(T)} добавляет в динамический массив объект
     * типа Т. Возвращает булево значение в зависимости успешности добавления.
     *
     * @param item объект типа Т.
     * @return <code>true</code> если добавление успешно,
     * <code>false</code> добавление не успешно.
     */
    @Override
    public boolean add(T item) {
        ensureCapacity(1);
        data[size] = item;
        size++;
        return true;
    }

    /**
     * Метод {@link CustomArrayImpl#addAll(T[])} добавляет в динамический массив
     * масив объектов типа Т.
     * Возвращает булево значение в зависимости успешности добавления.
     *
     * @param items массив объектов типа Т.
     * @return <code>true</code> если добавление успешно,
     * <code>false</code> добавление не успешно.
     * @throws NullPointerException если в качестве параметра передан null.
     */
    @Override
    public boolean addAll(T[] items) {
        if (null == items) {
            throw new IllegalArgumentException("Объект для добавления не может быть null!");
        }

        int newSize = size + items.length;
        this.ensureCapacity(newSize);
        int j = 0;
        for (int i = size; i < newSize; i++) {
            data[i] = items[j++];
        }
        size = newSize;

        return true;
    }

    /**
     * Метод {@link CustomArrayImpl#addAll(Object[])} добавляет в динамический массив
     * масив объектов типа Collection.
     * Возвращает булево значение в зависимости успешности добавления.
     *
     * @param items типа Collection.
     * @return <code>true</code> если добавление успешно,
     * <code>false</code> добавление не успешно.
     * @throws IllegalArgumentException if parameter items is null
     */
    @Override
    public boolean addAll(Collection<T> items) {
        if (null == items) {
            throw new IllegalArgumentException("Объект для добавления не может быть null!");
        }

        Object[] newData = items.toArray();
        int newSize = size + newData.length;
        this.ensureCapacity(newSize);
        int j = 0;
        for (int i = size; i < newSize; i++) {
            data[i] = newData[j++];
        }
        size = newSize;

        return true;
    }

    /**
     * Метод {@link CustomArrayImpl#addAll(int, Object[])} добавляет в динамический массив
     * масив объектов типа T начиная с индекса.
     * Возвращает булево значение в зависимости успешности добавления.
     *
     * @param index - index с которого будет вставлен объект типа Т.
     * @param items - объект типа Т для добавления.
     * @return <code>true</code> если добавление успешно,
     * <code>false</code> добавление не успешно.
     * @throws ArrayIndexOutOfBoundsException если индекс за пределами массива.
     * @throws IllegalArgumentException       если переданный параметр равен null.
     */
    @Override
    public boolean addAll(int index, T[] items) {
        rangeCheck(index);
        if (null == items) {
            throw new IllegalArgumentException("Объект для добавления не может быть null!");
        }

        int newSize = size + items.length;
        Object[] newData = new Object[newSize];

        // копируем в новый объект часть от начала до индекса
        for (int i = 0; i < index; i++) {
            newData[i] = data[i];
        }

        // копируем в новый объект часть от индекса до конца добавляемого объекта
        int firstAddIndex = index; // индекс с которого начато копирование нового массива
        int lastAddIndex = index + items.length; // индекс которым заканчивается копируемый массив
        int j = 0;
        for (; firstAddIndex < lastAddIndex; firstAddIndex++) {
            newData[firstAddIndex] = items[j++];
        }

        // копируем в новый объект оставшуюся часть исходного объекта
        int remainDataIndex = newSize - lastAddIndex; // индекс с которого продолжается копирование
        for (int i = remainDataIndex; i < newSize; i++) {
            newData[i] = data[i];
        }

        data = newData;
        size = newSize;
        return true;
    }

    /**
     * Метод {@link CustomArrayImpl#get(int)} возвращает элемент динамического массива
     * по индексу.
     *
     * @param index - index запрашиваемого элемента
     * @return объект находящийся по индексу.
     * @throws ArrayIndexOutOfBoundsException если индекс за гарницами массива.
     */
    @Override
    public T get(int index) {
        rangeCheck(index);
        return (T) data[index];
    }

    /**
     * Метод {@link CustomArrayImpl#set(int, T)} устанавливает элемент динамического массива
     * по индексу равным значению объекта.
     *
     * @param index - index вводимого элемента.
     * @param item  - объект вводимого элемента.
     * @return предыдущее значение элемента массива.
     * @throws ArrayIndexOutOfBoundsException если индекс за гарницами массива.
     */
    @Override
    public T set(int index, T item) {
        rangeCheck(index);
        T oldItem = (T) data[index];
        data[index] = item;
        return oldItem;
    }

    /**
     * Метод {@link CustomArrayImpl#remove(int)} удаляет элемент динамического массива
     * по индексу равным переданному параметру.
     *
     * @param index - index
     * @throws ArrayIndexOutOfBoundsException если индекс за гарницами массива.
     */
    @Override
    public void remove(int index) {
        rangeCheck(index);
        Object[] newData = new Object[size - 1];
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (i != index) {
                newData[j++] = data[i];
            }
        }
        size--;
        data = newData;

    }

    /**
     * Метод {@link CustomArrayImpl#remove(int)} удаляет элемент динамического массива
     * по значению переданного объекта. Удаляется элемент найденный первым.
     *
     * @param item - item
     * @return true if item was removed
     */
    @Override
    public boolean remove(T item) {
        Object[] newData = new Object[size - 1];
        int j = 0;
        boolean result = false;

        int index = 0;
        if (item == null) {
            for (; index < size; index++) {
                if (null == data[index]) {
                    result = true;
                    break;
                }
                if (index == size - 1) {
                    return false;
                }
                newData[j++] = data[index];
            }

        } else {
            for (; index < size; index++) {
                if (item.equals(data[index])) {
                    result = true;
                    break;
                }
                if (index == size - 1) {
                    return false;
                }
                newData[j++] = data[index];
            }

        }
        index++;
        for (; index < size; index++) {
            newData[j++] = data[index];
        }
        size--;
        data = newData;
        return result;
    }

    /**
     * Метод {@link CustomArrayImpl#contains(T)} проверяет наличие объекта в
     * динамическом массиве.
     *
     * @param item - объект типа Т для поиска в массиве.
     * @return <code>true</code> если объект содержится в массиве и <code>false</code> если нет.
     */
    @Override
    public boolean contains(T item) {
        if (item == null) {
            for (int i = 0; i < size; i++)
                if (data[i] == null)
                    return true;
        } else {
            for (int i = 0; i < size; i++)
                if (item.equals(data[i]))
                    return true;
        }
        return false;
    }

    /**
     * Метод {@link CustomArrayImpl#indexOf(T)}
     * возвращает индекс по которому объект находится в динамическом массиве.
     *
     * @param item - объект типа Т для поиска в массиве.
     * @return index элемента по которому он находится в массиве или -1 если объект
     * не содержится в массиве.
     */
    @Override
    public int indexOf(T item) {
        if (item == null) {
            for (int i = 0; i < size; i++)
                if (data[i] == null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (item.equals(data[i]))
                    return i;
        }
        return -1;
    }

    /**
     * Метод {@link CustomArrayImpl#ensureCapacity(int)}
     * увеличивает вместимость динамического массива
     * {@link CustomArrayImpl} если достигнута предельная текущая вместимость.
     *
     * @param newElementsCount - новое количество элементов.
     */
    @Override
    public void ensureCapacity(int newElementsCount) {

        int totalSize = size + newElementsCount;
        if (totalSize <= data.length) {
            return;
        }

        int newCapacity = 2 * data.length;
        if (totalSize > newCapacity) {
            newCapacity = totalSize;
        }

        Object[] newData = new Object[newCapacity];
        for (int i = 0; i < data.length; ++i) {
            newData[i] = data[i];
        }
        data = newData;

    }

    /**
     * Метод {@link CustomArrayImpl#getCapacity()}
     * возвращает вместимость динамического массива
     * {@link CustomArrayImpl}
     *
     * @return целое число типа {@link int}
     */
    @Override
    public int getCapacity() {
        return data.length;
    }

    /**
     * Метод {@link CustomArrayImpl#reverse()}
     * меняет порядок расположения объектов динамического массива
     * {@link CustomArrayImpl} на противоположный.
     */
    @Override
    public void reverse() {
        Object[] reverseData = new Object[size];
        int j = 0;
        for (int i = size - 1; i >= 0; i--) {
            reverseData[j++] = data[i];
        }
        data = reverseData;
    }

    /**
     * Метод {@link CustomArrayImpl#toString()}
     * возвращает строковое представление динамического массива {@link CustomArrayImpl}
     *
     * @return объект типа String в формате '[ element1 element2 ... elementN ] или [ ] если массив пустой.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; ++i) {
            sb.append(" " + data[i]);
        }
        sb.append(" ]");
        return sb.toString();
    }

    /**
     * Метод {@link CustomArrayImpl<T>#toArray()}
     * возвращает копию текущего объекта
     * {@link CustomArrayImpl<T>}.
     *
     * @return объект типа {@link CustomArrayImpl<T>}.
     */
    @Override
    public Object[] toArray() {
        Object[] newData = new Object[size];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        return newData;
    }

    /**
     * Метод {@link CustomArrayImpl<T>#rangeCheck(integer)} проверяет,
     * что передаваемое значение index не отрицательно и не выходит за пределы массива.
     *
     * @param index целое число.
     */
    private void rangeCheck(int index) {
        if (index < 0 || index >= size)
            throw new ArrayIndexOutOfBoundsException(
                    "Индекс " + index + " отрицательный или находится за пределами массива.");
    }

}