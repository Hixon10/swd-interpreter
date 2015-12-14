
public class StatisticFunctionFactory {
    private static StatisticFunction function;

    public static StatisticFunction make() {
        return function;
    }

    public static void setFunction(StatisticFunction function) {
        StatisticFunctionFactory.function = function;
    }
}
