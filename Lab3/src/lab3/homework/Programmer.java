package lab3.compulsory;
import java.util.Date;
public class Programmer extends Person{
    private String skill;
    private String ProgrammingLanguage;
    public Programmer(String name, Date BirthDate, String ProgrammingLanguage) {
        super(name, BirthDate);
        this.skill = "Programmer";
        this.ProgrammingLanguage = ProgrammingLanguage;
    }

    public String getSkill() {
        return skill;
    }

    public String getProgrammingLanguage() {
        return ProgrammingLanguage;
    }

    public void setProgrammingLanguage(String ProgrammingLanguage) {
        this.ProgrammingLanguage = ProgrammingLanguage;
    }

    @Override
    public String toString() {
        return super.toString() +
                "skill='" + skill + '\'' +
                ", ProgrammingLanguage='" + ProgrammingLanguage + '\'' +
                '}';
    }
}
