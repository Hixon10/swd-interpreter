
public class StatisticFunctionFactory {
    private static StatisticFunction function = new VarNumberStatisticFunction();

    public static StatisticFunction make() {
        return function;
    }
}
