import java.util.Objects;

public interface Program {
    int status = 0;

    static void checkInputString(String str) {
        if (isNull(str)) stop(status);

        else if (areEqual(replaceChar(str, " ", ""), "")) stop(status);
        else if (orMatches(str , "\t", "\n")) stop(status);
        else if (areEqual(str, "|-") || notContains(str, "|-")) stop(status);
    }

    static void stop(int status) {
        System.exit(status);
    }

    static boolean isNull(String str) {
        return str == null;
    }

    static String replaceChar(String str, String oldChar, String newChar) {
        return str.replace(oldChar, newChar);
    }

    default String replaceChars(String str, String oldStr, String newStr) {
        return str.replaceAll(oldStr, newStr);
    }

    default String replaceDouble(String str, String char1, String char2, String newChar) {
        return str.replace(char1, newChar).replace(char2, newChar);
    }

    static boolean areEqual(String str1, String str2) {
        return str1.equals(str2);
    }

    static boolean orMatches(String str, String... signs) {
        for(String sign : signs) if (str.matches(sign)) return true;
        return false;
    }

    static boolean notContains(String str, String sign) {
        return !str.contains(sign);
    }

    default boolean startsWith(String str, String sign) {
        return str.startsWith(sign);
    }
}
