package lab3.compulsory;

import java.util.Objects;
import java.lang.Comparable;

public class Company implements Node, Comparable<Company>{
    private String name;

    public Company(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        Company company = (Company) o;
        return Objects.equals(getName(), company.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public int compareTo(Company o) {
        /* Comparing by their name
         * Personal mention: the compareTo function below from return is different
         * And it's java function for comparing 2 strings, the reason why it works without a conflict
         * it's because it takes a string as argument.
         *  */
        return this.name.compareTo(o.getName());
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                '}';
    }
}
