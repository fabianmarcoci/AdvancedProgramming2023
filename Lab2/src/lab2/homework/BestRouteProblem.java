package lab2.compulsory;

import java.util.ArrayList;
import java.util.List;

public class BestRouteProblem {
    private List<Location> locations = new ArrayList<>();
    private List<Road> roads = new ArrayList<>();
    private List<Location> ends = new ArrayList<>();
    public void addLocation(Location location) {
        if (locations.contains(location)) {
            System.out.println("Location " + location + " already exists!");
        } else {
            locations.add(location);
        }
    }
    public void addEnd(Location end) {
        if (ends.contains(end)) {
            System.out.println("End " + end + " already exists!");
        } else {
            ends.add(end);
        }
    }
    public void addRoad(Road road) {
        if (roads.contains(road)) {
            System.out.println("Road " + road + " already exists!");
        } else {
            roads.add(road);
        }
    }

    public String getLocations(int i) {
        return locations.get(i).getName();
    }

    public String getRoads(int i) {
        return roads.get(i).getName();
    }

    public int getLocationSize() {
        return locations.size();
    }

    public int getRoadSize() {
        return roads.size();
    }
    /*  To check if you can go from a location to another with the given roads
    *   I will do a collinearity test, where I check whether the location is on a road,
    *   I create a matrix where the columns represent the roads and the rows represent the locations,
    *   If the location is situated on a road I fill the matrix space with 'match' else it remains null.
    * */

    public void collinearityCheck(String Matrix[][]) {
        for (int i = 0; i < locations.size(); i++) {
            /* We jump from j to j + 2 because at j is FirstEnd of a Road and at j+1 is SecondEnd of the same Road*/
            for(int j = 0; j < ends.size(); j+=2) {
                Road FirstEndToLocation = new Highway("FirstEndToLocation", ends.get(j), locations.get(i));
                Road SecondEndToLocation = new Highway("SecondEndToLocation", locations.get(i), ends.get(j + 1));
                if((int)roads.get(j / 2).getLength() == (int)FirstEndToLocation.getLength() + (int)SecondEndToLocation.getLength()) {
                    /* That means the Location is located on the road and we can go through it */
                    Matrix[i + 1][(j / 2) + 1] = "true";
                }
            }
        }
    }

    public void straightWays(String Matrix[][]) {
        for (int i = 1; i <= roads.size(); i++) {
            System.out.print("Possible ways on the road " + Matrix[0][i] + ": ");
            for(int j = 1; j <= locations.size(); j++) {
                if(Matrix[i][j] == "true") {
                    System.out.print(Matrix[j][0] + " ");
                }
            }
            System.out.println();
        }
    }
}
