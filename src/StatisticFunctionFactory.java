import java.util.ArrayList;

public class StatisticFunctionFactory {
    private static ArrayList<StatisticFunction> functions = new ArrayList<>();

    public static ArrayList<StatisticFunction> make() {
        return functions;
    }

    public static void addFunction(StatisticFunction function) {
        StatisticFunctionFactory.functions.add(function);
    }
}
