public class ConstantExpression extends Expression {

  {
    statisticFunction = StatisticFunctionFactory.make();
  }

  private final int value;

  public ConstantExpression(int value) {
    this.value = value;

    if (statisticFunction != null) {
      statisticFunction.calculate(this);
    }
  }

  @Override
  int evaluate() {
    return value;
  }

  public static ConstantExpression of(String s) {
    try {
      return new ConstantExpression(Integer.valueOf(s));
    } catch (NumberFormatException e) {
      throw new ParseException("Wrong number format for: " + s, e);
    }
  }
}
