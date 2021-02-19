package org.spstu.aleksandrov;

import org.jetbrains.annotations.Nullable;
import java.util.Map;
import java.util.Objects;

interface ListOfProducts {
    Product get(Integer code);
    boolean add(Product product);
    Product delete(int code);
    Integer countPrice(Map<Integer,Integer> ShoppingList);
}

public class PriceList implements ListOfProducts {

    public final static String emptyLstException = "Список пуст.";
    public final static String existsException = "Товара с таким кодом не существует.";
    private final Map<Integer, Product> map;

    public PriceList(Map<Integer, Product> map) {
        this.map = map;
    }

    public boolean add(Product product) {
        if (this.map.containsKey(product.getCode())) return false;
        this.map.put(product.getCode(), product);
        return true;
    }

    @Nullable
    public Product delete(int code) {
        Product result = map.get(code);
        this.map.remove(code);
        return result;
    }

    public Integer countPrice(Map<Integer, Integer> shoppingList) {
        if (shoppingList == null) throw new IllegalArgumentException(emptyLstException);
        int result = 0;
        for (int code : shoppingList.keySet()) {
            if (this.map.get(code) == null)
                throw new IllegalArgumentException(existsException) ;
            result += this.map.get(code).getPrice() * shoppingList.get(code);
        }
        return result;
    }

    @Nullable
    public Product get(Integer code) {
        return this.map.get(code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(map);
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
        int i = 0;
        for (int code : map.keySet()) {
            sb.append(map.get(code).toString());
            if (i != map.keySet().size() - 1) sb.append(System.getProperty("line.separator"));
            i++;
        }
        return sb.toString();
    }
}