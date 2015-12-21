

public class VarNumberStatisticFunction extends StatisticFunction<Integer> {

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
        if (dataContainer.size() == 0) {
            dataContainer.add(1);
        } else {
            Integer val = dataContainer.get(0);
            dataContainer.remove(0);
            dataContainer.add(val + 1);
        }
    }

    @Override
    public void calculate(Expression expression) {
    }
}
