package lab2.compulsory;

import java.util.Objects;

public abstract class Location {
    private String name;
    protected String type;
    private double x, y;
    public Location(double x, double y){
        this.x = x;
        this.y = y;
        this.name = "Unknown";
        this.type = "Vague";
    }
    public Location(String name, double x, double y){
        this.name = name;
        this.x = x;
        this.y = y;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public String getType(){
        return this.type;
    }
    public void setX(double x){
        this.x = x;
    }
    public double getX(){
        return this.x;
    }
    public void setY(double y){
        this.y = y;
    }
    public double getY(){
        return this.y;
    }
    /* 'equals' compares only the coordinates
    *   because there cannot be two locations with identical x and y,
    *   but there can be two locations with the same name and type
    * */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return Double.compare(location.getX(), getX()) == 0 &&
                Double.compare(location.getY(), getY()) == 0;
    }
    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }
}
