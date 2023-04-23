package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

interface A {
    int aMthod(String s);
}
public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        IntStream instream1 = IntStream.of(1, 2, 3, 5, 8);
        Stream<String> strings = Stream.of("Nothing", "worths");
        List<Integer> list = Arrays.asList(10,20,33,43,54,68);
        list.stream()
                .filter(a->a%2==0)
                .map(a->a*11)
                .sorted((a, b) -> b-a)
                .limit(1)
                .forEach(System.out::println);
        Stream<String> gameNames = Stream.of("azur lane", "ggst", "starcraft");
        boolean starcraftInGameNames = gameNames.anyMatch(a->a.equals("starcraft"));
        System.out.println(starcraftInGameNames);
    }
}