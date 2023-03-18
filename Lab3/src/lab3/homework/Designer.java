package lab3.compulsory;
import java.util.Date;

public class Designer extends Person{
    private String skill;
    private String DesigningTool;

    public Designer(String name, Date BirthDate, String DesigningTool) {
        super(name, BirthDate);
        this.skill = "Programmer";
            this.DesigningTool = DesigningTool;
    }

    public String getSkill() {
        return skill;
    }

    public String getDesigningTool() {
        return DesigningTool;
    }

    public void setDesigningTool(String DesigningTool) {
        this.DesigningTool = DesigningTool;
    }

    @Override
    public String toString() {
        return super.toString() +
                "skill='" + skill + '\'' +
                ", DesigningTool='" + DesigningTool + '\'' +
                '}';
    }
}
