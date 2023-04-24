package lab7.compulsory;
import javax.swing.JFrame;
import java.awt.*;

public class MainFrame extends JFrame {
    RobotManager display;
    public MainFrame() {
        super("Explore with robots");
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //create the component
        display = new RobotManager(this);

        setLayout(new BorderLayout());
        add(display, BorderLayout.CENTER);


        pack();
    }

}

