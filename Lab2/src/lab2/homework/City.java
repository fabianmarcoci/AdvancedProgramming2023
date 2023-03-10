package lab2.compulsory;

public class City extends Location {
    private int PopulationSize;
    public City(String name, double x, double y, int PopulationSize) {
        super(name, x, y);
        this.type = "City";
        this.PopulationSize = PopulationSize;
    }

    public void setPopulationSize(int PopulationSize) {
        this.PopulationSize = PopulationSize;
    }

    public int getPopulationSize() {
        return this.PopulationSize;
    }

    @Override
    public String toString(){
        return "{ LocationName: " + this.getName() +
                " | LocationType: City" +
                " | X: " + this.getX() +
                " | Y: " + this.getY() +
                " | PopulationSize: " + this.PopulationSize +
                " }";
    }
}
