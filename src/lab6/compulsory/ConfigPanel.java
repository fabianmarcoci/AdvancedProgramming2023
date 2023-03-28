package lab6.compulsory;
import javax.swing.*;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel dotsLabel, linesLabel;
    JSpinner dotsSpinner;
    JComboBox linesCombo;
    JButton createButton;
    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }
    private void init() {
        //create the labels, the spinner, the combobox and the button
        dotsLabel = new JLabel("Number of dots:");
        dotsSpinner = new JSpinner(new SpinnerNumberModel(6, 3, 15, 1));

        linesLabel = new JLabel("Line probability");
        linesCombo = new JComboBox();
        linesCombo.addItem(0.50);
        linesCombo.addItem(1.0);

        createButton = new JButton("Create new game");

        add(dotsLabel);
        add(dotsSpinner);
        add(linesLabel);
        add(linesCombo);
        add(createButton);
    }

}

