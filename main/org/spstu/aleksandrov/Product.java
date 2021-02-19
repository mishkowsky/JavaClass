package org.spstu.aleksandrov;

import org.jetbrains.annotations.Nullable;

import java.util.Objects;

interface Products {
    int getCode();
    int getPrice();
    boolean changePrice(int price);
    String changeName(String name);
}

public class Product implements Products {

    public final static String priceException = "Неверное значение цены.";
    public final static String codeException = "Неверное значение кода.";
    private String name;
    private final int code;
    private int price;

    public Product(String name, int code, int price) {
        if (price < 0) throw new NumberFormatException(priceException);
        if (code < 0) throw new NumberFormatException(codeException);
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

    public boolean changePrice(int price){
        if (price < 0) return false;
        this.price = price;
        return true;
    }

    @Nullable
    public String changeName(String name){
        String result = this.name;
        this.name = name;
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, getCode(), getPrice());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Product)) return false;
        Product other = (Product) obj;
        return name.equals(other.name) &&
                code == other.code &&
                price == other.price;
    }

    @Override
    public String toString() {
        return "Наименование: " + name + ", код: " + code + ", цена: "
            + price / 100 + " рублей " + price % 100 + " копеек.";
    }
}