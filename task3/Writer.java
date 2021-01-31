import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Writer {
    private List<String> list;
    private Map<Integer, String> map;

    private String hypo = "";
    private List<String> hypos;

    public void write() {
        ptr = new Pattern();

        for (int index = 0; index < list.size(); index++) {
            String activeExpr = list.get(index);

            if (hypos.contains(activeExpr)) {
                this.firstMethod(activeExpr);
            } else if (!isModusPonens(activeExpr, index)){
                if (isAxiom(activeExpr)) this.secondMethod(this.hypo);
                else this.firstMethod(activeExpr);
            }
        }
    }

    private boolean isModusPonens(String crazy, int pointer) {
        for (int index = 0; index < pointer; index++) {
            String temporary = String.format("(%s -> %s)", list.get(index), crazy);
            if (map.containsValue(temporary)) {
                this.thirdMethod(list.get(index), crazy);
                return true;
            }
        } return false;
    }

    private boolean isAxiom(String crazy) {
        if (!crazy.startsWith("(!!")) return false;

        String IMPL = " -> ";
        String[] temporary = crazy.substring(3, crazy.length() - 1).split(IMPL);
        if (temporary.length % 2 != 0) return false;

        int pointer = temporary.length / 2;

        StringBuilder lowerBound = new StringBuilder(temporary[0]);
        StringBuilder upperBound = new StringBuilder(temporary[pointer]);
        for (int index = 1; index < pointer; index++) {
            lowerBound.append(IMPL).append(temporary[index]);
        }
        for (int index = pointer + 1; index < temporary.length; index++) {
            upperBound.append(IMPL).append(temporary[index]);
        }
        this.hypo = lowerBound.toString();
        return this.hypo.equals(upperBound.toString());
    }

    public static class Builder {
        private final Writer writer;

        public Builder() {
            this.writer = new Writer();
        }
        public Builder setList(List<String> list) {
            this.writer.list = list;
            return this;
        }
        public Builder setMap(Map<Integer, String> map) {
            this.writer.map = map;
            return this;
        }
        public Builder setHypos(String[] hypos) {
            this.writer.hypos = Arrays.asList(hypos);
            return this;
        }
        public Writer build() {
            return this.writer;
        }
    }

    private Pattern ptr;

    private void firstMethod(String str1) {
        String temporary;
        List<String> list = new ArrayList<>();

        list = this.write(list, str1);
        list = this.write(list, temporary = ptr.kilo(list.get(0), "!" + str1));
        list = this.write(list, Algorithms.modusPonens(list.get(0), temporary));
        list = this.write(list, ptr.kilo("!" + str1, "!" + str1));
        list = this.write(list, temporary = ptr.mega("!" + str1, "(!" + str1 + " -> !" + str1 + ")", "!" + str1));
        list = this.write(list, Algorithms.modusPonens(list.get(3), temporary));
        list = this.write(list, temporary = ptr.kilo("!" + str1, "(!" + str1 + " -> !" + str1 + ")"));
        list = this.write(list, Algorithms.modusPonens(temporary, list.get(5)));
        list = this.write(list, temporary = ptr.giga("!" + str1, str1));
        list = this.write(list, temporary = Algorithms.modusPonens(list.get(2), temporary));
               this.write(list, Algorithms.modusPonens(list.get(7), temporary));
    }

    private void secondMethod(String str1) {
        List<String> list = new ArrayList<>();
        list.add(str1);

        list = this.write(list, ptr.kilo(list.get(0), "!!" + str1));
        list = this.write(list, ptr.kilo(list.get(1), "!(!!" + str1 + " -> " + list.get(0) + ")"));
        list = this.write(list, Algorithms.modusPonens(list.get(1), list.get(2)));
        list = this.write(list, ptr.kilo("!(!!" + str1 + " -> " + list.get(0) + ")", list.get(0)));
        list = this.write(list, ptr.kilo("!(!!" + str1 + " -> " + list.get(0) + ")", "!(!!" + str1 + " -> " + list.get(0) + ")"));
        list = this.write(list, ptr.kilo("!(!!" + str1 + " -> " + list.get(0) + ")", "(!(!!" + str1 + " -> " + list.get(0) + ") -> !(!!" + str1 + " -> " + list.get(0) + "))"));
        list = this.write(list, ptr.mega("!(!!" + str1 + " -> " + list.get(0) + ")", "(!(!!" + str1 + " -> " + list.get(0) + ") -> !(!!" + str1 + " -> " + list.get(0) + "))", "!(!!" + str1 + " -> " + list.get(0) + ")"));
        list = this.write(list, Algorithms.modusPonens(list.get(5), list.get(7)));
        list = this.write(list, Algorithms.modusPonens(list.get(6), list.get(8)));
        list = this.write(list, ptr.giga(list.get(0), "(!!" + str1 + " -> " + list.get(0) + ")"));
        list = this.write(list, ptr.kilo(list.get(10), "!(!!" + str1 + " -> " + list.get(0) + ")"));
        list = this.write(list, Algorithms.modusPonens(list.get(10), list.get(11)));
        list = this.write(list, ptr.mega("!(!!" + str1 + " -> " + list.get(0) + ")", list.get(1), "((" + list.get(0) + " -> !(!!" + str1 + " -> " + list.get(0) + ")) -> !" + str1 + ")"));
        list = this.write(list, Algorithms.modusPonens(list.get(3), list.get(13)));
        list = this.write(list, Algorithms.modusPonens(list.get(12), list.get(14)));
        list = this.write(list, ptr.mega("!(!!" + str1 + " -> " + list.get(0) + ")", "(" + list.get(0) + " -> !(!!" + str1 + " -> " + list.get(0) + "))", "!" + str1));
        list = this.write(list, Algorithms.modusPonens(list.get(4), list.get(16)));
        list = this.write(list, Algorithms.modusPonens(list.get(15), list.get(17)));
        list = this.write(list, ptr.tera("!" + str1, list.get(0)));
        list = this.write(list, ptr.kilo(list.get(19), "!(!!" + str1 + " -> " + list.get(0) + ")"));
        list = this.write(list, Algorithms.modusPonens(list.get(19), list.get(20)));
        list = this.write(list, ptr.mega("!(!!" + str1 + " -> " + list.get(0) + ")", "!" + str1, "(!!" + str1 + " -> " + list.get(0) + ")"));
        list = this.write(list, Algorithms.modusPonens(list.get(18), list.get(22)));
        list = this.write(list, Algorithms.modusPonens(list.get(21), list.get(23)));
        list = this.write(list, ptr.giga("!(!!" + str1 + " -> " + list.get(0) + ")", "(!!" + str1 + " -> " + list.get(0) + ")"));
        list = this.write(list, Algorithms.modusPonens(list.get(24), list.get(25)));
               this.write(list, Algorithms.modusPonens(list.get(9), list.get(26)));
    }

    private void thirdMethod(String str1, String str2) {
        List<String> list = new ArrayList<>();

        list = this.write(list, ptr.kilo("!" + str2, "(" + str1 + " -> " + str2 + ")"));
        list = this.write(list, ptr.kilo("!" + str2, str1));
        list = this.write(list, ptr.kilo(list.get(1), "!" + str2));
        list = this.write(list, Algorithms.modusPonens(list.get(1), list.get(2)));
        list = this.write(list, ptr.kilo(list.get(1), "(" + str1 + " -> " + str2 + ")"));
        list = this.write(list, ptr.kilo(list.get(4), "!" + str2));
        list = this.write(list, Algorithms.modusPonens(list.get(4), list.get(5)));
        list = this.write(list, ptr.mega("!" + str2, list.get(1), "((" + str1 + " -> " + str2 + ") -> " + list.get(1) + ")"));
        list = this.write(list, Algorithms.modusPonens(list.get(3), list.get(7)));
        list = this.write(list, Algorithms.modusPonens(list.get(6), list.get(8)));
        list = this.write(list, ptr.mega("(" + str1 + " -> " + str2 + ")", "!" + str2, "(" + str1 + " -> !" + str2 + ")"));
        list = this.write(list, ptr.kilo(list.get(10), "!" + str2));
        list = this.write(list, Algorithms.modusPonens(list.get(10), list.get(11)));
        list = this.write(list, ptr.mega("!" + str2, "((" + str1 + " -> " + str2 + ") -> !" + str2 + ")", "(((" + str1 + " -> " + str2 + ") -> (!" + str2 + " -> (" + str1 + " -> !" + str2 + "))) -> ((" + str1 + " -> " + str2 + ") -> (" + str1 + " -> !" + str2 + ")))"));
        list = this.write(list, Algorithms.modusPonens(list.get(0), list.get(13)));
        list = this.write(list, Algorithms.modusPonens(list.get(12), list.get(14)));
        list = this.write(list, ptr.mega("!" + str2, "((" + str1 + " -> " + str2 + ") -> (!" + str2 + " -> (" + str1 + " -> !" + str2 + ")))", "((" + str1 + " -> " + str2 + ") -> (" + str1 + " -> !" + str2 + "))"));
        list = this.write(list, Algorithms.modusPonens(list.get(9), list.get(16)));
        list = this.write(list, Algorithms.modusPonens(list.get(15), list.get(17)));
        list = this.write(list, ptr.giga(str1, str2));
        list = this.write(list, ptr.kilo(list.get(19), "!" + str2));
        list = this.write(list, Algorithms.modusPonens(list.get(19), list.get(20)));
        list = this.write(list, ptr.mega("(" + str1 + " -> " + str2 + ")", "(" + str1 + " -> !" + str2 + ")", "!" + str1));
        list = this.write(list, ptr.kilo(list.get(22), "!" + str2));
        list = this.write(list, Algorithms.modusPonens(list.get(22), list.get(23)));
        list = this.write(list, ptr.mega("!" + str2, "((" + str1 + " -> " + str2 + ") -> (" + str1 + " -> !" + str2 + "))", "(((" + str1 + " -> " + str2 + ") -> ((" + str1 + " -> !" + str2 + ") -> !" + str1 + ")) -> ((" + str1 + " -> " + str2 + ") -> !" + str1 + "))"));
        list = this.write(list, Algorithms.modusPonens(list.get(18), list.get(25)));
        list = this.write(list, Algorithms.modusPonens(list.get(24), list.get(26)));
        list = this.write(list, ptr.mega("!" + str2, "((" + str1 + " -> " + str2 + ") -> ((" + str1 + " -> !" + str2 + ") -> !" + str1 + "))", "((" + str1 + " -> " + str2 + ") -> !" + str1 + ")"));
        list = this.write(list, Algorithms.modusPonens(list.get(21), list.get(28)));
        list = this.write(list, Algorithms.modusPonens(list.get(27), list.get(29)));
        list = this.write(list, "!!" + str1);
        list = this.write(list, ptr.kilo("!!" + str1, "!" + str2));
        list = this.write(list, Algorithms.modusPonens(list.get(31), list.get(32)));
        list = this.write(list, ptr.kilo(list.get(31), "(" + str1 + " -> " + str2 + ")"));
        list = this.write(list, ptr.kilo(list.get(34), "!" + str2));
        list = this.write(list, Algorithms.modusPonens(list.get(34), list.get(35)));
        list = this.write(list, ptr.mega("!" + str2, "!!" + str1, "((" + str1 + " -> " + str2 + ") -> !!" + str1 + ")"));
        list = this.write(list, Algorithms.modusPonens(list.get(33), list.get(37)));
        list = this.write(list, Algorithms.modusPonens(list.get(36), list.get(38)));
        list = this.write(list, ptr.tera("!" + str1, "!(" + str1 + " -> " + str2 + ")"));
        list = this.write(list, ptr.kilo(list.get(40), "!" + str2));
        list = this.write(list, Algorithms.modusPonens(list.get(40), list.get(41)));
        list = this.write(list, ptr.kilo(list.get(40), "(" + str1 + " -> " + str2 + ")"));
        list = this.write(list, ptr.kilo(list.get(43), "!" + str2));
        list = this.write(list, Algorithms.modusPonens(list.get(43), list.get(44)));
        list = this.write(list, ptr.mega("!" + str2, list.get(40), "((" + str1 + " -> " + str2 + ") -> (!" + str1 + " -> (!!" + str1 + " -> !(" + str1 + " -> " + str2 + "))))"));
        list = this.write(list, Algorithms.modusPonens(list.get(42), list.get(46)));
        list = this.write(list, Algorithms.modusPonens(list.get(45), list.get(47)));
        list = this.write(list, ptr.mega("(" + str1 + " -> " + str2 + ")", "!" + str1, "(!!" + str1 + " -> !(" + str1 + " -> " + str2 + "))"));
        list = this.write(list, ptr.kilo(list.get(49), "!" + str2));
        list = this.write(list, Algorithms.modusPonens(list.get(49), list.get(50)));
        list = this.write(list, ptr.mega("!" + str2, "((" + str1 + " -> " + str2 + ") -> !" + str1 + ")", "(((" + str1 + " -> " + str2 + ") -> (!" + str1 + " -> (!!" + str1 + " -> !(" + str1 + " -> " + str2 + ")))) -> ((" + str1 + " -> " + str2 + ") -> (!!" + str1 + " -> !(" + str1 + " -> " + str2 + "))))"));
        list = this.write(list, Algorithms.modusPonens(list.get(30), list.get(52)));
        list = this.write(list, Algorithms.modusPonens(list.get(51), list.get(53)));
        list = this.write(list, ptr.mega("!" + str2, "((" + str1 + " -> " + str2 + ") -> (!" + str1 + " -> (!!" + str1 + " -> !(" + str1 + " -> " + str2 + "))))", "((" + str1 + " -> " + str2 + ") -> (!!" + str1 + " -> !(" + str1 + " -> " + str2 + ")))"));
        list = this.write(list, Algorithms.modusPonens(list.get(48), list.get(55)));
        list = this.write(list, Algorithms.modusPonens(list.get(54), list.get(56)));
        list = this.write(list, ptr.mega("(" + str1 + " -> " + str2 + ")", "!!" + str1, "!(" + str1 + " -> " + str2 + ")"));
        list = this.write(list, ptr.kilo(list.get(58), "!" + str2));
        list = this.write(list, Algorithms.modusPonens(list.get(58), list.get(59)));
        list = this.write(list, ptr.mega("!" + str2, "((" + str1 + " -> " + str2 + ") -> !!" + str1 + ")", "(((" + str1 + " -> " + str2 + ") -> (!!" + str1 + " -> !(" + str1 + " -> " + str2 + "))) -> ((" + str1 + " -> " + str2 + ") -> !(" + str1 + " -> " + str2 + ")))"));
        list = this.write(list, Algorithms.modusPonens(list.get(39), list.get(61)));
        list = this.write(list, Algorithms.modusPonens(list.get(60), list.get(62)));
        list = this.write(list, ptr.mega("!" + str2, "((" + str1 + " -> " + str2 + ") -> (!!" + str1 + " -> !(" + str1 + " -> " + str2 + ")))", "((" + str1 + " -> " + str2 + ") -> !(" + str1 + " -> " + str2 + "))"));
        list = this.write(list, Algorithms.modusPonens(list.get(57), list.get(64)));
        list = this.write(list, Algorithms.modusPonens(list.get(63), list.get(65)));
        list = this.write(list, "!!(" + str1 + " -> " + str2 + ")");
        list = this.write(list, ptr.kilo(list.get(67), "!" + str2));
        list = this.write(list, Algorithms.modusPonens(list.get(67), list.get(68)));
        list = this.write(list, ptr.kilo(list.get(67), "(" + str1 + " -> " + str2 + ")"));
        list = this.write(list, ptr.kilo(list.get(70), "!" + str2));
        list = this.write(list, Algorithms.modusPonens(list.get(70), list.get(71)));
        list = this.write(list, ptr.mega("!" + str2, list.get(67), "((" + str1 + " -> " + str2 + ") -> " + list.get(67) + ")"));
        list = this.write(list, Algorithms.modusPonens(list.get(69), list.get(73)));
        list = this.write(list, Algorithms.modusPonens(list.get(72), list.get(74)));
        list = this.write(list, ptr.giga("(" + str1 + " -> " + str2 + ")", "!(" + str1 + " -> " + str2 + ")"));
        list = this.write(list, ptr.kilo(list.get(76), "!" + str2));
        list = this.write(list, Algorithms.modusPonens(list.get(76), list.get(77)));
        list = this.write(list, ptr.mega("!" + str2, "((" + str1 + " -> " + str2 + ") -> !(" + str1 + " -> " + str2 + "))", "(((" + str1 + " -> " + str2 + ") -> " + list.get(67) + ") -> !(" + str1 + " -> " + str2 + "))"));
        list = this.write(list, Algorithms.modusPonens(list.get(66), list.get(79)));
        list = this.write(list, Algorithms.modusPonens(list.get(78), list.get(80)));
        list = this.write(list, ptr.mega("!" + str2, "((" + str1 + " -> " + str2 + ") -> " + list.get(67) + ")", "!(" + str1 + " -> " + str2 + ")"));
        list = this.write(list, Algorithms.modusPonens(list.get(75), list.get(82)));
        list = this.write(list, Algorithms.modusPonens(list.get(81), list.get(83)));
        list = this.write(list, ptr.tera("!(" + str1 + " -> " + str2 + ")", "!" + str1));
        list = this.write(list, ptr.kilo(list.get(85), "!" + str2));
        list = this.write(list, Algorithms.modusPonens(list.get(85), list.get(86)));
        list = this.write(list, ptr.mega("!" + str2, "!(" + str1 + " -> " + str2 + ")", "(" + list.get(67) + " -> !" + str1 + ")"));
        list = this.write(list, Algorithms.modusPonens(list.get(84), list.get(88)));
        list = this.write(list, Algorithms.modusPonens(list.get(87), list.get(89)));
        list = this.write(list, ptr.mega("!" + str2, list.get(67), "!" + str1));
        list = this.write(list, Algorithms.modusPonens(list.get(69), list.get(91)));
        list = this.write(list, Algorithms.modusPonens(list.get(90), list.get(92)));
        list = this.write(list, ptr.giga("!" + str2, "!" + str1));
        list = this.write(list, Algorithms.modusPonens(list.get(93), list.get(94)));
               this.write(list, Algorithms.modusPonens(list.get(33), list.get(95)));
    }

    public List<String> write(List<String> list, String expression) {
        list.add(expression);
        System.out.println(expression);
        return list;
    }
}


// |-a+0=a
// (((a)+0))=a
// (@y.y+0*0'=y)->(?x.@y.x=y)

// |-A->W->A
// A->B->A&B
// A->A|B
// (A->P)->(B->P)->(A|B->P)
// (@a.a+0=a)->b+0=b
// 0=0->(?x.x=0)
// a=b->a=c->b=c
// a=b->a'=b'
// a'=b'->a=b
// 0=0&(@x.x=x->x'=x')->x=x
// A->W->A
