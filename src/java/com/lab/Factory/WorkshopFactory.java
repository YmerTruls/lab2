package src.java.com.lab.Factory;

import src.java.com.lab.lab1.*;



public class WorkshopFactory {

        public static Workshop createWorkshop(String type, double x, double y) {
            return switch (type) {
                case "VolvoWorkshop" -> new VolvoWorkshop(x, y);
                default -> throw new IllegalArgumentException("Unknown vehicle type");
            };
        }
}
