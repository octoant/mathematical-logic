public final class LogicalOperation {

    public static String impl(String exprAc) {
        String exprNg = or(exprAc);
        if (Checker.checkOp(exprAc, "->")) {
            exprNg = "(" + exprNg + " -> " + impl(exprAc) + ")";
        }
        return exprNg;
    }

    public static String or(String exprAc) {
        String exprNg = and(exprAc);
        while (Checker.checkOp(exprAc, "|")) {
            exprNg = "(" + exprNg + " | " + and(exprAc) + ")";
        }
        return exprNg;
    }

    public static String and(String exprAc) {
        String exprNg = not(exprAc);
        while (Checker.checkOp(exprAc, "&")) {
            exprNg = "(" + exprNg + " & " + not(exprAc) + ")";
        }
        return exprNg;
    }

    public static String not(String exprAc) {
        String exprNg;
        if (Checker.checkOp(exprAc, "(")) {
            exprNg = impl(exprAc);
            Checker.checkOp(exprAc, ")");
            return exprNg;
        }
        if (Checker.checkOp(exprAc, "!")) return "!" + not(exprAc);

        String elemItem = analysisElement(exprAc); exprNg = "";
        while (elemItem.matches("[0-9]+") || elemItem.equals("'") || elemItem.matches("[a-zA-Z]+")) {
            Checker.incrementPointer();
            exprNg += elemItem;
            elemItem = analysisElement(exprAc);
        }
        return exprNg;
    }

    private static String analysisElement(String exprAc) {
        int index = Checker.getPointer();
        return index < exprAc.length() - 1 ? exprAc.substring(index, index + 1) : exprAc.substring(index);
    }
}
