import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ArithmeticTest {
  @Test
  public void testEvalConst() throws Exception {
    Expression expression = Expression.of("92");
    int actual = expression.evaluate();
    assertEquals(92, actual);
  }

  @Test
  public void testEvalOtherConst() throws Exception {
    Expression expression = Expression.of("4");
    int actual = expression.evaluate();
    assertEquals(4, actual);
  }

  @Test
  public void testScopeInteger() throws Exception {
    Expression expression = Expression.of("(42)");
    int actual = expression.evaluate();
    assertEquals(42, actual);
  }

  @Test(expected = ParseException.class)
  public void testInvalidParenthesis() throws Exception {
    Expression.of("((");
  }

  @Test
  public void testSum() throws Exception {
    Expression expression = Expression.of("2+2");
    int actual = expression.evaluate();
    assertEquals(4, actual);
  }

  @Test
  public void testSpacedExpression() throws Exception {
    Expression expression = Expression.of("(2 + 2)");
    int actual = expression.evaluate();
    assertEquals(4, actual);
  }

  @Test
  public void testSumWithParenthesis() throws Exception {
    Expression expression = Expression.of("2+(2 + 2)");
    int actual = expression.evaluate();
    assertEquals(6, actual);
  }

  @Test
  public void testComplicatedExpression() throws Exception {
    Expression expression = Expression.of("((2)+(2 + 2))");
    int actual = expression.evaluate();
    assertEquals(6, actual);
  }

  @Test
  public void testVariableExpression() throws Exception {
    Expression.of("foo");
  }

  @Test
  public void testEvaluateInEnvironment() throws Exception {
    Map<String, Integer> environment = Collections.singletonMap("x", 92);
    Expression expression = Expression.of("x");
    assertEquals(92, expression.evaluate(environment));
  }

  @Test
  public void testScopeCalculation() throws Exception {
      Program program = new Program();
      ArrayList<String> lines = new ArrayList<>();
      lines.add("var x = 2 + 6");
      lines.add("    var y = x + 8");
      program.load(lines);
      assertEquals(program.calculateString("    y"), 16);
  }

  @Test
  public void testScopeCalculation2() throws Exception {
      Program program = new Program();
      ArrayList<String> lines = new ArrayList<>();
      lines.add("var x = 2 + 6");
      lines.add("    var x = 0");
      program.load(lines);
      assertEquals(program.calculateString("x"), 8);
      assertEquals(program.calculateString("    x"), 0  );
  }

  @Test
  public void testScopeCalculation3() throws Exception {
      Program program = new Program();
      ArrayList<String> lines = new ArrayList<>();
      lines.add("var x = 2 + 6");
      lines.add("    var y = x + 8");
      lines.add("    var x = 0");
      lines.add("var q = x + 7");
      lines.add("var z = x + 1");
      program.load(lines);
      assertEquals(program.calculateString("z"), 9);
  }

  @Test
  public void testVariableCalculation() throws Exception {
      Program program = new Program();
      ArrayList<String> lines = new ArrayList<>();
      lines.add("var x = 2 + 6");
      program.load(lines);
      assertEquals(program.calculateString("(2 + 1) + (x)"), 11);
  }

  @Test
  public void testCountOfVariables() throws Exception {
      Program program = new Program();
      ArrayList<String> lines = new ArrayList<>();
      lines.add("var x = 2 + 6");
      lines.add("    var y = x + 8");
      lines.add("    var x = 0");
      lines.add("var q = x + 7");
      lines.add("var z = x + 1");
      VarNumberStatisticFunction statisticFunction = new VarNumberStatisticFunction();
      StatisticFunctionFactory.setFunction(statisticFunction);

      program.load(lines);
      int result = statisticFunction.getResult();
      assertEquals(result, 5);
  }

  @Test
  public void testListOfVariables() throws Exception {
      Program program = new Program();
      ArrayList<String> lines = new ArrayList<>();
      lines.add("var x = 2 + 6");
      lines.add("    var y = x + 8");
      lines.add("    var x = 0");
      lines.add("var q = x + 7");
      lines.add("var z = x + 1");
      PrintVariablesFunction statisticFunction = new PrintVariablesFunction();
      StatisticFunctionFactory.setFunction(statisticFunction);

      program.load(lines);
      ArrayList<String> data = statisticFunction.dataContainer;
      assertEquals(data.get(0), "x");
      assertEquals(data.get(1), "y");
      assertEquals(data.get(2), "x");
      assertEquals(data.get(3), "q");
      assertEquals(data.get(4), "z");
  }

  @Test
  public void testVariableTracing() throws Exception {
      Program program = new Program();
      ArrayList<String> lines = new ArrayList<>();
      lines.add("var x = 2 + 6");
      lines.add("    var y = x + 8");
      lines.add("    var x = 0");
      lines.add("var q = x + 7");
      lines.add("var z = x + 1");
      lines.add("var x = 4");
      VariableTracingFunction statisticFunction = new VariableTracingFunction();
      StatisticFunctionFactory.setFunction(statisticFunction);

      program.load(lines);
      ArrayList<Pair<String, Integer>> data = statisticFunction.dataContainer;
      assertEquals(data.get(0).getKey(), "x");
      assertEquals((int)data.get(0).getValue(), 4);
      assertEquals(data.get(1).getKey(), "q");
      assertEquals((int)data.get(1).getValue(), 15);
      assertEquals(data.get(2).getKey(), "z");
      assertEquals((int)data.get(2).getValue(), 9);

  }
}
