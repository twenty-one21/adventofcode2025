package solutions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Day06_1 {
    public static void main(String[] args) throws IOException {
        String input = Files.readString(Path.of("src/inputs/day06.txt"));
        //collect input
        List<List<String>> inputlines = Arrays.stream(input.split("\n")).map(line -> Arrays.stream(line.split(" ")).filter(num -> !num.isEmpty()).toList()).toList();
//        Stream<String> stream = input.lines();
        for (List<String> line : inputlines) {
            System.out.println(line.toString());
        }

        //calculate
        int numberLineCount = 4;
        long grandTotal = 0;
        for (int i = 0; i < inputlines.get(0).size(); i++) {
            String operator = inputlines.get(numberLineCount).get(i);
            if (operator.equals("*")) { //check operator
                long product = 1;
                for (int j = 0; j < numberLineCount; j++) {
                    product *= Long.parseLong(inputlines.get(j).get(i));
                }
                grandTotal += product;
            } else if (operator.equals("+")) {
                long sum = 0;
                for (int j = 0; j < numberLineCount; j++) {
                    sum += Long.parseLong(inputlines.get(j).get(i));
                }
                grandTotal += sum;
            }
        }
        System.out.println("grand total " + grandTotal);
        System.out.println("Hello world!");
    }
}