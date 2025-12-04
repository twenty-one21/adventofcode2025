package solutions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class Day03_1 {
    public static char highestBattery(String bank) {
        char highest = 1;
        for (int i = 0; i < bank.length(); i++) {
            if (bank.charAt(i) > highest) highest = bank.charAt(i);
        }
        return highest;
    }
    public static void main(String[] args) throws IOException {
        String input = Files.readString(Path.of("src/inputs/day03.txt"));
        String[] banks = input.split("\n");
        int sum = 0;
        for (String bank : banks) {
            int b1, b2;
            b1 = highestBattery(bank.substring(0, bank.length()-1));
            b2 = highestBattery(bank.substring(bank.indexOf(b1)+1));
            b1 = b1 - '0';
            b2 = b2 - '0';
            System.out.printf("b1:%d,b2:%d,joltage:%d\n",b1,b2,10*b1+b2);
            sum += 10*b1 + b2;
        }
        System.out.println(sum);
    }
}