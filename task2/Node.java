public class Node {
    private final String current;

    private Node leftNode;
    private Node rightNode;

    // full node
    public Node(Operation operation, Node leftNode, Node rightNode) {
        current = operation.toString();
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }
    // half node
    public Node (Operation operation, Node leftNode) {
        current = operation.toString();
        this.leftNode = leftNode;
    }
    // leaf
    public Node(String variable) {
        current = variable;
    }

    public Node leftNode() {
        return this.leftNode;
    }
    public Node rightNode() {
        return this.rightNode;
    }

    @Override
    public String toString() {
        if (rightNode != null) // returns: (A & B) or (A | B) or (A -> B)
            return "(" +
                    leftNode.toString() + // A
                    " " +
                    current + // '&' or '|' or '->'
                    " " +
                    rightNode + // B
                    ")";

        else if (leftNode != null) // returns: !A
            return current + leftNode.toString();

        else return current; // returns: A
    }
}
