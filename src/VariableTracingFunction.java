import javafx.util.Pair;

public class VariableTracingFunction extends StatisticFunction<Pair<String, Integer>> {

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
        if (expression.getCurrentIndent() != 0) {
            return;
        }

        String varName = expression.getVarName();
        Boolean isFound = false;

        for (int i = 0; i < dataContainer.size(); i++) {
            if (dataContainer.get(i).getKey().equals(varName)) {
                dataContainer.remove(i);
                dataContainer.add(i, new Pair<>(varName, expression.getCurrentExpressionValue()));
                isFound = true;
                break;
            }
        }

        if (!isFound) {
            dataContainer.add(new Pair<>(varName, expression.getCurrentExpressionValue()));
        }
    }

    @Override
    public void calculate(Expression expression) {
    }
}
