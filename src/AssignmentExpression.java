import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AssignmentExpression extends Expression  {

    {
        statisticFunction = StatisticFunctionFactory.make();
    }

    private final String varName;

    private final Expression expression;
    private int currentIndent;
    private int currentExpressionValue = 0;

    public static Optional<AssignmentExpression> parse(String line, int currentIndent) {
        Pattern re = Pattern.compile("var\\s+(\\w+)\\s*=\\s*(.*)");
        Matcher match = re.matcher(line);

        if (match.matches()) {
            String variable = match.group(1);
            Expression expression = Expression.of(match.group(2));
            AssignmentExpression assignmentExpression = new AssignmentExpression(variable, expression, currentIndent);
            return Optional.of(assignmentExpression);
        }

        return Optional.empty();
    }

    public AssignmentExpression(String varName, Expression expression, int currentIndent) {
        this.varName = varName;
        this.expression = expression;
        this.currentIndent = currentIndent;
    }

    @Override
    public int evaluate() {
        return expression.evaluate();
    }

    @Override
    public int evaluate(Map<String, Integer> environment) {
        return expression.evaluate(environment);
    }

    public String getVarName() {
        return varName;
    }

    public Expression getExpression() {
        return expression;
    }

    public int getCurrentIndent() {
        return currentIndent;
    }

    public int getCurrentExpressionValue() {
        return currentExpressionValue;
    }

    public void setCurrentExpressionValue(int currentExpressionValue) {
        this.currentExpressionValue = currentExpressionValue;
        if (statisticFunction != null) {
            statisticFunction.calculate(this);
        }
    }
}
