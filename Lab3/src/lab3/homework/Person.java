package lab3.compulsory;

import java.util.HashMap;
import java.util.Objects;
import java.lang.Comparable;
import java.util.Date;
import java.util.Map;
public abstract class Person implements Node, Comparable<Person>{
    private String name;
    private Date BirthDate;
    private Map<Person, PTOPRelationship> SocialRelation;
    private Map<Company, PTOCRelationship> ProfessionalRelation;

    public Person(String name, Date BirthDate) {
        this.name = name;
        this.BirthDate = BirthDate;
        this.SocialRelation = new HashMap<>();
        this.ProfessionalRelation = new HashMap<>();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Date getBirthDate() {
        return BirthDate;
    }

    public void setDateBirth(Date BirthDate) {
        this.BirthDate = BirthDate;
    }

    public void addSocialRelation(Person p, PTOPRelationship relationship) {
        this.SocialRelation.put(p, relationship);
        // With the line below we will update the Person's p map too with the same relation
        p.getSocialRelation().put(this, relationship);
    }

    public void addProfessionalRelation(Company c, PTOCRelationship relationship) {
        this.ProfessionalRelation.put(c, relationship);
    }

    public Map getSocialRelation() {
        return this.SocialRelation;
    }

    public Map getProfessionalRelation() {
        return this.ProfessionalRelation;
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
                ", BirthDate=" + BirthDate +
                '}';
    }
}
