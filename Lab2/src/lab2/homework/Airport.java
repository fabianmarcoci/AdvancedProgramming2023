package lab2.compulsory;

public class Airport extends Location{
    private int NoOfTerminals;
    public Airport(String name, double x, double y, int NoOfTerminals) {
        super(name, x, y);
        this.type = "Airport";
        this.NoOfTerminals = NoOfTerminals;
    }
   public void setNoOfTerminals(int NoOfTerminals){ this.NoOfTerminals = NoOfTerminals; }
   public int getNoOfTerminals(){ return this.NoOfTerminals; }
    @Override
    public String toString(){
        return "{ LocationName: " + this.getName() +
                " | LocationType: Airport" +
                " | X: " + this.getX() +
                " | Y: " + this.getY() +
                " | NoOfTerminals: " + this.NoOfTerminals +
                " }";
    }
}
