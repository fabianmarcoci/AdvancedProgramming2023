package lab2.compulsory;

public class Main {
    public static void main(String[] args) {
        Location Iasi = new Location("Iasi", LocationType.City, 350, 800);
        Iasi.setX(450);
        System.out.println("Iasi info: " + Iasi);
        Location Bucharest = new Location("Bucharest", LocationType.City, 50, 600);
        System.out.println("Bucharest info: " + Bucharest);
        /* Easier way to find the euclidean distance between the two locations */
        Road MeasureDistance = new Road(Iasi, Bucharest);
        System.out.printf("Distance between " + Iasi.getName() + " and Bucharest: %.2f%n", MeasureDistance.getLength());
        System.out.println("MeasureDistance info: " + MeasureDistance);

        Location A30FirstEnd = new Location(275, 700);
        System.out.println("A30FirstEnd info: " + A30FirstEnd);
        Location A30SecondEnd = new Location (-25, 1125);
        Road A30 = new Road(RoadType.Express, A30FirstEnd, A30SecondEnd, 70);
        System.out.printf("A30 length: %.2f%n", A30.getLength());

        Location A45FirstEnd = new Location(730, 865);
        Location A45SecondEnd = new Location (450, 675);
        Road A45 = new Road(RoadType.Highway, A45FirstEnd, A45SecondEnd, 90);
        System.out.printf("A45 length: %.2f%n", A45.getLength());
        System.out.println("A45 info: " + A45);
    }
}