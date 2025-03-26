package Lesson_11;

abstract class Animal {
    protected String name;
    private static int totalCount = 0;

    public Animal(String name) {
        this.name = name;
        totalCount++;
    }

    public abstract void run(int distance);

    public abstract void swim(int distance);

    public static int getTotalCount() {
        return totalCount;
    }
}
