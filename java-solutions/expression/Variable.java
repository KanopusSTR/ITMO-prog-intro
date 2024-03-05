package expression;

import java.util.Objects;

public class Variable extends AbsMonoExpression {
    String s;

    public Variable (String s) {
        super(s);
        this.s = s;
    }

    @Override
    public int evaluate(int k) {
        return k;
    }

    public String toString() {
        return s;
    }

    public char getS() {
        throw new UnsupportedOperationException("getS");
    }

    public boolean equals(Object obj) {
        if (obj instanceof AbstractExpression) {
            return false;
        }
        if (obj instanceof Expression) {
            Expression exp = (Expression) obj;
            return exp.toString().equals(s);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(s);
    }
}
