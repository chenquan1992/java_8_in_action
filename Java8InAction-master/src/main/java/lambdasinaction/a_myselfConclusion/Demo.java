package lambdasinaction.a_myselfConclusion;

/**
 * Created with Chenquan.
 * Description:
 * Date: 2018-05-31
 * Time: 19:31
 */

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Demo {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<Product>();
        products.add(new Product("p1", "mobile 1", 1000, 2, "Manufacturer 1"));
        products.add(new Product("p2", "mobile 2", 700, 4, "Manufacturer 1"));
        products.add(new Product("p3", "laptop 1", 1200, 6, "Manufacturer 2"));
        products.add(new Product("p4", "laptop 2", 7000, 9, "Manufacturer 2"));
        products.add(new Product("p5", "laptop 3", 7000, 9, "Manufacturer 2"));
        System.out.println("Or Condition");
        products.stream()
                .filter(p -> p.getId().equalsIgnoreCase("p1") || p.getId().equalsIgnoreCase("p3"))
                .forEach(p -> {
            System.out.println(p.toString());
            System.out.println("======================");
        });
        System.out.println("And Condition");
        System.out.println("==============================================================================");
        List<Long> collect = products.stream()
                .filter(p -> {
                    System.out.println("测试打印次数:过滤");
                    return p.getPrice() > 700 && p.getPrice() < 7000;
                })
                .map((Product p )-> {
                    System.out.println("测试打印次数：map");
                    return p.getPrice();
                })
                .collect(toList());
        System.out.println(collect);
    }
}