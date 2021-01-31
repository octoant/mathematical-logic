public class ExpressionParser implements Parser {

    @Override
    public String parse(String exprAc) {
        Checker.restore();
        return LogicalOperation.impl(exprAc);
    }
}
