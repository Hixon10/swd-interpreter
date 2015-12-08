

public class VarNumberStatisticFunction implements StatisticFunction {

    private int result = 0;

    @Override
    public int calculate(ConstantExpression expression) {
        return 0;
    }

    @Override
    public int calculate(SumExpression expression) {
        return 0;
    }

    @Override
    public int calculate(VariableExpression expression) {
        return 0;
    }

    @Override
    public int calculate(AssignmentExpression expression) {
        result++;
        return 0;
    }

    @Override
    public int calculate(Expression expression) {
        return 0;
    }

    @Override
    public int getResult() {
        return result;
    }
}
