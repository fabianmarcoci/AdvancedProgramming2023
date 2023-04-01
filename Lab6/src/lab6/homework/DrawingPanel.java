package lab6.compulsory;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class DrawingPanel extends JPanel {
    final MainFrame frame;
    final int W = 1530, H = 768;
    private int numVertices;
    private double edgeProbability;
    private int[] x, y;
    private boolean redTurn = true;
    private Color[] player = {Color.red, Color.blue};
    private int playerTurn;
    private boolean gameOver = false;
    BufferedImage image; //the offscreen image
    Graphics2D graphics; //the tools needed to draw in the image
    private final Font messageFont = new Font("SansSerif", Font.BOLD, 48);
    private boolean[][] lineExistence, redLineExistence, blueLineExistence;
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
                if (gameOver == false) {
                    int mouseX = e.getX();
                    int mouseY = e.getY();

                    for (int i = 0; i < numVertices - 1; i++) {
                        for (int j = i + 1; j < numVertices; j++) {
                            if (lineExistence[i][j] == true) {
                                int x1 = x[i];
                                int y1 = y[i];
                                int x2 = x[j];
                                int y2 = y[j];

                                // calculate the distance from the click to the line with the perpendicular from a point to a line
                                double distance = Math.abs((y2 - y1) * mouseX - (x2 - x1) * mouseY + x2 * y1 - y2 * x1)
                                        / Math.sqrt(Math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2));

                                // check if the distance is less than the line width 5 pixels
                                if (distance < 5) {
                                    // delete line from the matrix so the other player can't click on it
                                    lineExistence[i][j] = false;

                                    playerTurn = redTurn ? 0 : 1;
                                    graphics.setColor(player[playerTurn]);

                                    System.out.println((playerTurn == 0 ? "Red" : "Blue") + " clicked on line between vertices " + i + " and " + j);

                                    graphics.drawLine(x1, y1, x2, y2);

                                    if (playerTurn == 0) {
                                        redLineExistence[i][j] = true;
                                        if (checkWin(redLineExistence)) {
                                            System.out.println(("Red won!"));
                                            gameOver = true;
                                            showMessage(playerTurn);
                                        }
                                    } else {
                                        blueLineExistence[i][j] = true;
                                        if (checkWin(blueLineExistence)) {
                                            System.out.println(("Blue won!"));
                                            gameOver = true;
                                            showMessage(playerTurn);
                                        }
                                    }

                                    // change player's turn
                                    redTurn = !redTurn;

                                    repaint();

                                    // save the current state of the game as a PNG file
                                    File outputFile = new File("gameState.png");
                                    try {
                                        ImageIO.write(image, "png", outputFile);
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }

                                    return;
                                }
                            }
                        }
                    }
                }
            }
        });
    }
        public void createOffscreenImage() {
            image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
            graphics = image.createGraphics();
            graphics.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, W, H);
            graphics.setFont(messageFont);
        }
        final void createBoard () {
            numVertices = (Integer) frame.configPanel.dotsSpinner.getValue();
            edgeProbability = (Double) frame.configPanel.linesCombo.getSelectedItem();
            lineExistence = new boolean[numVertices + 1][numVertices + 1];
            redLineExistence = new boolean[numVertices + 1][numVertices + 1];
            blueLineExistence = new boolean[numVertices + 1][numVertices + 1];
            createOffscreenImage();
            createVertices();
            drawVertices();
            drawLines();
            repaint();
        }
        public void createVertices () {
            int x0 = W / 2;
            int y0 = H / 2; //middle of the board
            int radius = H / 2 - 80; //board radius
            double alpha = 2 * Math.PI / numVertices; // the angle
            x = new int[numVertices];
            y = new int[numVertices];
            for (int i = 0; i < numVertices; i++) {
                x[i] = x0 + (int) (radius * Math.cos(alpha * i));
                y[i] = y0 + (int) (radius * Math.sin(alpha * i));
            }
        }

    public void drawLines() {
        graphics.setColor(Color.GRAY);
        for (int i = 0; i  < numVertices - 1; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                if (Math.random() < edgeProbability) {
                    graphics.drawLine(x[i], y[i], x[j], y[j]);
                    lineExistence[i][j] = true;
                }
            }
        }
    }
    public void drawVertices() {
        graphics.setColor(Color.BLACK);
        final int diameter = 10; // the diameter of each circle
        for (int i = 0; i < numVertices; i++) {
            graphics.fillOval(x[i] - diameter/2, y[i] - diameter/2, diameter, diameter);
        }
    }

    // functions made to load the game to it's saved state
    public void reDrawVertices() {
        graphics.setColor(Color.BLACK);
        final int diameter = 10; // the diameter of each circle
        for (int i = 0; i < numVertices; i++) {
            graphics.fillOval(x[i] - diameter/2, y[i] - diameter/2, diameter, diameter);
        }
    }

    // rebuilding the saved state of the game
    public void reDrawLines() {
        for (int i = 0; i < numVertices - 1; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                if (lineExistence[i][j] == true) {
                    graphics.setColor(Color.GRAY);
                    graphics.drawLine(x[i], y[i], x[j], y[j]);
                } else if (redLineExistence[i][j] == true) {
                    graphics.setColor(Color.RED);
                    graphics.drawLine(x[i], y[i], x[j], y[j]);
                } else if (blueLineExistence[i][j] == true) {
                    graphics.setColor(Color.BLUE);
                    graphics.drawLine(x[i], y[i], x[j], y[j]);
                }
            }
        }
    }

    // function to reset the game to it's initial state when all lines where grey
    public void resetGame() {
        for (int i = 0; i < numVertices - 1; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                if (redLineExistence[i][j] == true) {
                    lineExistence[i][j] = true;
                    redLineExistence[i][j] = false;
                    // I didn't know how to delete a line and redraw it in gray, so I had to redraw it twice
                    graphics.setColor(Color.WHITE);
                    graphics.drawLine(x[i], y[i], x[j], y[j]);
                    graphics.setColor(Color.GRAY);
                    graphics.drawLine(x[i], y[i], x[j], y[j]);
                } else if (blueLineExistence[i][j] == true) {
                    lineExistence[i][j] = true;
                    blueLineExistence[i][j] = false;
                    graphics.setColor(Color.WHITE);
                    graphics.drawLine(x[i], y[i], x[j], y[j]);
                    graphics.setColor(Color.GRAY);
                    graphics.drawLine(x[i], y[i], x[j], y[j]);
                }
            }
        }

        redTurn = true;
        gameOver = false;
    }

    private boolean checkWin(boolean[][] colorMatrix) {
        for (int i = 0; i < numVertices - 2; i++) {
            for (int j = i + 1; j < numVertices - 1; j++) {
                // if there is a line between 2 vertices we search a 3rd vertex 'k' which is connected to both 'i' and 'j'
                if (colorMatrix[i][j] == true) {
                    for (int k = j + 1; k < numVertices; k++) {
                        if (colorMatrix[i][k] == true && colorMatrix[j][k] == true) {
                            // it means a player won
                            return true;
                        }
                    }
                }
            }
        }
        return false;

    }

    private void showMessage(int playerTurn) {
        // setting the color of the player who won
        graphics.setColor(playerTurn == 0 ? Color.RED : Color.BLUE);
        String message = ((playerTurn == 0 ? "Red" : "Blue") + " won!");
        FontMetrics fontMetrics = graphics.getFontMetrics(messageFont);
        int messageWidth = fontMetrics.stringWidth(message);
        int messageHeight = fontMetrics.getHeight();
        graphics.drawString(message, (getWidth() - messageWidth) / 2, (getHeight() - messageHeight) / 2);
    }

    // getters for saving function
    public int getNumVertices() {
        return numVertices;
    }

    public double getEdgeProbability() {
        return edgeProbability;
    }

    public int[] getXArr() {
        return x;
    }

    public int[] getYArr() {
        return y;
    }

    public boolean[][] getLineExistence() {
        return lineExistence;
    }

    public boolean[][] getRedLineExistence() {
        return redLineExistence;
    }

    public boolean[][] getBlueLineExistence() {
        return blueLineExistence;
    }

    public boolean isRedTurn() {
        return redTurn;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    // setters for loading function

    public void setNumVertices(int numVertices) {
        this.numVertices = numVertices;
    }

    public void setEdgeProbability(double edgeProbability) {
        this.edgeProbability = edgeProbability;
    }

    public void setX(int[] x) {
        this.x = x;
    }

    public void setY(int[] y) {
        this.y = y;
    }

    public void setLineExistence(boolean[][] lineExistence) {
        this.lineExistence = lineExistence;
    }

    public void setRedLineExistence(boolean[][] redLineExistence) {
        this.redLineExistence = redLineExistence;
    }

    public void setBlueLineExistence(boolean[][] blueLineExistence) {
        this.blueLineExistence = blueLineExistence;
    }

    public void setRedTurn(boolean redTurn) {
        this.redTurn = redTurn;
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    @Override
    public void update(Graphics g) { } //No need for update

    //Draw the offscreen image, using the original graphics
    @Override
    protected void paintComponent(Graphics graphics) {
        graphics.drawImage(image, 0, 0, this);
    }

}

