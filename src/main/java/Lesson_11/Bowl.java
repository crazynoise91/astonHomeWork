package Lesson_11;

public class Bowl {
    private int food;

    public Bowl(int initialFood) {
        this.food = Math.max(initialFood, 0);
    }

    public boolean takeFood(int amount) {
        if (amount <= 0) {
            System.out.println("Количество еды должно быть положительным!");
            return false;
        }
        if (food >= amount) {
            food -= amount;
            return true;
        }
        return false;
    }

    public void addFood(int amount) {
        if (amount > 0) {
            food += amount;
            System.out.println("Добавлено " + amount + " еды. Теперь в миске: " + food);
        } else {
            System.out.println("Нельзя добавить отрицательное количество еды!");
        }
    }

    public int getFood() {
        return food;
    }
}
