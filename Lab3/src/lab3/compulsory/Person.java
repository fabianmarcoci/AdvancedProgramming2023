package lab3.compulsory;

import java.util.Objects;
import java.lang.Comparable;
public class Person implements Node, Comparable<Person> {
    private String name;

    private Specialization skill;

    public Person(String name, Specialization skill) {
        this.name = name;
        this.skill = skill;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Specialization getSkill() {
        return skill;
    }

    public void setSkill(Specialization skill) {
        this.skill = skill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(getName(), person.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public int compareTo(Person o) {
        /* Comparing by their name
        * Personal mention: the compareTo function below from return is different
        * And it's java function for comparing 2 strings, the reason why it works without a conflict
        * it's because it takes a string as argument.
        *  */
        return this.name.compareTo(o.getName());
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", skill=" + skill +
                '}';
    }
}
