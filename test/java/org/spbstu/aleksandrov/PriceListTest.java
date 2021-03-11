package org.spbstu.aleksandrov;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PriceListTest {

    Product product = new Product("Сыр", 20, 400, 0);

    PriceList list = new PriceList(List.of(
            new Product("Масло", 21, 119, 90),
            new Product("Колбаса", 22, 220, 50)));

    @Test
    void add() {
        assertFalse(list.add(new Product("Арбуз", 21, 124, 90)));
        assertTrue(list.add(product));
    }

    @Test
    void changePrice() {
        assertTrue(product.changePrice(100, 0));
        assertFalse(product.changePrice(-100, 0));
        assertFalse(product.changePrice(100, 100));
        assertFalse(product.changePrice(-100, 100));

    }

    @Test
    void changeName() {
        assertEquals("Сыр", product.changeName("Арбуз"));
        assertNull(new Product(null, 25, 124, 0).changeName("Арбуз"));
    }

    @Test
    void delete() {
        assertEquals(new Product("Масло", 21, 119, 90), list.delete(21));
        assertEquals(list, new PriceList(List.of(new Product("Колбаса", 22, 220, 50))));
        assertNull(list.delete(1));
    }

    @Test
    void countPrice() {
        assertEquals("Стоимость списка покупок: 901 рублей 30 копеек.",
                list.countPrice(Map.of(21, 2, 22, 3)));
        assertEquals("Стоимость списка покупок: 0 рублей 0 копеек.",
                list.countPrice(Map.of()));
        assertThrows(IllegalArgumentException.class, () -> list.countPrice(null));
        assertThrows(IllegalArgumentException.class, () -> list.countPrice(Map.of(21, 2, 22, -3)));
        assertThrows(IllegalArgumentException.class, () -> list.countPrice(Map.of(0, 2, 22, 3)));
    }

    @Test
    void getters() {
        assertEquals(new Product("Колбаса", 22, 220, 50), list.get(22));
        assertNull(list.get(0));
        assertEquals("Сыр", product.getName());
        assertEquals(400, product.getPriceRub());
        assertEquals(0, product.getPriceKop());
        assertEquals("Стоимость: 400 рублей 0 копеек.", product.getPrice());
        assertEquals(20, product.getCode());
    }

    @Test
    void turnToString() {
        assertEquals("Наименование: Сыр, код: 20, цена: 400 рублей 0 копеек.", product.toString());
        assertEquals("Наименование: Масло, код: 21, цена: 119 рублей 90 копеек."
                + System.getProperty("line.separator") +
                "Наименование: Колбаса, код: 22, цена: 220 рублей 50 копеек.", list.toString());
    }

    @Test
    void equals() {
        assertEquals(new Product("Колбаса", 0, 165, 50),
                new Product("Колбаса", 0, 165,50));
        assertNotEquals(new Product("олбаса", 1, 192, 99),
                new Product("Колбаса", 1, 192, 99));
        assertEquals(new Product(null, 2, 20, 0),
                new Product(null, 2, 20,0));
    }
}