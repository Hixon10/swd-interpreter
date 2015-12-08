

public interface StatisticFunction {
    int calculate(ConstantExpression expression);
    int calculate(SumExpression expression);
    int calculate(VariableExpression expression);
    int calculate(AssignmentExpression expression);
    int calculate(Expression expression);
    int getResult();
}
