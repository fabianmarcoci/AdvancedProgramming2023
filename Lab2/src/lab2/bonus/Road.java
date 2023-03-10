package lab2.compulsory;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static java.lang.Math.sqrt;

public abstract class Road {
    private String name;
    private double length;
    protected Location FirstEnd;
    protected Location SecondEnd;
    public Road(String name, Location FirstEnd, Location SecondEnd){
        this.name = name;
        this.length = CalculateLength(FirstEnd, SecondEnd);
        this.FirstEnd = FirstEnd;
        this.SecondEnd = SecondEnd;
    }
    public double CalculateLength(@NotNull Location L1, @NotNull Location L2){
        double distanceX, distanceY;
        distanceX = (L1.getX() - L2.getX());
        distanceX *= distanceX;
        distanceY = (L1.getY() - L2.getY());
        distanceY *= distanceY;

        return sqrt(distanceX + distanceY);
    }
    public String getName() {
        return this.name;
    }
    public double getLength() { return this.length; }

    /* It only compares if two roads have the same ends */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Road)) return false;
        Road road = (Road) o;
        return FirstEnd.equals(road.FirstEnd) && SecondEnd.equals(road.SecondEnd);
    }
    @Override
    public int hashCode() {
        return Objects.hash(FirstEnd, SecondEnd);
    }
}
