package Lesson_6;

import java.util.ArrayList;
import java.util.List;

public class Park {
    private String name;
    private List<Attraction> attractions = new ArrayList<>();

    public Park(String name) {
        this.name = name;
    }

    public void addAttraction(Attraction attraction) {
        attractions.add(attraction);
    }

    public void printAllAttractions() {
        System.out.println("Аттракционы в парке '" + name + "':");
        for (Attraction attraction : attractions) {
            attraction.printAttractionInfo();
            System.out.println("==========================");

        }
    }

    public class Attraction {
        private final String name;
        private final String workingHours;
        private final double price;

        public Attraction(String name, String workingHours, double price) {
            this.name = name;
            this.workingHours = workingHours;
            this.price = price;
        }

        public void printAttractionInfo() {
            System.out.println("Информация об аттракционе: ");
            System.out.println("Название: " + this.name);
            System.out.println("Часы работы: " + this.workingHours);
            System.out.println("Стоимость: " + this.price);
        }
    }
}
