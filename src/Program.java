import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Program {
    /**
     * Contains environment on each level of program
     */
    Map<Integer, Map<String, Integer>> levelEnvironment = new HashMap<>();

    int currentIndent = 0;

    void load(ArrayList<String> lines) {
        for (String line : lines) {
            setCurrentIndent(line);
            parseLine(line.trim());
        }
    }

    int calculateString(String line) {
        setCurrentIndent(line);
        return parseLine(line.trim());
    }

    private int parseLine(String line) {
        Pattern re = Pattern.compile("var\\s+(\\w+)\\s*=\\s*(.*)");
        Matcher match = re.matcher(line);
        Expression expression;
        String variable;

        if (match.matches()) {
            variable = match.group(1);
            expression = Expression.of(match.group(2));

            Integer val = calculateInEnEnvironment(expression);
            putToEnvironment(currentIndent, variable, val);

            System.out.println(variable + " == " + val);
            return val;
        } else {
            expression = Expression.of(line);
            return calculateInEnEnvironment(expression);
        }
    }

    private int calculateInEnEnvironment(Expression expression) {
        Map<String, Integer> e = levelEnvironment.get(currentIndent);
        Integer val;
        if (e != null) {
            val = expression.evaluate(e);
        } else {
            e = levelEnvironment.get(currentIndent - 1);
            if (e != null) {
                val = expression.evaluate(e);
            } else {
                val = expression.evaluate();
            }
        }

        return val;
    }

    private void putToEnvironment(Integer currentIndentLevel, String varName, Integer value) {
        Map<String, Integer> e = levelEnvironment.get(currentIndentLevel);
        if (e == null) {
            Map<String, Integer> env = new HashMap<>();
            env.put(varName, value);
            levelEnvironment.put(currentIndentLevel, env);
        } else {
            e.put(varName, value);
        }
    }

    public void setCurrentIndent(String line) {
        currentIndent = 0;

        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) != ' ') {
                break;
            }

            if ((i + 1) % 4 == 0) {
                currentIndent = (i + 1) / 4;
            }
        }
    }
}
