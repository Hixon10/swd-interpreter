import java.util.Map;

public class VariableExpression extends Expression {

  {
    statisticFunctions = StatisticFunctionFactory.make();
  }

  private final String name;

  private VariableExpression(String name) {
    this.name = name;

    for (StatisticFunction statisticFunction : statisticFunctions) {
      statisticFunction.calculate(this);
    }
  }

  public static VariableExpression of(String s) {
    if (!s.matches("\\w+")) {
      throw new ParseException("Invalid variable name: " + s);
    }
    return new VariableExpression(s);
  }

  String getName() {
    return name;
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
