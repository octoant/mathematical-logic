public enum Operation {

    IMPLICATION("->"),
    DISJUNCTION("|"),
    CONJUNCTION("&"),
    NEGATION("!");

    private final String sign;
    Operation(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return this.sign;
    }
}
