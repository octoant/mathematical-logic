import java.util.*;

public class Solution implements Program {

    private static final String SIGH = "|-";
    private static Scanner input;

    public static void main(String[] arguments) {
        input = new Scanner(System.in);

        String insanis = input.nextLine();
        // checks input string
        Program.checkInputString(insanis);
        new Solution().run(insanis);
    }

    private static final String REGULAR = "\\|-";
    private static final char SPACE = ' ';
    private static final String COMMASPACE = ", ";

    private void run(String expr) {
        String hypo, evidence, hypos[];

        if (startsWith(expr, SIGH)) {
            hypo = ""; evidence = expr.substring(2); hypos = new String[]{""};
        } else {
            String[] temporary = expr.split(REGULAR);

            hypo = temporary[0].charAt(temporary[0].length() - 1) != SPACE ? temporary[0] + " " : temporary[0];
            evidence = temporary.length != 1 ? temporary[1] : "";
            hypos = temporary[0].contains(COMMASPACE) ? temporary[0].split(COMMASPACE) : new String[]{temporary[0]};
        }

        String insanis = input.nextLine();
        if (insanis == null) return;

        String activeExpr = replaceDouble(insanis, "\t", "\n", "").replaceAll(" ", "");

        evidence = this.replaceChars(evidence, " ", "");

        List<String> list = new ArrayList<>();
        Map<Integer, String> map = new HashMap<>();

        Parser parser = new ExpressionParser();

        String temporary;
        while (!activeExpr.equals(evidence)) {
            if (!activeExpr.equals("") && !list.contains(activeExpr)) {
                temporary = parser.parse(activeExpr);
                list.add(temporary);
                map.put(list.indexOf(temporary), temporary);
            }

            if (input.hasNextLine()) insanis = input.nextLine();
            else break;

            if (insanis == null) break;
            temporary = replaceDouble(insanis, "\t", "\n", "");

            if (temporary.replace(" ", "").equals("")) break;
            activeExpr = temporary.replaceAll(" ", "");
        }

        if (!list.contains(activeExpr) && !activeExpr.equals("")) {
            list.add(parser.parse(activeExpr));
        }

        Writer writer = new Writer.Builder().setList(list).setMap(map).setHypos(hypos).build();
        if (!evidence.equals("")) System.out.println(hypo + "|- !!" + parser.parse(evidence));
        writer.write();
    }
}
