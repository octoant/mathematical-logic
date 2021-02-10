public final class Parser {
    private int pointer;

    public Node parse(String expr) {
        this.pointer = 0;
        return implication(expr);
    }

    private static final String OPEN_BRACKET = "(";
    private static final String CLOSE_BRACKET = ")";

    // IMPL
    private Node implication(String expr) {
        Node node = disjunction(expr);

        if (nextSignIs(expr, Operation.IMPLICATION.toString())) {
            node = new Node(Operation.IMPLICATION, node, implication(expr));
        }
        return node;
    }

    // OR
    private Node disjunction(String expr) {
        Node node = conjunction(expr);

        while (nextSignIs(expr, Operation.DISJUNCTION.toString())) {
            node = new Node(Operation.DISJUNCTION, node, conjunction(expr));
        }
        return node;
    }

    // AND
    private Node conjunction(String expr) {
        Node node = negation(expr);

        while (nextSignIs(expr, Operation.CONJUNCTION.toString())) {
            node = new Node(Operation.CONJUNCTION, node, negation(expr));
        }
        return node;
    }

    // NOT
    private Node negation(String expr) {
        if (nextSignIs(expr, Operation.NEGATION.toString())) {
            return new Node(Operation.NEGATION, negation(expr));
        }

        if (nextSignIs(expr, OPEN_BRACKET)) {
            Node node = implication(expr);
            nextSignIs(expr, CLOSE_BRACKET);
            return node;
        }

        char character = expr.charAt(pointer);

        StringBuilder variable = new StringBuilder();
        while (pointer < expr.length() - 1 && isAllowedChar(character)) {
            variable.append(character);
            character = expr.charAt(++pointer);
        }
        if (pointer == expr.length() - 1 && isAllowedChar(character)) variable.append(character);
        return new Node(variable.toString());
    }

    // allowed only capital latin letters[A-Z], numbers[0-9] and apostrophe[\']
    private boolean isAllowedChar(char character) {
        return 64 < character && character < 91 || 47 < character && character < 58 || character == 39;
    }

    private boolean nextSignIs(String expr, String sign) {
        skipAllWhiteSpaces(expr);
        boolean result = expr.startsWith(sign, pointer);
        skipAllWhiteSpaces(expr);

        if (result) this.pointer += sign.length();
        return result;
    }

    // skips all whitespaces
    public void skipAllWhiteSpaces(String expr) {
        while (pointer < expr.length() && isAllowedWhite(expr.charAt(pointer))) {
            this.pointer ++;
        }
    }
    // allowed whitespaces[\\s+]
    private boolean isAllowedWhite(char character) {
        return character == ' ' || character == '\t' || character == '\r';
    }
}
