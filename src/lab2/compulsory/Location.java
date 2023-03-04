package lab2.compulsory;

public class Location {
    private String name;
    private LocationType type;
    private double x, y;
    public Location(double x, double y){
        this.x = x;
        this.y = y;
        this.name = "Unknown";
        this.type = LocationType.Vague;
    }
    public Location(String name, LocationType type, double x, double y){
        this.name = name;
        this.type = type;
        this.x = x;
        this.y = y;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    public void setType(LocationType type){
        this.type = type;
    }

    public LocationType getType(){
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
    @Override
    public String toString(){
        return "{ LocationName: " + this.name +
                " | LocationType: " + this.type +
                " | X: " + this.x +
                " | Y: " + this.y +
                " }";
    }
}
