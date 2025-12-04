package solutions;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class Day01_2 {
    static int mod100(int n) {
        return n - 100*(n/100);
    }
    public static void main(String[] args) throws IOException {
        String input = Files.readString(Path.of("src/inputs/day01.txt"));
        String[] lines = input.split("\n");
        int zeroes = 0;
        int dial = 50;
//        int prevDial = 50;
        for (String line : lines) {
            char dir = line.charAt(0);
            int dist = Integer.parseInt(line.substring(1));
            int rotation = (dir=='L'?-1:1) * dist; //-34
//            prevDial = dial; //22
            for (int i = 0; i < Math.abs(rotation); i++) { //i give up
                dial += (int) Math.signum(rotation);
                if (dial == -1) dial = 99;
                if (dial == 100) dial = 0;
                if (dial == 0) zeroes++;
            }
            System.out.printf("Given rotation: %s\ndirection: %c, distance: %d\nrotation:%d\ndial:%d\nzeroes:%d\n\n",
                    line,
                    dir,
                    dist,
                    rotation,
                    dial,
                    zeroes);
//            dial += rotation; // -12
//            //at any point, there is a distance to the closest left + right multiple of 100
//            //if the rotation < closestleft or rotation > closestright, then it crosses at least once
//            //40 -> 0
//            // -40 -> 0
//            int closestSmall = Math.abs(dial - (dial / 100 * 100)); // distance to clsoest left
//            //40 -> 100
//            //-40 -> 100
//            int closestBig = Math.abs(dial - (closestSmall + (int) Math.signum(dial) * 100)); // same
//            zeroes += (rotation < -closestSmall || rotation > closestBig ? 0 : 1) + Math.abs(rotation / 100); //crossed zeroes + zeroes missed
//
//            System.out.printf("Given rotation: %s\ndirection: %c, distance: %d\nrotation:%d\nprevDial:%d\ndial:%d\nzeroes:%d\n\n",
//                    line,
//                    dir,
//                    dist,
//                    rotation,
//                    prevDial,
//                    dial,
//                    zeroes);
        }
        System.out.println(zeroes);
    }
}