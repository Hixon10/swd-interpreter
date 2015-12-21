import java.util.ArrayList;

public abstract class StatisticFunction<T> {
    ArrayList<T> dataContainer = new ArrayList<>();

    abstract void calculate(ConstantExpression expression);
    abstract void calculate(SumExpression expression);
    abstract void calculate(VariableExpression expression);
    abstract void calculate(AssignmentExpression expression);
    abstract void calculate(Expression expression);
}
