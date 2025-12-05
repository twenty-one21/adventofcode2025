package solutions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Day05_1 {
    public static void main(String[] args) throws IOException {
        String input = Files.readString(Path.of("src/inputs/day05.txt"));
//        Stream<String> stream = input.lines();
        List<String> ranges = Arrays.asList(input.split("\\.")[0].split("\n"));
        List<String> ingredients = Arrays.asList(input.split("\\.\n")[1].split("\n"));
//        ranges.stream().forEach(range -> idk why not work im bad at stream
//            ingredients.stream().filter(ingredient ->
//                Long.parseLong(range.split("-")[0]) <= Long.parseLong(ingredient)
//                &&
//                Long.parseLong(range.split("-")[1]) >= Long.parseLong(ingredient)
//            ).toList()
//        );
        System.out.println(ingredients);
        int spoiled = 0;
        checkIngredient: for (String ingredient : ingredients) {
            long asNum = Long.parseLong(ingredient);
            //assume it is spoiled
            for (String range : ranges) {
                if (Long.parseLong(range.split("-")[0]) <= asNum && asNum <= Long.parseLong(range.split("-")[1])) {
                    //not spoiled
                    continue checkIngredient;
                }
            }
            //not in any range therefore spoiled
            spoiled++;
        }
        System.out.println("spoiled " + spoiled);
        System.out.println("fresh " + (ingredients.size() - spoiled));
    }
}