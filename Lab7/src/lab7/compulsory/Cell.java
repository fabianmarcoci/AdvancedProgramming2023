package lab7.compulsory;
import java.util.ArrayList;
import java.util.List;

public class Cell {
    private List<Token> tokens;
    private int x, y;
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        tokens = new ArrayList<>();
    }
    public synchronized void visit(List<Token> tokens) {
        for (int i = 0; i < tokens.size(); i++) {
            this.tokens.add(tokens.get(i));
        }
    }
    public boolean isVisited() {
        if (tokens.isEmpty()) {
            return false;
        }
        return true;
    }
}
