import java.util.Map;

public class SumExpression extends Expression {
  private final Expression left;
  private final Expression right;

  public SumExpression(Expression left, Expression right) {
    this.left = left;
    this.right = right;
  }

  @Override
  int evaluate() {
    return left.evaluate() + right.evaluate();
  }

  @Override
  public int evaluate(Map<String, Integer> environment) {
    return left.evaluate(environment) + right.evaluate(environment);
  }
}
