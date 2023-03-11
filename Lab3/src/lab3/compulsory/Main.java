package lab3.compulsory;

import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        Person[] persons = {
                new Person("Ion", Specialization.Programmers),
                new Person("Eric", Specialization.Programmers),
                new Person("Alex", Specialization.Designers),
                new Person("Bogdan", Specialization.Programmers),

        };

        Company[] companies = {
                new Company("Ubisoft"),
                new Company("Continental"),
                new Company("Amazon"),
                new Company("Bit")
        };

        for(int i = 0; i < persons.length; i++) {
            System.out.print(persons[i] + " ");
        }

        System.out.println();

        for(int i = 0; i < companies.length; i++) {
            System.out.print(companies[i] + " ");
        }

        System.out.println();
        System.out.println();

        /* We store in this reference the address of the first person from the array in alphabetical order */
        Person FirstAlphabeticalPerson = persons[0];
        for(int i = 1; i < persons.length; i++) {
            if(FirstAlphabeticalPerson.compareTo(persons[i]) > 0) {
                FirstAlphabeticalPerson = persons[i];
            }
        }
        System.out.println("FirstAlphabeticalOrderPerson: " + FirstAlphabeticalPerson.getName());
        System.out.println();

        /* We store in this reference the address of the first company from the array in alphabetical order */
        Company FirstAlphabeticalCompany = companies[0];
        for(int i = 1; i < companies.length; i++) {
            if(FirstAlphabeticalCompany.compareTo(companies[i]) > 0) {
                FirstAlphabeticalCompany = companies[i];
            }
        }
        System.out.println("FirstAlphabeticalOrderCompany: " + FirstAlphabeticalCompany.getName());

        List<Node> nodes = new ArrayList<>();
        nodes.add(new Person("David", Specialization.Designers));
        nodes.add(new Company("AMD"));
        nodes.add(new Person("Bianca", Specialization.Designers));

        System.out.println();

        for(int i = 0; i < nodes.size(); i++) {
            System.out.print(nodes.get(i).getName() + " ");
        }

        System.out.println();
    }
}