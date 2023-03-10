package lab2.compulsory;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BestRouteProblem check = new BestRouteProblem();

        Location Iasi = new City("Iasi", 350, 800, 300_000);
        check.addLocation(Iasi);

        Iasi.setX(394);
        Iasi.setY(786);
        System.out.println("Iasi info: " + Iasi);

        Location Bucharest = new City("Bucharest", 50, 600, 2_000_000);
        check.addLocation(Bucharest);

        System.out.println("Bucharest info: " + Bucharest);

        Location Cluj = new City("Cluj", 25, 700, 800_000);
        check.addLocation(Cluj);

        System.out.println("Cluj info: " + Cluj);

        /* Easier way to find the euclidean distance between the two locations, by creating a road between the two locations */
        Road MeasureDistance = new Highway("MeasureDistance", Iasi, Bucharest);
        System.out.printf("Distance between " + Iasi.getName() + " and Bucharest: %.2f%n", MeasureDistance.getLength());
        System.out.println("MeasureDistance info: " + MeasureDistance);
        double IasiBucDistance = MeasureDistance.getLength();

        Road MeasureDistance2 = new Highway("MeasureDistance2", Iasi, Cluj);
        System.out.printf("Distance between " + Iasi.getName() + " and Cluj: %.2f%n", MeasureDistance2.getLength());
        System.out.println("MeasureDistance2 info: " + MeasureDistance2);
        double IasiClujDistance = MeasureDistance2.getLength();

        Road MeasureDistance3 = new Highway("MeasureDistance3", Bucharest, Cluj);
        System.out.printf("Distance between " + Bucharest.getName() + " and Cluj: %.2f%n", MeasureDistance3.getLength());
        System.out.println("MeasureDistance3 info: " + MeasureDistance3);
        double BucClujDistance = MeasureDistance3.getLength();

        /* We assume that every start and end location is a GasStation by default */
        Location A30FirstEnd = new GasStation("A30FirstEnd", 460, 810, 7);
        check.addEnd(A30FirstEnd);
        System.out.println("A30FirstEnd info: " + A30FirstEnd);

        Location A30SecondEnd = new GasStation("A30SecondEnd", 40, 590, 7);
        check.addEnd(A30SecondEnd);
        System.out.println("A30SecondEnd info: " + A30SecondEnd);

        Road A30 = new Express("A30", A30FirstEnd, A30SecondEnd);
        check.addRoad(A30);
        System.out.printf("A30 length: %.2f%n", A30.getLength());
        System.out.println("A30 info: " + A30);

        Location A45FirstEnd = new GasStation("A45FirstEnd", 51, 599, 7.50);
        check.addEnd(A45FirstEnd);
        System.out.println("A45FirstEnd info: " + A45FirstEnd);

        Location A45SecondEnd = new GasStation("A45SecondEnd", 24, 701, 7.50);
        check.addEnd(A45SecondEnd);
        System.out.println("A45SecondEnd info: " + A45SecondEnd);

        Road A45 = new Highway("A45", A45FirstEnd, A45SecondEnd);
        check.addRoad(A45);
        System.out.printf("A45 length: %.2f%n", A45.getLength());
        System.out.println("A45 info: " + A45);

        Road A46 = new Country("A46", A45FirstEnd, A45SecondEnd);
        check.addRoad(A46);

        Location A60FirstEnd = new GasStation("A60FirstEnd", 450, 810, 7);
        check.addEnd(A60FirstEnd);
        System.out.println("A60FirstEnd info: " + A60FirstEnd);

        Location A60SecondEnd = new GasStation("A60SecondEnd", 24, 699, 7);
        check.addEnd(A60SecondEnd);
        System.out.println("A60SecondEnd info: " + A60SecondEnd);

        Road A60 = new Express("A60", A60FirstEnd, A60SecondEnd);
        check.addRoad(A60);
        System.out.printf("A60 length: %.2f%n", A60.getLength());
        System.out.println("A60 info: " + A60);

        String[][] LocationsOnRoads = new String[check.getLocationSize()+1][check.getRoadSize()+1];
        LocationsOnRoads[0][0] = null;

        for(int i = 0; i < check.getLocationSize(); i++) {
            LocationsOnRoads[i+1][0] = check.getLocations(i);
        }
        for(int i = 0; i < check.getRoadSize(); i++) {
            LocationsOnRoads[0][i+1] = check.getRoads(i);
        }

        check.collinearityCheck(LocationsOnRoads);

        System.out.println();
        for(int i = 0; i <= check.getLocationSize(); i++) {
            for(int j = 0; j <= check.getRoadSize(); j++) {
                System.out.print(LocationsOnRoads[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        
        check.straightWays(LocationsOnRoads);
    }
}
