package lab3.compulsory;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
public class Network{
    private List<Node> nodes;

    public Network() {
        this.nodes = new ArrayList<>();
    }

    public void addNode(Node node) {
        this.nodes.add(node);
    }

    public List<Node> getNodes() {
        return nodes;
    }
}
