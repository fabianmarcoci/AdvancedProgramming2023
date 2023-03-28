package lab6.compulsory;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class DrawingPanel extends JPanel {
    final MainFrame frame;
    final int W = 1366, H = 768;
    private int numVertices;
    private double edgeProbability;
    private int[] x, y;
    BufferedImage image; //the offscreen image
    Graphics2D graphics; //the tools needed to draw in the image

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        initPanel();
        createBoard();
    }

    private void initPanel() {
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int a = e.getX();
                int b = e.getY();


                repaint();
            }
        });
    }
        private void createOffscreenImage () {
            image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
            graphics = image.createGraphics();
            graphics.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, W, H);
        }
        final void createBoard () {
            numVertices = (Integer) frame.configPanel.dotsSpinner.getValue();
            edgeProbability = (Double) frame.configPanel.linesCombo.getSelectedItem();
            createOffscreenImage();
            createVertices();
            drawVertices();
            drawLines();
            repaint();
        }
        private void createVertices () {
            int x0 = W / 2;
            int y0 = H / 2; //middle of the board
            int radius = H / 2 - 10; //board radius
            double alpha = 2 * Math.PI / numVertices; // the angle
            x = new int[numVertices];
            y = new int[numVertices];
            for (int i = 0; i < numVertices; i++) {
                x[i] = x0 + (int) (radius * Math.cos(alpha * i));
                y[i] = y0 + (int) (radius * Math.sin(alpha * i));
            }
        }

    private void drawLines() {
        graphics.setColor(Color.GRAY);
        for (int i = 0; i  < numVertices - 1; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                if (Math.random() < edgeProbability) {
                    graphics.drawLine(x[i], y[i], x[j], y[j]);
                }
            }
        }
    }
    private void drawVertices() {
        graphics.setColor(Color.BLACK);
        final int diameter = 10; // the diameter of each circle
        for (int i = 0; i < numVertices; i++) {
            graphics.fillOval(x[i] - diameter/2, y[i] - diameter/2, diameter, diameter);
        }
    }

    @Override
    public void update(Graphics g) { } //No need for update

    //Draw the offscreen image, using the original graphics
    @Override
    protected void paintComponent(Graphics graphics) {
        graphics.drawImage(image, 0, 0, this);
    }

}

