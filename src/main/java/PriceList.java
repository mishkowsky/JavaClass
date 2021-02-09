/*
Вариант 11 -- прейскурант [Java]
Хранит списки товаров и их цен, причём у каждого товара есть имя и целочисленный код, а цена представляется в рублях и копейках.
Методы: добавление товара и его цены,
        изменение цены товара,
        изменение имени товара,
        удаление товара,
        определение цены покупки по коду и количеству экземпляров.
*/

import java.util.HashMap;
import java.util.Map;

class Product {

    private String name;
    private int code;
    private int price;

    {
        name = "Undefined";
        code = 0;
        price = 0;
    }

    Product() {

    }

    Product(String name, int code) {
        this.name = name;
        this.code = code;
    }

    Product(String name, int code, int price) {
        if (price < 0) throw new NumberFormatException("Неверное значение цены.");
        this.name = name;
        this.code = code;
        this.price = price;
    }

    public int getCode() {
        return code;
    }

    public int getPrice() {
        return price;
    }

    void changePrice(int price){
        if (price < 0) throw new NumberFormatException("Неверное значение цены.");
        this.price = price;
    }

    void changeName(String name){
        this.name = name;
    }

    @Override
    public int hashCode() {
        int result = price;
        result = 31 * result + code;
        return result;
    }

    @Override
    public String toString() {
        return "Наименование: " + name + ", код: " + code + ", цена: "
                + price / 100 + " рублей " + price % 100 + " копеек.";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Product) {
            Product other = (Product) obj;
            return this.name.equals(other.name) && this.code == other.code && this.price == other.price;
        }
        return false;
    }
}

class PriceList {

    private final HashMap<Integer, Product> map;

    PriceList(HashMap<Integer, Product> map) {
        this.map = map;
    }

    public void add(Product product) {
        if (this.map.containsKey(product.getCode()))
            throw new IllegalArgumentException("Товар с таким кодом уже существует");
        this.map.put(product.getCode(), product);
    }

    public void delete(int code) {
        if (!this.map.containsKey(code)) throw new IllegalArgumentException("Товара с таким кодом не существует");
        this.map.remove(code);
    }

    public Integer countPrice(Map<Integer,Integer> ShoppingList) {
        //if (ShoppingList.isEmpty()) throw new IllegalArgumentException("Пустой список.");
        int result = 0;
        for (int code : ShoppingList.keySet()) {
            result += this.map.get(code).getPrice() * ShoppingList.get(code);
        }
        return result;
    }

    public Product get(Integer code) {
        if (!map.containsKey(code)) throw new IllegalArgumentException("Товара с таким кодом не существует.");
        return this.map.get(code);
    }

    @Override
    public int hashCode() {
        int result = 0;
        for (int code : map.keySet()) {
            result = code * 31 + map.get(code).getPrice();
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof PriceList) {
            PriceList other = (PriceList) obj;
            return (this.map.equals(other.map));
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int code : map.keySet()) {
            sb.append(map.get(code).toString());
            sb.append("\n");
        }
        return sb.toString();
    }

}

