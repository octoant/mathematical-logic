public final class Checker {

    private static int pointer = 0;

    public static void restore() {
        pointer = 0;
    }

    public static boolean checkOp(String exprAc, String operator) {
        boolean startsWithOp = exprAc.startsWith(operator, pointer);
        // moving pointer to the new index
        if (startsWithOp) pointer += operator.length();
        return startsWithOp;
    }

    public static int getPointer() {
        return pointer;
    }
    public static void incrementPointer () {
        pointer ++;
    }
}
