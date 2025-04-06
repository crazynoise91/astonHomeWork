package Lesson_6;

import java.time.LocalDate;

public class Product {

    private final String name;
    private final LocalDate productionDate;
    private final String manufacturer;
    private final String countryOfOrigin;
    private final double price;
    private final boolean bookingStatus;

    public Product(String name, LocalDate productionDate, String manufacturer, String countryOfOrigin, double price, boolean bookingStatus) {
        this.name = name;
        this.productionDate = productionDate;
        this.manufacturer = manufacturer;
        this.countryOfOrigin = countryOfOrigin;
        this.price = price;
        this.bookingStatus = bookingStatus;
    }

    public void printProductInfo() {
        System.out.println("Название: " + this.name);
        System.out.println("Дата производства: " + this.productionDate);
        System.out.println("Производитель: " + this.manufacturer);
        System.out.println("Страна происхождения: " + this.countryOfOrigin);
        System.out.println("Цена: " + this.price);
        System.out.println("Состояние бронирования покупателем: " + (this.bookingStatus ? "Забронирован" : "Доступен"));
    }
}
