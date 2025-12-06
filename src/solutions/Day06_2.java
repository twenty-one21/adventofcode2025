package solutions;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class Day06_2 {
    public static void main(String[] args) throws IOException {
        String input = Files.readString(Path.of("src/inputs/day06.txt"));
        //collect input
        List<String> inputlines = new ArrayList<>(Arrays.stream(input.split("\n")).toList());

        for (String line : inputlines) { //print current lines
            System.out.println(line);
        }
        System.out.println("\n\n\n");
        //calculate
        int numberLineCount = 4;
        long grandTotal = 0;
        List<String> operators = Arrays.stream(input.split("\n")).map(line -> Arrays.stream(line.split(" ")).filter(num -> !num.isEmpty()).toList()).toList().get(numberLineCount);
        for (int opNum = operators.size() - 1; opNum >= 0; opNum--) { //For every operator
            String operator = operators.get(opNum);
            List<Long> numbers = new ArrayList<>();
            System.out.println("starting loop");
            infloop: while (true) {
                //get current 1width number column
                String numberReading = "";
                for (int j = 0; j < numberLineCount; j++) {
                    String currentLine = inputlines.get(j);
                    try {
                        numberReading += currentLine.charAt(currentLine.length() - 1);
                    } catch (StringIndexOutOfBoundsException e) {
                        break;
                    }
                }
                if (numberReading.isBlank()) {
                    //remove last column
                    for (int j = 0; j < numberLineCount; j++) {
                        try {
                            inputlines.set(j, inputlines.get(j).substring(0, inputlines.get(j).length()-1));
                        } catch (StringIndexOutOfBoundsException e) {
                            break;
                        }

                    }
                    System.out.println("ending loop");
                    break infloop;
                }
                numbers.add(Long.parseLong(numberReading.strip()));
                System.out.println("number reading " + Long.parseLong(numberReading.strip()));
                //remove last column
                try {
                    for (int j = 0; j < numberLineCount; j++) {
                        inputlines.set(j, inputlines.get(j).substring(0, inputlines.get(j).length()-1));
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    //idc
                }
            }
            if (operator.equals("+")) {
                Optional<Long> sum = numbers.stream().reduce(Long::sum);
                grandTotal += sum.isEmpty()?0:sum.get();
                System.out.println("sum: " + (sum.isEmpty()?0:sum.get()));
            }
            else if (operator.equals("*")) {
                Optional<Long> sum = numbers.stream().reduce((a,b) -> a*b);
                grandTotal += sum.isEmpty()?0:sum.get();
                System.out.println("prod: " + (sum.isEmpty()?0:sum.get()));
            }
            else {
                System.out.println("cry");
            }
            for (String line : inputlines) { //print current lines
                System.out.println(line);
            }
            System.out.println("opnum: " + opNum + "\n\n\n");
        }

        System.out.println("grand total " + grandTotal);
    }
}