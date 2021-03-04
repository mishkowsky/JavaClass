package org.spbstu.aleksandrov;

import org.jetbrains.annotations.Nullable;

interface Products {
    String getName();
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

    private boolean illegalPrice(int priceKop, long priceRub) {
        return priceRub < 0 || priceKop < 0 || priceKop > 99;
    }

    public Product(String name, int code, long priceRub, int priceKop) {
        if (illegalPrice(priceKop, priceRub)) throw new NumberFormatException(PRICE_EXCEPTION);
        if (code < 0) throw new NumberFormatException(CODE_EXCEPTION);
        this.name = name;
        this.code = code;
        this.priceRub = priceRub;
        this.priceKop = priceKop;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public long getPriceRub() {
        return priceRub;
    }

    public int getPriceKop() {
        return priceKop;
    }

    public String getPrice() {
        return "Стоимость: " + priceRub + " рублей " + priceKop + " копеек.";
    }

    public boolean changePrice(int priceRub, int priceKop){
        if (illegalPrice(priceKop, priceRub)) return false;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        if (code != product.getCode()) return false;
        if (priceRub != product.getPriceRub()) return false;
        if (priceKop != product.getPriceKop()) return false;
        return name != null ? name.equals(product.name) : product.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + code;
        result = 31 * result + (int) (priceRub ^ (priceRub >>> 32));
        result = 31 * result + priceKop;
        return result;
    }

    @Override
    public String toString() {
        return "Наименование: " + name + ", код: " + code + ", цена: "
            + priceRub + " рублей " + priceKop + " копеек.";
    }
}