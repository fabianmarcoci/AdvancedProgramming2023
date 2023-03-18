package lab3.compulsory;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class Main {
    public static void main(String[] args) {
        // Create Date objects for the birthdates
        Date IonBirthDate = new Date(100, 4, 24); // May 24th, 2000
        Date EricBirthDate = new Date(98, 9, 13); // October 13th, 1998
        Date AlexBirthDate = new Date(96, 6, 6); // July 6th, 1996
        Date BogdanBirthDate = new Date(99, 2, 28); // March 28th, 1999

        // Create an array of Person objects
        Person[] persons = {
                new Programmer("Ion", IonBirthDate, "Java"),
                new Programmer("Eric", EricBirthDate, "C++"),
                new Designer("Alex", AlexBirthDate, "Photoshop"),
                new Programmer("Bogdan", BogdanBirthDate, "Python")
        };

        // Create an array of Company objects
        Company[] companies = {
                new Company("Ubisoft", 22_000),
                new Company("Continental", 1350),
                new Company("Amazon", 55_000),
                new Company("Bit", 14_000)
        };

        for (int i = 0; i < persons.length; i++) {
            System.out.println(persons[i]);
        }

        System.out.println();

        for (int i = 0; i < companies.length; i++) {
            System.out.println(companies[i]);
        }

        System.out.println();
        System.out.println();

        /* We store in this reference the address of the first person from the array in alphabetical order */
        Person FirstAlphabeticalPerson = persons[0];
        for (int i = 1; i < persons.length; i++) {
            if (FirstAlphabeticalPerson.compareTo(persons[i]) > 0) {
                FirstAlphabeticalPerson = persons[i];
            }
        }
        System.out.println("FirstAlphabeticalOrderPerson: " + FirstAlphabeticalPerson.getName());
        System.out.println();

        /* We store in this reference the address of the first company from the array in alphabetical order */
        Company FirstAlphabeticalCompany = companies[0];
        for (int i = 1; i < companies.length; i++) {
            if (FirstAlphabeticalCompany.compareTo(companies[i]) > 0) {
                FirstAlphabeticalCompany = companies[i];
            }
        }
        System.out.println("FirstAlphabeticalOrderCompany: " + FirstAlphabeticalCompany.getName());

        System.out.println();

        persons[0].addSocialRelation(persons[1], PTOPRelationship.FRIENDSHIP);
        persons[0].addSocialRelation(persons[2], PTOPRelationship.MARRIAGE);
        persons[0].addProfessionalRelation(companies[0], PTOCRelationship.MANAGER);
        // Printing all social relationships created above
        for (int i = 0; i < persons.length; i++) {
            Map<Person, PTOPRelationship> socialRelationship = persons[i].getSocialRelation();
            System.out.println(persons[i].getName() + "'s social relations");
            for (int j = 0; j < socialRelationship.size(); j++) {
                System.out.println(persons[i].getSocialRelation());
            }

            Map<Company, PTOCRelationship> professionalRelationship = persons[i].getProfessionalRelation();
            for (int j = 0; j < professionalRelationship.size(); j++) {
                System.out.println(persons[i].getProfessionalRelation());
            }
            System.out.println();
        }

        // Working with nodes:
        List<Node> nodes = new ArrayList<>();

        Date DavidBirtDate = new Date(101, 5, 28); // June 28th, 2001
        nodes.add(new Programmer("David", DavidBirtDate, "C++"));
        nodes.add(new Company("AMD", 24_500));
        Date BiancaBirthDate = new Date(94, 6, 20); // July 20th, 1994
        nodes.add(new Designer("Bianca", BiancaBirthDate, "After Effects"));
        Date AndreiBirthDate = new Date(98, 7, 25); // August 25th, 1998
        nodes.add(new Designer("Andrei", AndreiBirthDate, "Photoshop"));


        for (int i = 0; i < nodes.size(); i++) {
            System.out.println(nodes.get(i));
        }
    }
}