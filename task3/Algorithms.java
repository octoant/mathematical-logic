public final class Algorithms {

    protected static String modusPonens(String str1, String crazy) {
        String temporary = String.format("(%s -> ", str1);
        return crazy.startsWith(temporary) ? crazy.substring(str1.length() + 5, crazy.length() - 1) : crazy;
    }
}
