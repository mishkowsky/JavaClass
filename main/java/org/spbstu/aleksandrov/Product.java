package org.spbstu.aleksandrov;

import org.jetbrains.annotations.Nullable;

interface Products {
    int getCode();
    long getPriceRub();
    int getPriceKop();
    boolean changePrice(int priceRub, int priceKop);
    String changeName(String name);
}

public class Product implements Products {

    public final static String PRICE_EXCEPTION = "Неверное значение цены.";
    public final static String CODE_EXCEPTION = "Неверное значение кода.";
    private String name;
    private final int code;
    private long priceRub;
    private int priceKop;

    public Product(String name, int code, long priceRub, int priceKop) {
        if (priceRub < 0 || priceKop < 0 || priceKop > 99) throw new NumberFormatException(PRICE_EXCEPTION);
        if (code < 0) throw new NumberFormatException(CODE_EXCEPTION);
        this.name = name;
        this.code = code;
        this.priceRub = priceRub;
        this.priceKop = priceKop;
    }

    public int getCode() {
        return code;
    }

    public long getPriceRub() {
        return priceRub;
    }

    public String getPrice() {
        return "Стоимость: " + priceRub + " рублей " + priceKop + " копеек.";
    }

    public int getPriceKop() {
        return priceKop;
    }

    public boolean changePrice(int priceRub, int priceKop){
        if (priceRub < 0 || priceKop < 0 || priceKop > 99) return false;
        this.priceRub = priceRub;
        this.priceKop = priceKop;
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
        int result = code;
        result = result * 31 + Long.hashCode(priceRub);
        result = result * 31 + priceKop;
        result = result * 31 + name.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Product)) return false;
        Product other = (Product) obj;
        return name.equals(other.name) &&
                code == other.code &&
                priceRub == other.priceRub &&
                priceKop == other.priceKop;
    }

    @Override
    public String toString() {
        return "Наименование: " + name + ", код: " + code + ", цена: "
            + priceRub + " рублей " + priceKop + " копеек.";
    }
}