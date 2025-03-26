package Lesson_11;

public class Dog extends Animal {
    private static int dogCount = 0;
    private final int MAX_RUN = 500;
    private final int MAX_SWIM = 10;

    public Dog(String name) {
        super(name);
        dogCount++;
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
        if (distance <= 0) {
            System.out.println("Расстояние не может быть отрицательным!");
            return;
        }
        if (distance <= MAX_SWIM) {
            System.out.println(name + " проплыл " + distance + " м.");
        } else {
            System.out.println(name + " не может проплыть " + distance + " м.");
        }
    }

    public static int getDogCount() {
        return dogCount;
    }
}
