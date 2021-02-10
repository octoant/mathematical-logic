public class Node {
    private final String instance;

    private Node leftNode;
    private Node rightNode;

    private final int hashCode; // (x << 6) - x == (2^6 - 1) * x == 63 * x

    // full node
    public Node(Operation operation, Node leftNode, Node rightNode) {
        instance = operation.toString();
        this.leftNode = leftNode;
        this.rightNode = rightNode;

        int tmp = instance.hashCode();
            tmp = (tmp << 6) - tmp + leftNode.hashCode();
            tmp = (tmp << 6) - tmp + rightNode.hashCode();
        hashCode = tmp;
    }
    // half node
    public Node (Operation operation, Node leftNode) {
        instance = operation.toString();
        this.leftNode = leftNode;

        int tmp = instance.hashCode();
            tmp = tmp << 6 - tmp + leftNode.hashCode();
        hashCode = tmp;
    }
    // leaf
    public Node(String variable) {
        instance = variable;
        hashCode = instance.hashCode();
    }

    public String getInstance() {
        return this.instance;
    }

    public Node leftNode() {
        return this.leftNode;
    }
    public Node rightNode() {
        return this.rightNode;
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }

    public boolean equals(Node node) {
        return this.hashCode() == node.hashCode();
    }

    @Override
    public String toString() {
        if (rightNode != null) // returns: (A & B) or (A | B) or (A -> B)
            return "(" +
                    leftNode.toString() + // A
                    " " +
                    instance + // '&' or '|' or '->'
                    " " +
                    rightNode + // B
                    ")";

        else if (leftNode != null) // returns: !A
            return instance + leftNode.toString();

        else return instance; // returns: A
    }
}
