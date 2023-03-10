package lab2.compulsory;

public class Country extends Road{
    private String type;
    private int SpeedLimit;
    public Country(String name, Location FirstEnd, Location SecondEnd) {
        super(name, FirstEnd, SecondEnd);
        this.type = "Country";
        this.SpeedLimit = 70;
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
