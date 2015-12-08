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

    public static Optional<AssignmentExpression> parse(String line) {
        Pattern re = Pattern.compile("var\\s+(\\w+)\\s*=\\s*(.*)");
        Matcher match = re.matcher(line);

        if (match.matches()) {
            String variable = match.group(1);
            Expression expression = Expression.of(match.group(2));
            AssignmentExpression assignmentExpression = new AssignmentExpression(variable, expression);
            return Optional.of(assignmentExpression);
        }

        return Optional.empty();
    }

    public AssignmentExpression(String varName, Expression expression) {
        this.varName = varName;
        this.expression = expression;

        if (statisticFunction != null) {
            statisticFunction.calculate(this);
        }
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
}
