package lab6.compulsory;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.*;
import javax.swing.JFrame;

public class GameData extends JFrame{
    MainFrame frame;
    private static final String FILE_NAME = "game_data.ser";

    public GameData(MainFrame frame) {
        this.frame = frame;
    }

    public void save() {
        try {
            FileOutputStream fileOut = new FileOutputStream("myGame.dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(frame.canvas.getNumVertices());
            out.writeObject(frame.canvas.getEdgeProbability());
            out.writeObject(frame.canvas.getXArr());
            out.writeObject(frame.canvas.getYArr());
            out.writeObject(frame.canvas.getLineExistence());
            out.writeObject(frame.canvas.getRedLineExistence());
            out.writeObject(frame.canvas.getBlueLineExistence());
            out.writeObject(frame.canvas.isRedTurn());
            out.writeObject(frame.canvas.getPlayerTurn());
            out.writeObject(frame.canvas.isGameOver());
            out.close();
            fileOut.close();
            JOptionPane.showMessageDialog(frame.canvas, "Game saved successfully.");
        } catch (IOException i) {
            JOptionPane.showMessageDialog(frame.canvas, "Error saving game: " + i.getMessage());
        }
    }

    // we have to read the objects back in the same order we saved them!!!
    public void load() {
        try {
            FileInputStream fileIn = new FileInputStream("myGame.dat");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            frame.canvas.setNumVertices((int) in.readObject());
            frame.canvas.setEdgeProbability((double) in.readObject());
            frame.canvas.setX((int[]) in.readObject());
            frame.canvas.setY((int[]) in.readObject());
            frame.canvas.setLineExistence((boolean[][]) in.readObject());
            frame.canvas.setRedLineExistence((boolean[][]) in.readObject());
            frame.canvas.setBlueLineExistence((boolean[][]) in.readObject());
            frame.canvas.setRedTurn((boolean) in.readObject());
            frame.canvas.setPlayerTurn((int) in.readObject());
            frame.canvas.setGameOver((boolean) in.readObject());
            in.close();
            fileIn.close();
            frame.canvas.createOffscreenImage();
            frame.canvas.reDrawVertices();
            frame.canvas.reDrawLines();
            frame.canvas.repaint();
            JOptionPane.showMessageDialog(frame.canvas, "Game loaded successfully.");
        } catch (IOException i) {
            JOptionPane.showMessageDialog(frame.canvas, "Error loading game: " + i.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void reset() {
        frame.canvas.resetGame();
        frame.canvas.repaint();
        JOptionPane.showMessageDialog(frame.canvas, "Game reset successfully.");
    }
    public void createNewGame() {
        frame.canvas.setRedTurn(true);
        frame.canvas.setGameOver(false);
        frame.canvas.setNumVertices((int) frame.configPanel.dotsSpinner.getValue());
        frame.canvas.createVertices();
        frame.canvas.setEdgeProbability((double) frame.configPanel.linesCombo.getSelectedItem());
        frame.canvas.setLineExistence(new boolean[frame.canvas.getNumVertices() + 1][frame.canvas.getNumVertices() + 1]);
        frame.canvas.setRedLineExistence(new boolean[frame.canvas.getNumVertices() + 1][frame.canvas.getNumVertices() + 1]);
        frame.canvas.setBlueLineExistence(new boolean[frame.canvas.getNumVertices() + 1][frame.canvas.getNumVertices() + 1]);
        frame.canvas.createOffscreenImage();
        frame.canvas.drawVertices();
        frame.canvas.drawLines();
        frame.canvas.repaint();
        JOptionPane.showMessageDialog(frame.canvas, "Game created successfully.");
    }
}
