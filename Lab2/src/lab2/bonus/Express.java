package lab2.compulsory;

public class Express extends Road{
    private String type;
    private int SpeedLimit;
    public Express(String name, Location FirstEnd, Location SecondEnd) {
        super(name, FirstEnd, SecondEnd);
        this.type = "Express";
        this.SpeedLimit = 90;
    }
    public String getType(){
        return this.type;
    }
    public void setSpeedLimit(int SpeedLimit) { this.SpeedLimit = SpeedLimit; }
    public int getSpeedLimit() { return this.SpeedLimit; }
    @Override
    public String toString() {
        return "{ RoadType: " + this.type +
                " | Roadlength: " + getLength() +
                " | SpeedLimit: " + this.SpeedLimit +
                " | FirstEnd: " + this.FirstEnd.getName() +
                " (" + this.FirstEnd.getX() + ", " + this.FirstEnd.getY() + ")" +
                " | SecondEnd: " + this.SecondEnd.getName() +
                " (" + this.SecondEnd.getX() + ", " + this.SecondEnd.getY() + ")" +
                " }";
    }
}
