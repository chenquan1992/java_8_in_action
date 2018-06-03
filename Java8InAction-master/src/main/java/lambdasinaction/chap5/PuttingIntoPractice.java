package lambdasinaction.chap5;

import lambdasinaction.chap5.*;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;

/**
 * 第三个问题：能不能直接返回这个List<Transaction> transactions，不要返回List<Trader> traders，正序可以，倒序不行
 */
public class PuttingIntoPractice{
    public static void main(String ...args){    
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
		
		List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300), 
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),	
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
        );

//        Integer cambridge = transactions.stream()
//                .filter(p -> p.getTrader().getCity().equals("Cambridge"))
//                .map(p -> p.getValue())
//                .reduce(0, Integer::max);
//        System.out.println("cambridge:"+cambridge);
//
//
//  // Query 1: Find all transactions from year 2011 and sort them by value (small to high).
//        List<Transaction> tr2011 = transactions.stream()
//                .filter(transaction -> transaction.getYear() == 2011)
//                .sorted(comparing(Transaction::getValue).reversed()) //comparing(Transaction::getValue) 正序排序，   .reversed()反转顺序,还可以直接 .sorted()只要list可以直接排序
//                .collect(toList());
//        System.out.println(tr2011);

        List<Transaction> cambridge111 = transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .sorted(comparing(p -> p.getTrader().getName())) //这里，能不能倒序啊？
                .collect(toList());

//        // Query 2: What are all the unique cities where the traders work?
//        List<String> cities =
//                transactions.stream()
//                        .map(transaction -> transaction.getTrader().getCity())
//                        .distinct()
//                        .collect(toList());
//        System.out.println(cities);
//
//        // Query 3: Find all traders from Cambridge and sort them by name.
//
//        List<Trader> traders =
//                transactions.stream()
//                        .map(Transaction::getTrader)
//                        .filter(trader -> trader.getCity().equals("Cambridge"))
//                        .distinct()
//                        .sorted(comparing(Trader::getName))
//                        .collect(toList());
//        System.out.println(traders);
//
//
//        // Query 4: Return a string of all traders’ names sorted alphabetically.
//
//        String traderStr =
//                transactions.stream()
//                        .map(transaction -> transaction.getTrader().getName())
//                        .distinct()
//                        .sorted()
//                        .reduce("", (n1, n2) -> n1 + n2);
//        System.out.println(traderStr);
//
//        // Query 5: Are there any trader based in Milan?
//
//        boolean milanBased =
//                transactions.stream()
//                        .anyMatch(transaction -> transaction.getTrader()
//                                .getCity()
//                                .equals("Milan")
//                        );
//        System.out.println(milanBased);
//
//
//        // Query 6: Update all transactions so that the traders from Milan are set to Cambridge.
//        transactions.stream()
//                .map(Transaction::getTrader)
//                .filter(trader -> trader.getCity().equals("Milan"))
//                .forEach(trader -> trader.setCity("Cambridge"));
//        System.out.println(transactions);
//
//
//        // Query 7: What's the highest value in all the transactions?
//        int highestValue =
//                transactions.stream()
//                        .map(Transaction::getValue)
//                        .reduce(0, Integer::max);
//        System.out.println(highestValue);
    }


    }
