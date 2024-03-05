package expression;

public class Multiply extends AbstractExpression {
    Expression exp1;
    Expression exp2;
    char s = '*';

    public Multiply (Expression exp1, Expression exp2) {
        super(exp1, exp2);
        super.s = s;
    }

    public int getValue(int a, int b) {
        return a * b;
    }
}
