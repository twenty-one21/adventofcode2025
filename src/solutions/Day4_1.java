package solutions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

class Day4_1 {
    public static int rollAtLocation(char[][] grid, int row, int column) {
//        System.out.print(grid[row][column]=='@');
        return grid[row][column]=='@'?1:0;
    }
    public static void main(String[] args) throws IOException {
        String input = Files.readString(Path.of("src/inputs/day4.txt"));

        //populate 2d array
        String[] rows = input.split("\n"); //"..@@.." , "@@..@@" etc
        char[][] grid = new char[rows[0].length()][rows.length]; //grid with width length of first string, height num of rows
        for (int row = 0; row < rows.length; row++) {
            for (int letter = 0; letter < rows[row].length(); letter++) {
                grid[row][letter] = rows[row].charAt(letter);
            }
        }
        ArrayList<String> coords = new ArrayList<String>();

        //check each cell for neighbors
        int numAccessible = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                if (grid[row][column] != '@') continue;
//                System.out.println("row" + row + "column" + column);
                boolean topEdge = row == 0;
                boolean bottomEdge = row == grid.length - 1;
                boolean leftEdge = column == 0;
                boolean rightEdge = column == grid[row].length - 1;
                int rolls = 0;
                // down and right is +
                /*
                    r0c0    r0c1    r0c2
                    r1c0    r1c1    r1c2
                    r2c0    r2c1    r2c2
                 */
//                System.out.printf("t%bb%bl%br%b\n",topEdge,bottomEdge,leftEdge,rightEdge);
                if (!leftEdge && !topEdge) {
                    rolls += rollAtLocation(grid, row-1, column-1);
//                    System.out.println("lt");
                }
                if (!topEdge) {
                    rolls += rollAtLocation(grid, row-1, column);
//                    System.out.println("t");
                }
                if (!rightEdge && !topEdge) {
                    rolls += rollAtLocation(grid, row-1, column+1);
//                    System.out.println("rt");
                }
                if (!leftEdge) {
                    rolls += rollAtLocation(grid, row, column-1);
//                    System.out.println("l");
                }
                if (!rightEdge) {
                    rolls += rollAtLocation(grid, row, column+1);
//                    System.out.println("r");
                }
                if (!leftEdge && !bottomEdge) {
                    rolls += rollAtLocation(grid, row+1, column-1);
//                    System.out.println("lb");
                }
                if (!bottomEdge) {
                    rolls += rollAtLocation(grid, row+1, column);
//                    System.out.println("b");
                }
                if (!bottomEdge && !rightEdge) {
                    rolls += rollAtLocation(grid, row+1, column+1);
//                    System.out.println("br");
                }
                if (rolls<4) {
                    numAccessible++;
                    coords.add(row + "," + column);
//                    System.out.println("rolls" + rolls);
                }
                if (row == 0 && column == 7) {
                    System.out.printf("0,7\tt%bb%bl%br%b\t\n", topEdge,bottomEdge,leftEdge,rightEdge);
                    System.out.printf("rollAtLocation(grid, row, column-1)\t%d\n",rollAtLocation(grid, row, column-1));
                    System.out.println("row, column-1: " + grid[row][column-1]);
                    System.out.printf("rollAtLocation(grid, row, column+1)\t%d\n",rollAtLocation(grid, row, column+1));
                }
            }
        }
//        for (char[] d : display) {
//            System.out.println(Arrays.toString(d));
//        }
        System.out.println("accessible: " + numAccessible);
    }
}