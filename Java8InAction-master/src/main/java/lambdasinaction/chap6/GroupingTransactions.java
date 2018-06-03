package lambdasinaction.chap6;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

public class GroupingTransactions {

    public static List<Transaction> transactions = Arrays.asList( new Transaction(Currency.EUR, 1500.0),
                                                                  new Transaction(Currency.USD, 2300.0),
                                                                  new Transaction(Currency.GBP, 9900.0),
                                                                  new Transaction(Currency.EUR, 1100.0),
                                                                  new Transaction(Currency.JPY, 7800.0),
                                                                  new Transaction(Currency.CHF, 6700.0),
                                                                  new Transaction(Currency.EUR, 5600.0),
                                                                  new Transaction(Currency.USD, 4500.0),
                                                                  new Transaction(Currency.CHF, 3400.0),
                                                                  new Transaction(Currency.GBP, 3200.0),
                                                                  new Transaction(Currency.USD, 4600.0),
                                                                  new Transaction(Currency.JPY, 5700.0),
                                                                  new Transaction(Currency.EUR, 6800.0) );
    public static void main(String ... args) {
        groupImperatively();
        groupFunctionally();

    }

    private static void groupImperatively() {
        Map<Currency, List<Transaction>> transactionsByCurrencies = new HashMap<>();//建立累积交易分组的 Map，分组使用Map最好
        for (Transaction transaction : transactions) {
            Currency currency = transaction.getCurrency();//提取Transaction 的货币
            List<Transaction> transactionsForCurrency = transactionsByCurrencies.get(currency);//根据货币获取分组条目，根据类别获取Map
            if (transactionsForCurrency == null) {//如果分组Map中没有这种货币的条目，就创建一个,没有就新增分组，还要给list空间
                    transactionsForCurrency = new ArrayList<>();
                transactionsByCurrencies.put(currency, transactionsForCurrency);
            }
            transactionsForCurrency.add(transaction);//将当前遍历的Transaction 加入同一货币的Transaction 的List，然后就把分组内存存进去
        }

        System.out.println(transactionsByCurrencies);
    }

    private static void groupFunctionally() {
        Map<Currency, List<Transaction>> transactionsByCurrencies = transactions.stream().collect(groupingBy(Transaction::getCurrency));
        System.out.println(transactionsByCurrencies);
    }

    public static class Transaction {
        private final Currency currency;
        private final double value;

        public Transaction(Currency currency, double value) {
            this.currency = currency;
            this.value = value;
        }

        public Currency getCurrency() {
            return currency;
        }

        public double getValue() {
            return value;
        }

        @Override
        public String toString() {
            return currency + " " + value;
        }
    }

    public enum Currency {
        EUR, USD, JPY, GBP, CHF
    }
}
