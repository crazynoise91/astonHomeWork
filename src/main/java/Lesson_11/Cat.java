package Lesson_11;

public class Cat extends Animal {
    private static int catCount = 0;
    private final int MAX_RUN = 200;
    private boolean isFull;

    public Cat(String name) {
        super(name);
        this.isFull = false;
        catCount++;
    }

    @Override
    public void run(int distance) {
        if (distance <= 0) {
            System.out.println("Расстояние не может быть отрицательным!");
            return;
        }
        if (distance <= MAX_RUN) {
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + " не может пробежать " + distance + " м.");
        }
    }

    @Override
    public void swim(int distance) {
        System.out.println(name + " не умеет плавать!");
    }

    public void eat(Bowl bowl) {
        if (!isFull && bowl.takeFood(10)) {
            isFull = true;
            System.out.println(name + " поел и теперь сыт!");
        } else if (isFull) {
            System.out.println(name + " уже сыт!");
        } else {
            System.out.println(name + " не смог поесть, в миске мало еды!");
        }
    }

    public boolean isFull() {
        return isFull;
    }

    public static int getCatCount() {
        return catCount;
    }
}
