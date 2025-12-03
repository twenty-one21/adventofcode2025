package solutions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

class Day2_2 {
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

                String stringed = String.valueOf(i);
                checkNum: for (int chunkLength = 1; chunkLength <= stringed.length() / 2; chunkLength++) { //length 1, 2, 3, ..., half of n
                    if (stringed.length() % chunkLength != 0) continue checkNum;
                    for (int chunkNum = 0; chunkNum < stringed.length() / chunkLength; chunkNum++) { //chunk 1 ... chunk (n/length)
                        String chunk1 = stringed.substring(0, chunkLength);
                        String chunk2 = stringed.substring(chunkLength * chunkNum, chunkLength * (chunkNum+1));
//                        System.out.printf("i:%d\nchunklength:%d\nchunk1:%s\nchunk2:%s\n\n",i,chunkLength,chunk1,chunk2);
                        if (!chunk1.equals(chunk2)) {
                            continue checkNum; //exit bc we know that this chunklength doesnt work
                        }
                    }
//                    System.out.println("Adding " + i + " to invalid\n");
                    invalids.add(i); //if not continued already then this chunklength works
                    break checkNum; //finish checking this num

                }
            }
        }
        long sum = invalids.stream().mapToLong(i -> i).sum();
        System.out.println(sum);
//        System.out.println(invalids);
    }
}