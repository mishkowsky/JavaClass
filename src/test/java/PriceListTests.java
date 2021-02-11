import org.junit.jupiter.api.Test;


import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class PriceListTest {

    PriceList map = new PriceList(new HashMap<>(Map.of(
            23, new Product("Сыр", 23, 11990),
            24, new Product("Колбаса", 24, 22050))));

    @Test
    void add() {
        map.add(new Product("Варенье", 25, 6970));
        assertEquals(map, new PriceList(new HashMap<>(Map.of(
                23, new Product("Сыр", 23, 11990),
                24, new Product("Колбаса", 24, 22050),
                25, new Product("Варенье", 25, 6970)))));

        assertThrows(IllegalArgumentException.class, () -> map.add(new Product("Арбуз", 23, 7490)));
    }

    @Test
    void changePrice() {
        Product result = new Product("Колбаса", 24, 12390);
        map.get(24).changePrice(12390);
        assertEquals(result, map.get(24));

        Product result2 = new Product("Колбаса", 24, 9901);
        map.get(24).changePrice(9901);
        assertEquals(result2, map.get(24));

        assertThrows(NumberFormatException.class, () -> map.get(24).changePrice(-100));
    }

    @Test
    void changeName() {
        Product result = new Product("Молоко", 24, 22050);
        map.get(24).changeName("Молоко");
        assertEquals(result, map.get(24));

        Product result2 = new Product("Морковь", 24, 22050);
        map.get(24).changeName("Морковь");
        assertEquals(result2, map.get(24));
    }

    @Test
    void delete() {
        map.delete(23);
        assertEquals(map, new PriceList(new HashMap<>(Map.of(
                24, new Product("Колбаса", 24, 22050)))));
        assertThrows(IllegalArgumentException.class, () -> map.delete(1));
    }

    @Test
    void countPrice() {
        assertEquals(90130, map.countPrice(Map.of(23, 2, 24, 3)));
        assertEquals(0, map.countPrice(Map.of()));
        //assertThrows(IllegalArgumentException.class, () -> map.countPrice(Map.of()));
    }

    @Test
    void get() {
        assertEquals(new Product("Колбаса", 24, 22050), map.get(24));
        assertThrows(IllegalArgumentException.class, () -> map.get(0));
    }
}