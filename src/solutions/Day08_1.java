package solutions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

class Day08_1 {
    public static double euclidianDistance(int[] c1, int[] c2) {
        return Math.sqrt(
                Math.pow(c2[0]-c1[0], 2)
                +Math.pow(c2[1]-c1[1], 2)
                +Math.pow(c2[2]-c1[2], 2)
        );
    }
    public static void main(String[] args) throws IOException {
        //Get coords as list of points:
        //[[1,1,1],[2,2,2]]
        List<int[]> coords = Arrays.stream(Files.readString(Path.of("src/inputs/day08_test.txt")).split("\n"))
                .map(a -> Arrays.stream(a.split(",")).map(Integer::parseInt).mapToInt(i->i).toArray())
                .toList();
        //make a hashmap of {double : [coord, coord]}
        HashMap<Double, List<int[]>> hashMap = new HashMap<>();
        for (int[] coord1 : coords) {
            for (int[] coord2 : coords) {
                double dist = euclidianDistance(coord1, coord2);
                if (dist == 0) continue;
                if (!hashMap.containsKey(dist)) {
                    hashMap.put(dist, List.of(coord1, coord2));
                }
            }
        }
        List<Double> sortedDistances = hashMap.keySet().stream().sorted(Collections.reverseOrder()).toList();
        List<List<int[]>> junctions = coords.stream().map(List::of).toList(); //List of junctions, which are lists of coordinates, which are arrays of integers :-)
        for (int i = 0; i < 10; i++) {
            double distance = sortedDistances.get(i);
            int[] coord1 = hashMap.get(distance).get(0);
            int[] coord2 = hashMap.get(distance).get(1);
            //TODO monday: What junction is coord1 at? What junction is coord2 at?
            boolean junctionContainsCoord1, junctionContainsCoord2 = false;
            for (List<int[]> junction : junctions) {
                if (junction.contains(coord1)) {
                    junctionContainsCoord1 = true;
                }
                if (junction.contains(coord2)) {
                    junctionContainsCoord2 = true;
                }
            }
            //merge the two junctions
        }
        
        System.out.println(sortedDistances);
//        for (List<Integer> coord : coords) {
//            System.out.println(coord.toString());
//        }
//        System.out.println(hashMap);
    }
}