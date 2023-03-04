package lab2.compulsory;

import static java.lang.Math.sqrt;

public class Road {
    private RoadType type;
    private double length;
    private int SpeedLimit;
    private Location FirstEnd, SecondEnd;
    public Road(Location FirstEnd, Location SecondEnd){
        this.type = RoadType.EuclideanDistance;
        this.SpeedLimit = 50;
        this.length = CalculateLength(FirstEnd, SecondEnd);
        this.FirstEnd = FirstEnd;
        this.SecondEnd = SecondEnd;
    }
    public Road(RoadType type, Location FirstEnd, Location SecondEnd, int SpeedLimit){
        this.type = type;
        this.length = CalculateLength(FirstEnd, SecondEnd);
        this.SpeedLimit = SpeedLimit;
        this.FirstEnd = FirstEnd;
        this.SecondEnd = SecondEnd;
    }
    public double CalculateLength(Location L1, Location L2){
        double distanceX, distanceY;
        distanceX = (L1.getX() - L2.getX());
        distanceX *= distanceX;
        distanceY = (L1.getY() - L2.getY());
        distanceY *= distanceY;

        return sqrt(distanceX + distanceY);
    }
    public void setType(RoadType type){
        this.type = type;
    }
    public RoadType getType(){
        return this.type;
    }
    public void setLength(Location FirstEnd, Location SecondEnd){
        this.length = CalculateLength(FirstEnd, SecondEnd);
    }
    public double getLength() { return this.length; }
    public void setSpeedLimit(int SpeedLimit) { this.SpeedLimit = SpeedLimit; }
    public int getSpeedLimit() { return this.SpeedLimit; }
    @Override
    public String toString(){
        return "{ RoadType: " + this.type +
                " | Roadlength: " + this.length +
                " | SpeedLimit: " + this.SpeedLimit +
                " | FirstEnd: " + this.FirstEnd.getName() +
                " | SecondEnd: " + this.SecondEnd.getName() +
                " }";
    }
}
