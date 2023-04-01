package lab6.compulsory;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton exitBtn = new JButton("Exit");
    JButton loadBtn = new JButton("Load");
    JButton saveBtn = new JButton("Save");
    JButton resetBtn = new JButton("Reset");
    GameData gameData; // store the game data here
    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        gameData = new GameData(frame);
        init();
    }
    private void init() {
        setLayout(new GridLayout(1, 4));
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(exitBtn);
        buttonPanel.add(saveBtn);
        buttonPanel.add(loadBtn);
        buttonPanel.add(resetBtn);

        add(buttonPanel, BorderLayout.CENTER);

        exitBtn.addActionListener(this::exitGame);
        saveBtn.addActionListener(this::saveGame);
        loadBtn.addActionListener(this::loadGame);
        resetBtn.addActionListener(this::resetGame);
    }
    private void exitGame(ActionEvent e) {
        frame.dispose();
        System.out.println("Game exited!");
    }
    private void saveGame(ActionEvent e) {
        gameData.save();
    }
    private void loadGame(ActionEvent e) {
        gameData.load();
    }
    private void resetGame(ActionEvent e) {
        gameData.reset();
    }
}
