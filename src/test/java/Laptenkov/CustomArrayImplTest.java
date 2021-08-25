package Laptenkov;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс для тестирования public методов класса {@link CustomArrayImpl<Object>}.
 *
 * @author habatoo
 */
class CustomArrayImplTest {

    private CustomArrayImpl<String> customEmptyArray;
    private CustomArrayImpl<String> customNotEmptyArray;

    /**
     * Инициализация экземпляров тестируемого класса {@link CustomArrayImpl}.
     */
    @BeforeEach
    void setUp() {
        customEmptyArray = new CustomArrayImpl<>();

        customNotEmptyArray = new CustomArrayImpl<>();
        customNotEmptyArray.add("first");
        customNotEmptyArray.add("second");
        customNotEmptyArray.add("third");
        customNotEmptyArray.add(null);
        customNotEmptyArray.add(null);
        customNotEmptyArray.add("last");
    }

    /**
     * Очистка экземпляров тестируемого класса {@link CustomArrayImpl}.
     */
    @AfterEach
    void tearDown() {
        customEmptyArray = null;
        customNotEmptyArray = null;
    }

    /**
     * Проверяет создание экземплара {@link CustomArrayImpl} при подаче коллекции.
     * Сценарий, при котором конструктор отрабатывает не пустую коллекцию, проверяет размер коллекции
     * равный 3 и отображение коллекции.
     */
    @Test
    public void CustomArrayImpl_Test() {
        Set<String> linkedHashSet = new LinkedHashSet<String>();
        linkedHashSet.add("first");
        linkedHashSet.add("second");
        linkedHashSet.add("third");
        customEmptyArray = new CustomArrayImpl<>(linkedHashSet);
        Assertions.assertEquals(3, customEmptyArray.size());
        Assertions.assertEquals(
                "[ first second third ]", customEmptyArray.toString());
    }

    /**
     * Проверяет создание экземплара {@link CustomArrayImpl} при подаче коллекции.
     * Сценарий, при котором конструктор отрабатывает пустую коллекцию и выбрасывает исключение
     * IllegalArgumentException.
     */
    @Test
    public void CustomArrayImplFail_Test() {
        Set<String> linkedHashSet = null;
        Throwable throwable = Assertions.assertThrows(
                IllegalArgumentException.class, () -> new CustomArrayImpl<>(linkedHashSet));
        Assertions.assertEquals(
                "Объект коллекции не может быть null!", throwable.getMessage());
    }

    /**
     * Метод {@link CustomArrayImplTest#size_Test} проверяет величину возвращаемого
     * динамического массива.
     * Сценарий, при котором метод отрабатывает пустой объект нулевого размера
     * и возвращает ноль.
     */
    @Test
    public void size_Test() {
        Assertions.assertEquals(0, customEmptyArray.size());
    }

    /**
     * Метод {@link CustomArrayImplTest#isEmptySuccess_Test} проверяет наличие объектов
     * динамического массива.
     * Сценарий, при котором метод отрабатывает пустой объект нулевого размера
     * и возвращает <code>true</code>.
     */
    @Test
    void isEmptySuccess_Test() {
        Assertions.assertEquals(true, customEmptyArray.isEmpty());
    }


    /**
     * Метод {@link CustomArrayImplTest#addStringSuccess_Test} проверяет возможность добавить объект
     * в динамический массив.
     * Сценарий, при котором метод отрабатывает добавление объекта типа Т
     * и возвращает <code>true</code>.
     */
    @Test
    void addStringSuccess_Test() {
        Assertions.assertEquals("[ first second third null null last ]", customNotEmptyArray.toString());
        Assertions.assertEquals(true, customNotEmptyArray.add("Test string"));
        Assertions.assertEquals(
                "[ first second third null null last Test string ]", customNotEmptyArray.toString());
    }

    /**
     * Метод {@link CustomArrayImplTest#addStringSuccessEmpty_Test} проверяет метод toString
     * для пустого массива.
     * Сценарий, при котором метод отрабатывает отображение пустого масива размером 0.
     */
    @Test
    void addStringSuccessEmpty_Test() {
        Assertions.assertEquals("[ ]", customEmptyArray.toString());
    }

    /**
     * Метод {@link CustomArrayImplTest#addAllObjectSuccess_Test} проверяет работу метода
     * {@link CustomArrayImpl#addAll(Object[])} при добавлении объекта типа Т.
     * Сценарий, при котором метод отрабатывает добавление не пустого объекта типа Т и возвращает
     * <code>true</code>.
     */
    @Test
    void addAllObjectSuccess_Test() {
        Assertions.assertEquals(
                true, customNotEmptyArray.addAll(new String[]{"first", "second"}));
        Assertions.assertEquals(
                "[ first second third null null last first second ]", customNotEmptyArray.toString());
    }

    /**
     * Метод {@link CustomArrayImplTest#addAllObjectNullFail_Test} проверяет метод {@link CustomArrayImpl#addAll(Object[])}
     * при добавлении объекта.
     * Сценарий, при котором метод отрабатывает добавление пустого массива null и выбрасывает исключение
     * IllegalArgumentException.
     */
    @Test
    void addAllObjectNullFail_Test() {
        Throwable throwable = Assertions.assertThrows(
                IllegalArgumentException.class, () -> customNotEmptyArray.addAll((String[]) null));
        Assertions.assertEquals(
                "Объект для добавления не может быть null!", throwable.getMessage());
    }

    /**
     * Метод {@link CustomArrayImplTest#addAllCollectionSuccess_Test} проверяет метод
     * {@link CustomArrayImpl# addAll(Collection[])}
     * при добавлении объекта.
     * Сценарий, при котором метод отрабатывает добавление не пустого объекта Collection и возвращает
     * <code>true</code>.
     */
    @Test
    void addAllCollectionSuccess_Test() {
        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("first");
        linkedHashSet.add("second");
        linkedHashSet.add("third");
        Assertions.assertEquals(true, customNotEmptyArray.addAll(linkedHashSet));
        Assertions.assertEquals(
                "[ first second third null null last first second third ]", customNotEmptyArray.toString());
    }

    /**
     * Метод {@link CustomArrayImplTest#addAllCollectionNullFail_Test} проверяет метод
     * {@link CustomArrayImpl# addAll(Collection[])}
     * при добавлении объекта.
     * Сценарий, при котором метод отрабатывает добавление не пустого объекта Collection и возвращает
     * <code>true</code>.
     */
    @Test
    void addAllCollectionNullFail_Test() {
        Set<String> linkedHashSet = null;
        Throwable throwable = Assertions.assertThrows(
                IllegalArgumentException.class, () -> customNotEmptyArray.addAll(linkedHashSet));
        Assertions.assertEquals(
                "Объект для добавления не может быть null!", throwable.getMessage());
    }

    /**
     * Метод {@link CustomArrayImplTest#addAllObjectIndexSuccess_Test} проверяет работу метода
     * {@link CustomArrayImpl#addAll(int, Object[])} при добавлении объекта типа Т.
     * Сценарий, при котором метод отрабатывает добавление не пустого объекта типа Т и возвращает
     * <code>true</code>.
     */
    @Test
    void addAllObjectIndexSuccess_Test() {
        Assertions.assertEquals(
                6, customNotEmptyArray.size());
        Assertions.assertEquals(
                true, customNotEmptyArray.addAll(2, new String[]{"first", "second"}));
        Assertions.assertEquals(
                8, customNotEmptyArray.size());
        Assertions.assertEquals(
                "[ first second first second third null null last ]", customNotEmptyArray.toString());
    }

    /**
     * Метод {@link CustomArrayImplTest#getSuccess_Test} проверяет работу метода  {@link CustomArrayImpl#get(int)}.
     * Сценарий проверяет возвращение объекта при обращении в пределах массива.
     * По индексу 1 возвращается обект типа String
     */
    @Test
    void getSuccess_Test() {
        Assertions.assertEquals("second", customNotEmptyArray.get(1));
    }

    /**
     * Метод {@link CustomArrayImplTest#getFail_Test} проверяет работу метода  {@link CustomArrayImpl#get(int)}.
     * Сценарий проверяет выбрасывание исключения ArrayIndexOutOfBoundsException
     * при обращении за пределы массива.
     */
    @Test
    void getFail_Test() {
        Throwable throwable = Assertions.assertThrows(
                ArrayIndexOutOfBoundsException.class, () -> customNotEmptyArray.get(10));
        Assertions.assertEquals(
                "Индекс 10 отрицательный или находится за пределами массива.", throwable.getMessage());
    }

    /**
     * Метод {@link CustomArrayImplTest#setSuccess_Test} проверяет работу метода {@link CustomArrayImpl#set(int, Object)}.
     * Сценарий проверяет успешную замену объекта массива по индексу и возвращение объекта который был заменен.
     * при обращении в пределах массива.
     */
    @Test
    void setSuccess_Test() {
        Assertions.assertEquals("second", customNotEmptyArray.set(1, "fourth"));
        Assertions.assertEquals("fourth", customNotEmptyArray.get(1));
    }

    /**
     * Метод {@link CustomArrayImplTest#setFail_Test} проверяет работу метода {@link CustomArrayImpl#set(int, Object)}.
     * Сценарий проверяет выбрасывание исключения ArrayIndexOutOfBoundsException
     * при обращении за пределы массива.
     */
    @Test
    void setFail_Test() {
        Throwable throwable = Assertions.assertThrows(
                ArrayIndexOutOfBoundsException.class, () -> customNotEmptyArray.set(10, "tenth"));
        Assertions.assertEquals(
                "Индекс 10 отрицательный или находится за пределами массива.", throwable.getMessage());
    }

    /**
     * Метод {@link CustomArrayImplTest#removeIndexSuccess_Test} проверяет работу метода {@link CustomArrayImpl#remove(int)}.
     * Сценарий проверяет успешное удаление по индексу
     * при обращении в пределах массива.
     */
    @Test
    void removeIndexSuccess_Test() {
        Assertions.assertEquals(6, customNotEmptyArray.size());
        customNotEmptyArray.remove(0);
        Assertions.assertEquals(5, customNotEmptyArray.size());
    }

    /**
     * Метод {@link CustomArrayImplTest#removeIndexSuccess_Test} проверяет работу метода {@link CustomArrayImpl#remove(int)}.
     * Сценарий проверяет выбрасывание исключения ArrayIndexOutOfBoundsException
     * при обращении при удалении за пределы массива.
     */
    @Test
    void removeIndexFail_Test() {
        Throwable throwable = Assertions.assertThrows(
                ArrayIndexOutOfBoundsException.class, () -> customNotEmptyArray.remove(10));
        Assertions.assertEquals(
                "Индекс 10 отрицательный или находится за пределами массива.", throwable.getMessage());
    }

    /**
     * Метод {@link CustomArrayImplTest#removeIndexSuccess_Test} проверяет работу метода {@link CustomArrayImpl#remove(Object)}.
     * Сценарий проверяет успешное удаление по содержанию объекта
     * при наличии объекта в массиве.
     * Метод возвращает  <code>true</code> размер массива уменьшается на 1.
     */
    @Test
    void removeObjectSuccess_Test() {
        Assertions.assertEquals(6, customNotEmptyArray.size());
        Assertions.assertEquals(true, customNotEmptyArray.remove("first"));
        Assertions.assertEquals(5, customNotEmptyArray.size());

    }

    /**
     * Метод {@link CustomArrayImplTest#removeObjectFail_Test} проверяет работу метода {@link CustomArrayImpl#remove(Object)}.
     * Сценарий проверяет не успешное удаление по содержанию объекта
     * при отсутсвии объекта в массиве.
     * Метод возвращает  <code>false</code> размер массива не уменьшается.
     */
    @Test
    void removeObjectFail_Test() {
        Assertions.assertEquals(6, customNotEmptyArray.size());
        Assertions.assertEquals(false, customNotEmptyArray.remove("none"));
        Assertions.assertEquals(6, customNotEmptyArray.size());
    }

    /**
     * Метод {@link CustomArrayImplTest#removeNullSuccess_Test} проверяет работу метода {@link CustomArrayImpl#remove(Object)}.
     * Сценарий проверяет успешное удаление по содержанию объекта
     * при наличии объекта null в пределах массива.
     */
    @Test
    void removeNullSuccess_Test() {
        Assertions.assertEquals(6, customNotEmptyArray.size());
        customNotEmptyArray.remove(null);
        Assertions.assertEquals(5, customNotEmptyArray.size());
    }

    /**
     * Метод {@link CustomArrayImplTest#containsSuccess_Test} проверяет наличие объекта
     * динамичского массива по его содержанию.
     * Сценарий проверяет возвращение <code>true</code> при наличии объекта.
     */
    @Test
    void containsSuccess_Test() {
        Assertions.assertEquals(true, customNotEmptyArray.contains("second"));
    }

    /**
     * Метод {@link CustomArrayImplTest#containsSuccess_Test} проверяет наличие объекта
     * динамичского массива по его содержанию.
     * Сценарий проверяет возвращение <code>false</code> при отсуствии объекта.
     */
    @Test
    void containsFail_Test() {
        Assertions.assertEquals(false, customNotEmptyArray.contains("fourth"));
    }

    /**
     * Метод {@link CustomArrayImplTest#containsSuccess_Test} проверяет наличие объекта
     * динамичского массива по его содержанию.
     * Сценарий проверяет возвращение <code>true</code> при наличии объекта null.
     */
    @Test
    void containsNull_Test() {
        Assertions.assertEquals(true, customNotEmptyArray.contains(null));
    }

    /**
     * Метод {@link CustomArrayImplTest#indexOfSuccess_Test} проверяет вовращение объекта
     * динамичского массива по его индексу.
     * Сценарий проверяет успешное возвращение индекса объекта по запрошеному объекту.
     */
    @Test
    void indexOfSuccess_Test() {
        Assertions.assertEquals(1, customNotEmptyArray.indexOf("second"));
    }

    /**
     * Метод {@link CustomArrayImplTest#indexOfFail_Test} проверяет вовращение объекта
     * динамичского массива по его индексу.
     * Сценарий проверяет не успешное возвращение объекта по запрошеному индексу.
     * Возвращенное занчение должно быть -1.
     */
    @Test
    void indexOfFail_Test() {
        Assertions.assertEquals(-1, customNotEmptyArray.indexOf("fourth"));
    }

    /**
     * Метод {@link CustomArrayImplTest#indexOfNull_Test} проверяет вовращение объекта
     * динамичского массива по его индексу.
     * Сценарий проверяет не успешное возвращение индекса первого встреченного объекта null
     * по запрошеному объекту null.
     * Возвращенное занчение должно быть 1.
     */
    @Test
    void indexOfNull_Test() {
        Assertions.assertEquals(3, customNotEmptyArray.indexOf(null));
    }

    /**
     * Метод {@link CustomArrayImplTest#getCapacityDefault_Test} проверяет изменение размера
     * Сценарий проверяет увеличение вместимости объекта с первначальной вместимостью ноль при
     * добавлении одного объекта новая вместимость равна 1.
     */
    @Test
    void ensureCapacity_Test() {
        CustomArrayImpl<String> customEmptyArray = new CustomArrayImpl<>(0);
        Assertions.assertEquals(0, customEmptyArray.getCapacity());
        customEmptyArray.add("first");
        Assertions.assertEquals(1, customEmptyArray.getCapacity());
    }

    /**
     * Метод {@link CustomArrayImplTest#getCapacityDefault_Test} проверяет величину по умолчанию возвращаемую
     * при запросе вместимости динамического массива.
     * Сценарий проверяет вместимость по умолчанию равную 10.
     */
    @Test
    void getCapacityDefault_Test() {
        Assertions.assertEquals(10, customEmptyArray.getCapacity());
    }

    /**
     * Метод {@link CustomArrayImplTest#getCapacityDefault_Test} проверяет величину возвращаемую
     * при запросе вместимости динамического массива с нулевой величиной.
     * Сценарий проверяет вместимость равную 0.
     */
    @Test
    void getCapacityEmpty_Test() {
        CustomArrayImpl<String> customEmptyArray = new CustomArrayImpl<>(0);
        Assertions.assertEquals(0, customEmptyArray.getCapacity());
    }

    /**
     * Метод {@link CustomArrayImplTest#getCapacityDefault_Test} проверяет величину возвращаемую
     * при запросе вместимости динамического массива с не нулевой величиной.
     * Сценарий проверяет вместимость равную 100.
     */
    @Test
    void getCapacityHundred_Test() {
        CustomArrayImpl<String> customEmptyArray = new CustomArrayImpl<>(100);
        Assertions.assertEquals(100, customEmptyArray.getCapacity());
    }

    /**
     * Метод {@link CustomArrayImplTest#reverse_Test} проверяет метод изменяющий порядок объектов
     * динамического массива на противоположный.
     * Сценарий проверяет отображение не пустого массива в обратном порядке и идентичность
     * отображения данных массивов.
     */
    @Test
    void reverse_Test() {
        customNotEmptyArray.reverse();
        Assertions.assertEquals("[ last null null third second first ]", customNotEmptyArray.toString());
    }

    /**
     * Метод {@link CustomArrayImplTest#reverse_Test} проверяет метод изменяющий порядок объектов
     * динамического массива на противоположный.
     * Сценарий проверяет отображение пустого массива в обратном порядке и идентичность
     * отображения данных массивов.
     */
    @Test
    void reverseEmpty_Test() {
        customEmptyArray.reverse();
        Assertions.assertEquals("[ ]", customEmptyArray.toString());
    }

    /**
     * Метод {@link CustomArrayImplTest#toString_Test} проверяет работу метода  {@link CustomArrayImpl#toString()}.
     * Сценарий проверяет отображение объекта заданного вида - возвращается обект типа String.
     */
    @Test
    void toString_Test() {
        Assertions.assertEquals("[ first second third null null last ]", customNotEmptyArray.toString());
    }

    /**
     * Метод {@link CustomArrayImplTest#toArray_Test} проверяет создание копии массива.
     * Сценарий проверяет идентичность созданной копии и исходного массива по элементно.
     */
    @Test
    void toArray_Test() {
        Object[] copy = customNotEmptyArray.toArray();
        for (int i = 0; i < copy.length; i++) {
            Assertions.assertEquals(customNotEmptyArray.get(i), (String) copy[i]);
        }
    }

}