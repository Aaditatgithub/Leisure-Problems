package GameTheoryProblems;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Node {
    int value;
    List<Node> children;

    Node(int value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    void addChild(Node child) {
        children.add(child);
    }
}

public class IDDFS {

    // Modified DLS method to handle cycles
    public boolean dls(Node node, int target, int depth, Set<Node> visited) {
        System.out.println("Visiting Node " + node.value + " at depth " + depth);

        // base case
        if (depth == 0) {
            return node.value == target;
        }
        
        //recursive case
        if (depth > 0) {
            visited.add(node);
            for (Node child : node.children) {
                if (!visited.contains(child)) {
                    if (dls(child, target, depth - 1, visited)) {
                        return true;
                    }
                }
            }
            visited.remove(node);
        }

        return false;
    }

    public boolean iddfs(Node root, int target, int maxDepth) {
        for (int depth = 0; depth < maxDepth; depth++) {
            System.out.println("Depth " + depth + ":");

            Set<Node> visited = new HashSet<>();
            
            if (dls(root, target, depth, visited)) {
                System.out.println("Target " + target + " found at depth " + depth);
                return true;
            }
            System.out.println();
        }
        return false;
    }

    public static void main(String[] args) {
        // Creating a more complex graph with cycles
        Node root = new Node(1);

        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);

        Node node10 = new Node(10);
        Node node11 = new Node(11);
        Node node12 = new Node(12);
        Node node13 = new Node(13);
        Node node14 = new Node(14);
        Node node15 = new Node(15);

        Node node16 = new Node(16);
        Node node17 = new Node(17);

        // Level 1
        root.addChild(node2);
        root.addChild(node3);
        root.addChild(node4);

        // Level 2
        node2.addChild(node5);
        node2.addChild(node6);
        node3.addChild(node7);
        node3.addChild(node8);
        node4.addChild(node9);

        // Level 3
        node5.addChild(node10);
        node5.addChild(node11);
        node7.addChild(node12);
        node8.addChild(node13);
        node8.addChild(node14);
        node9.addChild(node15);

        // Level 4
        node11.addChild(node16);
        node13.addChild(node17);

        // Adding cycles
        node12.addChild(node2);  // Cycle 1: Node 12 -> Node 2
        node15.addChild(node5);  // Cycle 2: Node 15 -> Node 5
        


        IDDFS iddfs = new IDDFS();
        int target = 17;  // Change target as needed
        int maxDepth = 6;

        if (!iddfs.iddfs(root, target, maxDepth)) {
            System.out.println("Target " + target + " not found within depth " + maxDepth);
        }
    }
}
