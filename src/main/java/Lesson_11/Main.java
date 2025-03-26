package Lesson_11;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // классы Собака и кот с наследованием от класса Животное.
        Dog dog1 = new Dog("Бим");
        Dog dog2 = new Dog("Чарли");
        Cat cat1 = new Cat("Граф");
        Cat cat2 = new Cat("Пушок");
        Cat cat3 = new Cat("Чита");

        // Проверка действий
        System.out.println("\nПроверка ограничений бега");
        dog1.run(-1);
        dog2.run(501);
        cat1.run(100);
        cat2.run(200);
        cat3.run(201);

        System.out.println("\nПроверка ограничений плавания");
        dog1.swim(10);
        dog2.swim(11);
        cat1.swim(0);
        dog1.swim(-1);

        // Миска
        System.out.println("\nКормление котов:");
        Bowl bowl = new Bowl(13);
        ArrayList<Cat> cats = new ArrayList<>();
        cats.add(cat1);
        cats.add(cat2);
        cats.add(cat3);

        System.out.println("\nПервое кормление:");
        for (Cat cat : cats) {
            cat.eat(bowl);
        }

        System.out.println("\nДобавление еды");
        bowl.addFood(20);
        bowl.addFood(-1);

        System.out.println("\nВторое кормление:");
        for (Cat cat : cats) {
            if (!cat.isFull()) {
                cat.eat(bowl);
            }
        }

        // Сытость
        System.out.println("\nПроверка сытости:");
        for (Cat cat : cats) {
            System.out.println(cat.name + ": " + (cat.isFull() ? "сыт" : "голоден"));
        }

        // Количество животных
        System.out.println("Всего животных: " + Animal.getTotalCount());
        System.out.println("Собак: " + Dog.getDogCount());
        System.out.println("Котов: " + Cat.getCatCount());
        System.out.println("Остаток еды в миске: " + bowl.getFood());

        // Расчет периметра и площади геометрических фигур.
        GeometricShape circle = new Circle(3, "Красный", "Синий");
        GeometricShape rectangle = new Rectangle(10, 5, "Желтый", "Зеленый");
        GeometricShape triangle = new Triangle(7, 7, 7, "Черный", "Оранжевый");

        System.out.println("\nКруг");
        circle.printInfo();

        System.out.println("\nПрямоугольник");
        rectangle.printInfo();

        System.out.println("\nТреугольник");
        triangle.printInfo();
    }
}