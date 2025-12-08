package solutions;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

class Day07_1 {
    public static void main(String[] args) throws IOException {
        String[] input = Files.readString(Path.of("src/inputs/day07.txt")).split("\n");
        char[] tachyon = new char[input[0].length()];
        Arrays.fill(tachyon, '.');
        int splits = 0;
        for (int i = 0; i < input.length; i++) {
            String line = input[i];
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == 'S') {
                    tachyon[j] = '|';
                }
                if (line.charAt(j) == '^' && tachyon[j] == '|') {
                    tachyon[j-1] = '|';
                    tachyon[j] = '.';
                    tachyon[j+1] = '|';
                    splits++;
                }
            }
        }
        System.out.println(Arrays.toString(tachyon));
        System.out.println(splits);
        
    }
}