package solutions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

class Day09_1 {
    static long area(int[] c1, int[] c2) {
        long length = (Math.abs(c2[0] - c1[0]) + 1);
        long height = (Math.abs(c2[1] - c1[1]) + 1);
        return Math.abs(length * height);
    }
    public static void main(String[] args) throws IOException {
        //2147470010 is wrong ??
        List<int[]> coords = Arrays.stream(Files.readString(Path.of("src/inputs/day09.txt")).split("\n"))
                .map(a -> Arrays.stream(a.split(",")).map(Integer::parseInt).mapToInt(i->i).toArray())
                .toList();
        long maxArea = 0;
        for (int[] coord1 : coords) {
            for (int[] coord2 : coords) {
                if (area(coord1, coord2) > maxArea) {
                    maxArea = area(coord1, coord2);
                    System.out.println("new area: " + Arrays.toString(coord1) + "|" + Arrays.toString(coord2));
                    System.out.println(maxArea);
                };
            }
        }
        System.out.println(maxArea);
    }
}