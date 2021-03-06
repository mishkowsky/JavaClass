package org.spbstu.aleksandrov;

import org.jetbrains.annotations.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface ListOfProducts {
    Product get(Integer code);
    boolean add(Product product);
    Product delete(int code);
    String countPrice(Map<Integer,Integer> ShoppingList);
}

public class PriceList implements ListOfProducts {

    public final static String EMPTY_LIST_EXCEPTION = "Список пуст.";
    public final static String EXISTS_EXCEPTION = "Товара с таким кодом не существует.";
    public final static String NON_UNIQUE_CODE_EXCEPTION = "Коды товаров должны быть уникальными.";
    public final static String AMOUNT_EXCEPTION = "Количество товаров не должно быть отрицательным.";

    private final Map<Integer, Product> map = new HashMap<>();

    public PriceList(List<Product> list) {
        for (Product product : list) {
            int code = product.getCode();
            if (map.get(code) != null) throw new IllegalArgumentException(NON_UNIQUE_CODE_EXCEPTION);
            this.map.put(code, product);
        }
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

    public String countPrice(Map<Integer, Integer> shoppingList) {
        if (shoppingList == null) throw new IllegalArgumentException(EMPTY_LIST_EXCEPTION);
        int resultRub = 0;
        int resultKop = 0;
        for (int code : shoppingList.keySet()) {
            if (shoppingList.get(code) < 0) throw new IllegalArgumentException(AMOUNT_EXCEPTION);
            if (this.map.get(code) == null) throw new IllegalArgumentException(EXISTS_EXCEPTION);
            resultRub += this.map.get(code).getPriceRub() * shoppingList.get(code);
            resultKop += this.map.get(code).getPriceKop() * shoppingList.get(code);
            resultRub += resultKop / 100;
            resultKop %= 100;
        }
        return "Стоимость списка покупок: " + resultRub + " рублей " + resultKop + " копеек.";
    }

    @Nullable
    public Product get(Integer code) {
        return this.map.get(code);
    }

    @Override
    public int hashCode() {
        int result = 0;
        for (Product product : map.values()){
            result = result * 31 + product.hashCode();
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
        int i = 0;
        for (int code : map.keySet()) {
            sb.append(map.get(code).toString());
            if (i != map.keySet().size() - 1) sb.append(System.getProperty("line.separator"));
            i++;
        }
        return sb.toString();
    }
}