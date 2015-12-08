import java.util.Map;

public class VariableExpression extends Expression {

  {
    statisticFunction = StatisticFunctionFactory.make();
  }

  private final String name;

  private VariableExpression(String name) {
    this.name = name;

    if (statisticFunction != null) {
      statisticFunction.calculate(this);
    }
  }

  public static VariableExpression of(String s) {
    if (!s.matches("\\w+")) {
      throw new ParseException("Invalid variable name: " + s);
    }
    return new VariableExpression(s);
  }

  @Override
  int evaluate() {
    return 0;
  }

  @Override
  public int evaluate(Map<String, Integer> environment) {
    return environment.get(name);
  }
}
