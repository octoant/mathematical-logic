import java.util.Scanner;

public class Root {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Parser parser = new Parser();
        Axioms axioms = new Axioms();

        // 1
        Node node1 = parser.parse("A->B->A");

        System.out.println(node1);
        System.out.print("1. ");
        System.out.println(axioms.isAxiom1(node1));

        // 2
        Node node2 = parser.parse("(A ->B) ->(A->B -> (A->!V)) -> (A -> (A -> !V))");

        System.out.println(node2);
        System.out.print("2. ");
        System.out.println(axioms.isAxiom2(node2));

        // 3
        Node node3 = parser.parse("GT' -> (GGG|JJJ) -> GT' &(GGG| JJJ)");

        System.out.println(node3);
        System.out.print("3. ");
        System.out.println(axioms.isAxiom3(node3));

        // 4
        Node node4 = parser.parse("!!!G7'F & (A->B) -> !!!G7'F");

        System.out.println(node4);
        System.out.print("4. ");
        System.out.println(axioms.isAxiom4(node4));

        // 5
        Node node5 = parser.parse("!!!G7'F & (A->B) -> A->B");

        System.out.println(node5);
        System.out.print("5. ");
        System.out.println(axioms.isAxiom5(node5));

        // 6
        Node node6 = parser.parse("J -> J | !(JJJ'098)");

        System.out.println(node6);
        System.out.print("6. ");
        System.out.println(axioms.isAxiom6(node6));

        // 7
        Node node7 = parser.parse("!(JJJ'098) -> J | !(JJJ'098)");

        System.out.println(node7);
        System.out.print("7. ");
        System.out.println(axioms.isAxiom7(node7));

        // 8
        Node node8 = parser.parse("(A -> C) -> (B -> C) -> (A | B -> C)");

        System.out.println(node8);
        System.out.print("8. ");
        System.out.println(axioms.isAxiom8(node8));

        // 9
        Node node9 = parser.parse("(J -> L) -> ((((J -> !L) -> !J))))");

        System.out.println(node9);
        System.out.print("9. ");
        System.out.println(axioms.isAxiom9(node9));

        // 10
        Node node10 = parser.parse("!!! ! ! ! ! GT -> ! !!!!GT");

        System.out.println(node10);
        System.out.print("10. ");
        System.out.println(axioms.isAxiom10(node10));
    }
}
