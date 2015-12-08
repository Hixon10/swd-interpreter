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

            putToEnvironment(currentIndent, variable, val);
            System.out.println(variable + " == " + val);
            return 0;
        } else {
            expression = Expression.of(line);
            return expression.evaluate(levelEnvironment.get(currentIndent));
        }
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

    public static void main(String[] args) {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("var x = 2 + 6");
        lines.add("    var y = x + 8");
        lines.add("    var x = 0");
        lines.add("    var q = x + 7");
        lines.add("var z = x + 1");
        lines.add("   (2 + 1) + (12)");

        Program program = new Program();
        program.load(lines);
    }
}
