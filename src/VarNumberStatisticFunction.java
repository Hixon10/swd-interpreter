

public class VarNumberStatisticFunction extends StatisticFunction<Integer> {

    private int result = 0;

    @Override
    public void calculate(ConstantExpression expression) {
    }

    @Override
    public void calculate(SumExpression expression) {
    }

    @Override
    public void calculate(VariableExpression expression) {
    }

    @Override
    public void calculate(AssignmentExpression expression) {
        result++;
    }

    @Override
    public void calculate(Expression expression) {
    }

    @Override
    public Integer getResult() {
        return result;
    }
}
