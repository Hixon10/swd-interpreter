

public class PrintVariablesFunction extends StatisticFunction<String> {

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
        dataContainer.add(expression.getVarName());
    }

    @Override
    public void calculate(Expression expression) {
    }
}
