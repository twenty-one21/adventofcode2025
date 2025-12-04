package solutions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class Day03_2 {
    public static int highestBattery(String bank) {
        char highest = 1;
        for (int i = 0; i < bank.length(); i++) {
            if (bank.charAt(i) > highest) highest = bank.charAt(i);
        }
        return +(highest - '0');
    }
    public static int getIndexOfString(String str, int i) {
        char asChar = (char) (i + '0');
        return str.indexOf(asChar);
    }
    public static int[] highestJoltage(String bank, int[] joltage, int index) {
        if (index == 12) {
            return joltage;
        }
        joltage[index] = highestBattery(bank.substring(0, bank.length()-11+index)); //123456789123456789 -- must not be last 12 for first
//        System.out.println(bank.substring(0, bank.length()-12+index));
        return highestJoltage(bank.substring(getIndexOfString(bank, joltage[index]) + 1), joltage, index + 1);
    }
    public static int[] highestJoltage(String bank) {
        return highestJoltage(bank, new int[12], 0);
    }
    public static void main(String[] args) throws IOException {
        String input = Files.readString(Path.of("src/inputs/day03.txt"));
        String[] banks = input.split("\n");
        long sum = 0;
        for (String bank : banks) {
            int[] joltage = highestJoltage(bank);
            for (int i = 0; i < 12; i++) {
                sum += (long) joltage[i] * (long) Math.pow(10, 11-i);
            }
        }
        System.out.println(sum);
    }
}