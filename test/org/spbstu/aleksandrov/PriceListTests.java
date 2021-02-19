package org.spbstu.aleksandrov;

import org.junit.jupiter.api.Test;
import org.spstu.aleksandrov.PriceList;
import org.spstu.aleksandrov.Product;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class PriceListTest {

    Product product = new Product("Сыр", 20, 40000);

    PriceList map = new PriceList(new HashMap<>(Map.of(
            21, new Product("Масло", 21, 11990),
            22, new Product("Колбаса", 22, 22050))));

    @Test
    void add() {
        assertFalse(map.add(new Product("Арбуз", 21, 12490)));
        assertTrue(map.add(product));
     }

    @Test
    void changePrice() {
        assertTrue(product.changePrice(10000));
        assertFalse(product.changePrice(-100));
    }

    @Test
    void changeName() {
        assertEquals("Сыр", product.changeName("Арбуз"));
        assertNull(new Product(null, 25, 12400).changeName("Арбуз"));
    }

    @Test
    void delete() {
        assertEquals(new Product("Масло", 21, 11990), map.delete(21));
        map.delete(21);
        assertEquals(map, new PriceList(Map.of(22, new Product("Колбаса", 22, 22050))));
        assertNull(map.delete(1));
    }

    @Test
    void countPrice() {
        assertEquals(90130, map.countPrice(Map.of(21, 2, 22, 3)));
        assertEquals(0, map.countPrice(Map.of()));
        assertThrows(IllegalArgumentException.class, () -> map.countPrice(null));
    }

    @Test
    void getters() {
        assertEquals(new Product("Колбаса", 22, 22050), map.get(22));
        assertNull(map.get(0));
        assertEquals(40000, product.getPrice());
        assertEquals(20, product.getCode());
    }

    @Test
    void turnToString() {
        assertEquals("Наименование: Сыр, код: 20, цена: 400 рублей 0 копеек.", product.toString());
        assertEquals("Наименование: Масло, код: 21, цена: 119 рублей 90 копеек."
                + System.getProperty("line.separator") +
                "Наименование: Колбаса, код: 22, цена: 220 рублей 50 копеек.", map.toString());
    }

    @Test
    void equals() {
        assertEquals(new Product("Колбаса", 24, 22050),
                new Product("Колбаса", 24, 22050));
        assertNotEquals(new Product("олбаса", 24, 22050),
                new Product("Колбаса", 24, 22050));
    }
}