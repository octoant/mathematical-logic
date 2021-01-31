public class Pattern {
    public String kilo(String s1, String s2) {
        return String.format("(%s -> (%s -> %s))", s1, s2, s1);
    }
    public String mega(String s1, String s2, String s3) {
        return String.format("((%s -> %s) -> ((%s -> (%s -> %s)) -> (%s -> %s)))", s1, s2, s1, s2, s3, s1, s3);
    }
    public String giga(String s1, String s2) {
        return String.format("((%s -> %s) -> ((%s -> !%s) -> !%s))", s1, s2, s1, s2, s1);
    }
    public String tera(String s1, String s2) {
        return String.format("(%s -> (!%s -> %s))", s1, s1, s2);
    }
}
