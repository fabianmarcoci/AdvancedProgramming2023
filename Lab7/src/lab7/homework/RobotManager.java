package lab7.compulsory;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class RobotManager extends JPanel {
    final MainFrame frame;
    private Exploration explore;
    final int W = 700, H = 250;
    private boolean gameOver = false;
    BufferedImage image; //the offscreen image
    Graphics2D graphics; //the tools needed to draw in the image
    JButton start = new JButton("Start");
    JLabel memoryAndCellsLabel = new JLabel("Number of cells and shared memory:");
    static JSpinner memoryAndCellsSpinner = new JSpinner(new SpinnerNumberModel(10, 6, 30, 1));
    private final Font messageFont = new Font("SansSerif", Font.BOLD, 48);
    public RobotManager(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        initPanel();
        addListeners();
    }

    private void initPanel() {
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
        add(memoryAndCellsLabel);
        add(memoryAndCellsSpinner);
        add(start);
    }
    public void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, W, H);
        graphics.setFont(messageFont);
    }

    private void addConfigurations() {
        explore = new Exploration();
        // setting the shared memory value
        explore.setMem();
        // setting the number of cells we want
        ExplorationMap.setNumberOfCells();

    }
    private void addListeners() {
        start.addActionListener(e -> {
            startExploration();
            start.setEnabled(false);
        });
    }
    public void startExploration() {
        addConfigurations();
        addBot("Wall-E", 6, 7);
        addBot("R2D2", 1, 2);
        addBot("Optimus Prime", 4, 5);
        explore.start();
    }

    public void addBot(String name, int x, int y) {
        Robot robot = new Robot(name, x, y, explore);
        explore.addRobot(robot);
        JButton stopButton = new JButton("Stop " + name);
        add(stopButton);
        stopButton.addActionListener(e -> {
            if (robot.isRunning()) {
                robot.pause();
                stopButton.setText("Start " + name);
            } else {
                robot.go();
                stopButton.setText("Stop " + name);
            }
        });
        stopButton.repaint();
        revalidate();
        repaint();
    }
    public static JSpinner getMemoryAndCellsSpinner() {
        return memoryAndCellsSpinner;
    }
}

