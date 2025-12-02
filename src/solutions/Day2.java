package solutions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class Day2 {
    public static void main(String[] args) throws IOException {
        String input = Files.readString(Path.of("src/inputs/day2.txt"));
        ArrayList<Long> invalids = new ArrayList<>();
//        Stream<String> stream = input.lines();
        String[] ranges = input.split(",");
        for (String range : ranges) {
            String start = range.split("-")[0];
            String end = range.split("-")[1];
            for (long i = Long.parseLong(start); i <= Long.parseLong(end); i++) {
                //1: odd
                //log 1 = 0
                //11: even
                //log 11 = 1.04
                //111: odd
                //log 111 = 2.04
                //log_10
                if ((long) Math.floor(Math.log10(i)) % 2 == 1) {
                    String stringed = String.valueOf(i);
                    if (
                            stringed.substring(0, stringed.length() / 2)
                            .equals(stringed.substring(stringed.length() / 2))
                    ) {
                        invalids.add(i);
                    }
                }
            }
        }
        long sum = invalids.stream().mapToLong(i -> i).sum();
        System.out.println(sum);
    }
}