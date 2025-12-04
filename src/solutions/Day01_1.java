package solutions;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class Day01_1 {
    public static void main(String[] args) throws IOException {
        String input = Files.readString(Path.of("src/inputs/day01.txt"));
        String[] lines = input.split("\n");
        int zeroes = 0;
        int rotation = 50;
        for (String line : lines) {
            char dir = line.charAt(0);
            int dist = Integer.parseInt(line.substring(1));
            rotation += (dir=='L'?-1:1) * dist;
            rotation %= 100;
            if (rotation == 0) zeroes++;
        }
        System.out.println(zeroes);
    }
}