package Lesson_6;

import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {

        // 1. Создаем класс с полями:
        Product product1 = new Product("Геймпад для консоли PS5 Sony DualSense White", LocalDate.of(2023, 11, 15), "Sony", "China", 9999.00, false); //(2023, 11), "Sony", "China",9999.00, false );

        System.out.println("Информация о продукте:");
        product1.printProductInfo();
        System.out.println();

        // 2. Создаем массив из 5 товаров:
        Product[] productsArray = new Product[5];

        productsArray[0] = new Product("Blu-ray привод Sony для консоли PS5", LocalDate.of(2020, 11, 15 ), "Sony", "China", 22999.00, true);
        productsArray[1] = new Product("Наушники True Wireless Sony Explore Earbuds", LocalDate.of(2024, 10, 18), "Sony", "India", 21999.00, false);
        productsArray[2] = new Product("Зарядная станция Artplays Magnetic LED для двух геймпадов DualSense", LocalDate.of(2024, 11, 07), "Sony", "China", 1399.00, true);
        productsArray[3] = new Product("Шлем виртуальной реальности Oculus Quest 3 512GB", LocalDate.of(2024, 02, 14), "Sony", "China", 74999.00, true);
        productsArray[4] = new Product("Аккумулятор GameSpot 9823", LocalDate.of(2025, 01, 15), "Sony", "China", 1187.00, false);

        // 3. Создаем класс Park:
        Park bestPark = new Park("Лучший парк");
        Park.Attraction ferrisWheel = bestPark.new Attraction("Карусель", "12:00 - 18:00", 300);
        Park.Attraction bungee = bestPark.new Attraction("Зеркальная комната", "12:00 - 18:00", 250);
        bestPark.addAttraction(ferrisWheel);
        bestPark.addAttraction(bungee);
        bestPark.printAllAttractions();

    }


}
