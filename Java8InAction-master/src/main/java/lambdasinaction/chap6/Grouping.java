package lambdasinaction.chap6;

import java.util.*;

import static java.util.stream.Collectors.*;
import static lambdasinaction.chap6.Dish.menu;

public class Grouping {

    enum CaloricLevel { DIET, NORMAL, FAT };

    public static void main(String ... args) {
        System.out.println("Dishes grouped by type: " + groupDishesByType());
        Map<CaloricLevel, List<Dish>> caloricLevelListMap = groupDishesByCaloricLevel();
        System.out.println("Dishes grouped by caloric level: "+caloricLevelListMap );
        System.out.println("Dishes grouped by type and caloric level: " + groupDishedByTypeAndCaloricLevel());
        System.out.println("Count dishes in groups: " + countDishesInGroups());
        System.out.println("Most caloric dishes by type: " + mostCaloricDishesByType());
        System.out.println("Most caloric dishes by type: " + mostCaloricDishesByTypeWithoutOprionals());
        System.out.println("Sum calories by type: " + sumCaloriesByType());
        Map<Dish.Type, Set<CaloricLevel>> typeSetMap1 = caloricLevelsByType();
        System.out.println("Caloric levels by type: " +typeSetMap1);
        Map<Dish.Type, Set<CaloricLevel>> typeSetMap = grounpByHash();
        System.out.println("typeSetMap levels by type: " + typeSetMap);

        
    }



    private static Map<Dish.Type, List<Dish>> groupDishesByType() {
        return menu.stream().collect(groupingBy(Dish::getType));
    }

    private static Map<CaloricLevel, List<Dish>> groupDishesByCaloricLevel() {
        return menu.stream().collect(
                groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                } ));
    }

    private static Map<Dish.Type, Map<CaloricLevel, List<Dish>>> groupDishedByTypeAndCaloricLevel() {
        return menu.stream().collect(
                groupingBy(Dish::getType,//一级分类
                        groupingBy((Dish dish) -> {//二级分类
                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        } )
                )
        );
    }

    private static Map<Dish.Type, Long> countDishesInGroups() {
        return menu.stream().collect(groupingBy(Dish::getType, counting()));
    }

    private static Map<Dish.Type, Optional<Dish>> mostCaloricDishesByType() {
        return menu.stream().collect(
                groupingBy(Dish::getType,
                        reducing((Dish d1, Dish d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)));
    }

    private static Map<Dish.Type, Dish> mostCaloricDishesByTypeWithoutOprionals() {
        return menu.stream().collect(
                groupingBy(Dish::getType,
                        collectingAndThen(
                                reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2),
                                Optional::get)));
    }

    private static Map<Dish.Type, Integer> sumCaloriesByType() {
        return menu.stream().collect(groupingBy(Dish::getType,
                summingInt(Dish::getCalories)));
    }

    private static Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType() {
        return menu.stream().collect(
                groupingBy(Dish::getType, mapping(
                        dish -> { if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                        else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                        else return CaloricLevel.FAT; },
                        toSet() )));// toSet()去重复，注意第一个参数，只有一个参数，和第二个参数的区别
    }

    private static Map<Dish.Type, Set<CaloricLevel>> grounpByHash() {
        return menu.stream().collect(
                groupingBy(Dish::getType, mapping(
                        dish -> { if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                        else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                        else return CaloricLevel.FAT; },
                        toCollection(HashSet::new) )));
    }

}
