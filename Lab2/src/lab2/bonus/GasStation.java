package lab2.compulsory;

public class GasStation extends Location {
    private double GasPrice;

    public GasStation(String name, double x, double y, double GasPrice) {
        super(name, x, y);
        this.type = "GasStation";
        this.GasPrice = GasPrice;
    }

    public void setGasPrice(int GasPrice) {
        this.GasPrice = GasPrice;
    }

    public double getGasPrice() {
        return this.GasPrice;
    }


    @Override
    public String toString() {
        return "{ LocationName: " + this.getName() +
                " | LocationType: GasStation" +
                " | X: " + this.getX() +
                " | Y: " + this.getY() +
                " | GasPrice: " + this.GasPrice +
                " }";
    }
}
