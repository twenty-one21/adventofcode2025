package solutions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Day09_2 {
    static long area(int[] c1, int[] c2) {
        long length = (Math.abs(c2[0] - c1[0]) + 1);
        long height = (Math.abs(c2[1] - c1[1]) + 1);
        return Math.abs(length * height);
    }
    static boolean isBounding(int[][] image, int[] c1, int[] c2) {
        int minx = Math.min(c1[0], c2[0]);
        int maxx = Math.max(c1[0], c2[0]);
        int miny = Math.min(c1[1], c2[1]);
        int maxy = Math.max(c1[1], c2[1]);
        if (!Arrays.stream(Arrays.copyOfRange(image[c1[1]], minx,maxx)).allMatch(x -> x == 1)) { //if every x from c1[0] to c2[0] is not 1, break
            return false;
        };
        if (!Arrays.stream(Arrays.copyOfRange(image[c2[1]], minx,maxx)).allMatch(x -> x == 1)) { //if every x from c1[0] to c2[0] is not 1, break
            return false;
        };
        int[] colOnly = new int[image.length];
        for (int i = 0; i < image.length; i++) {
            colOnly[i] = image[i][miny];
        }
        if (!Arrays.stream(Arrays.copyOfRange(colOnly, miny, maxy)).allMatch(y -> y == 1)) { //if every y from c1[1] to c2[1] is not 1, break
            return false;
        };
        for (int i = 0; i < image.length; i++) {
            colOnly[i] = image[i][maxy];
        }
        if (!Arrays.stream(Arrays.copyOfRange(colOnly, miny, maxy)).allMatch(y -> y == 1)) { //if every y from c1[1] to c2[1] is not 1, break
            return false;
        };
        return true;
    }
    public static void main(String[] args) throws IOException {
        //input data modfied to copypaste first set of coords to end
        List<int[]> coords = Arrays.stream(Files.readString(Path.of("src/inputs/day09_test.txt")).split("\n"))
                .map(a -> Arrays.stream(a.split(",")).map(Integer::parseInt).mapToInt(i->i).toArray())
                .toList();
        int[][] image = new int[15][15];
        // [. . .],
        // [. # .]
        for (int[] row : image) {
            Arrays.fill(row, 0);
        }
        int[] lastCoord = coords.get(0);
        image[lastCoord[0]][lastCoord[1]] = 1;
        for (int i = 1; i < coords.size(); i++) {
            int[] thisCoord = coords.get(i);
            int deltaX = thisCoord[0] - lastCoord[0];
            int deltaY = thisCoord[1] - lastCoord[1];
            if (deltaX > 0) {
                for (int x = lastCoord[0]; x <= thisCoord[0]; x++) {
                    image[x][lastCoord[1]] = 1;
                }
            } else if (deltaX < 0) {
                for (int x = thisCoord[0]; x <= lastCoord[0]; x++) {
                    image[x][lastCoord[1]] = 1;
                }
            } else if (deltaY > 0) {
                for (int y = lastCoord[1]; y <= thisCoord[1]; y++) {
                    image[lastCoord[0]][y] = 1;
                }
            } else if (deltaY < 0) {
                for (int y = thisCoord[1]; y <= lastCoord[1]; y++) {
                    image[lastCoord[0]][y] = 1;
                }
            }
            lastCoord = thisCoord;
        }
        for (int[] r : image) {
            System.out.println(Arrays.toString(r));
        }
        
        long maxArea = 0;
        for (int[] coord1 : coords) {
            for (int[] coord2 : coords) {
                if (area(coord1, coord2) > maxArea) {
                    if (isBounding(image, coord1, coord2)) {
                        maxArea = area(coord1, coord2);
                        System.out.println("new area: " + Arrays.toString(coord1) + "|" + Arrays.toString(coord2));
                        System.out.println(maxArea);
                    }
                };
            }
        }
        System.out.println(maxArea);
    }
}