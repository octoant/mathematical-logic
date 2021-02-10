public class Axioms {
    // A -> B -> A
    public boolean isAxiom1(Node node) {
        return node.getInstance().equals(Operation.IMPLICATION.toString()) && node.rightNode().getInstance().equals(Operation.IMPLICATION.toString()) && node.leftNode().equals(node.rightNode().rightNode());
    }
    // (A -> B) -> (A -> B -> C) -> (A -> C)
    public boolean isAxiom2(Node node) {
        return node.getInstance().equals(Operation.IMPLICATION.toString()) && node.leftNode().getInstance().equals(Operation.IMPLICATION.toString()) && node.rightNode().getInstance().equals(Operation.IMPLICATION.toString()) && node.rightNode().leftNode().getInstance().equals(Operation.IMPLICATION.toString()) && node.rightNode().leftNode().rightNode().getInstance().equals(Operation.IMPLICATION.toString()) && node.rightNode().rightNode().getInstance().equals(Operation.IMPLICATION.toString()) && node.leftNode().leftNode().equals(node.rightNode().leftNode().leftNode()) && node.leftNode().leftNode().equals(node.rightNode().rightNode().leftNode()) && node.leftNode().rightNode().equals(node.rightNode().leftNode().rightNode().leftNode()) && node.rightNode().leftNode().rightNode().rightNode().equals(node.rightNode().rightNode().rightNode());
    }
    // A -> B -> A & B
    public boolean isAxiom3(Node node) {
        return node.getInstance().equals(Operation.IMPLICATION.toString()) && node.rightNode().getInstance().equals(Operation.IMPLICATION.toString()) && node.rightNode().rightNode().getInstance().equals(Operation.CONJUNCTION.toString()) && node.leftNode().equals(node.rightNode().rightNode().leftNode()) && node.rightNode().leftNode().equals(node.rightNode().rightNode().rightNode());
    }
    // A & B -> A
    public boolean isAxiom4(Node node) {
        return node.getInstance().equals(Operation.IMPLICATION.toString()) && node.leftNode().getInstance().equals(Operation.CONJUNCTION.toString()) && node.leftNode().leftNode().equals(node.rightNode());
    }
    // A & B -> B
    public boolean isAxiom5(Node node) {
        return node.getInstance().equals(Operation.IMPLICATION.toString()) && node.leftNode().getInstance().equals(Operation.CONJUNCTION.toString()) && node.leftNode().rightNode().equals(node.rightNode());
    }
    // A -> A | B
    public boolean isAxiom6(Node node) {
        return node.getInstance().equals(Operation.IMPLICATION.toString()) && node.rightNode().getInstance().equals(Operation.DISJUNCTION.toString()) && node.leftNode().equals(node.rightNode().leftNode());
    }
    // B -> A | B
    public boolean isAxiom7(Node node) {
        return node.getInstance().equals(Operation.IMPLICATION.toString()) && node.rightNode().getInstance().equals(Operation.DISJUNCTION.toString()) && node.leftNode().equals(node.rightNode().rightNode());
    }
    // (A -> C) -> (B -> C) -> (A | B -> C)
    public boolean isAxiom8(Node node) {
        return node.getInstance().equals(Operation.IMPLICATION.toString()) && node.leftNode().getInstance().equals(Operation.IMPLICATION.toString()) && node.rightNode().getInstance().equals(Operation.IMPLICATION.toString()) && node.rightNode().leftNode().getInstance().equals(Operation.IMPLICATION.toString()) && node.rightNode().rightNode().getInstance().equals(Operation.IMPLICATION.toString()) && node.rightNode().rightNode().leftNode().getInstance().equals(Operation.DISJUNCTION.toString()) && node.leftNode().leftNode().equals(node.rightNode().rightNode().leftNode().leftNode()) && node.rightNode().leftNode().leftNode().equals(node.rightNode().rightNode().leftNode().rightNode()) && node.leftNode().rightNode().equals(node.rightNode().leftNode().rightNode()) && node.leftNode().rightNode().equals(node.rightNode().rightNode().rightNode());
    }
    // (A -> B) -> (A -> !B) -> !A
    public boolean isAxiom9(Node node) {
        return node.getInstance().equals(Operation.IMPLICATION.toString()) && node.leftNode().getInstance().equals(Operation.IMPLICATION.toString()) && node.rightNode().getInstance().equals(Operation.IMPLICATION.toString()) && node.rightNode().leftNode().getInstance().equals(Operation.IMPLICATION.toString()) && node.rightNode().leftNode().rightNode().getInstance().equals(Operation.NEGATION.toString()) && node.rightNode().rightNode().getInstance().equals(Operation.NEGATION.toString()) && node.leftNode().leftNode().equals(node.rightNode().leftNode().leftNode()) && node.leftNode().leftNode().equals(node.rightNode().rightNode().leftNode()) && node.leftNode().rightNode().equals(node.rightNode().leftNode().rightNode().leftNode());
    }
    // !!A -> A
    public boolean isAxiom10(Node node) {
        return node.getInstance().equals(Operation.IMPLICATION.toString()) && node.leftNode().getInstance().equals(Operation.NEGATION.toString()) && node.leftNode().leftNode().getInstance().equals(Operation.NEGATION.toString()) && node.leftNode().leftNode().leftNode().equals(node.rightNode());
    }
}
