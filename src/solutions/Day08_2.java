package solutions;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

class Day08_2 {
    public static double euclidianDistance(int[] c1, int[] c2) {
        return Math.sqrt(
                Math.pow(c2[0]-c1[0], 2)
                +Math.pow(c2[1]-c1[1], 2)
                +Math.pow(c2[2]-c1[2], 2)
        );
    }
    public static void main(String[] args) throws Exception {
        //Get coords as list of points:
        //[[1,1,1],[2,2,2]]
        List<int[]> coords = Arrays.stream(Files.readString(Path.of("src/inputs/day08.txt")).split("\n"))
                .map(a -> Arrays.stream(a.split(",")).map(Integer::parseInt).mapToInt(i->i).toArray())
                .toList();
        //make a hashmap of {distance : [coord, coord]}
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
        long x1 = 0;
        long x2 = 0;
        List<Double> sortedDistances = hashMap.keySet().stream().sorted().toList();
        List<List<int[]>> junctions = coords.stream().map(coord -> new ArrayList<>(Collections.singletonList(coord))).collect(Collectors.toCollection(ArrayList::new)); //List of junctions, which are lists of coordinates, which are arrays of integers :-)
        for (int i = 0; i < sortedDistances.size(); i++) {
            double distance = sortedDistances.get(i);
            int[] coord1 = hashMap.get(distance).get(0);
            int[] coord2 = hashMap.get(distance).get(1);
            List<int[]> j1 = new ArrayList<>();
            int j1index = -1;
            List<int[]> j2 = new ArrayList<>();
            int j2index = -1;
            for (List<int[]> junction : junctions) {
                if (junction.contains(coord1)) {
                    j1 = junction;
                }
                if (junction.contains(coord2)) {
                    j2 = junction;
                }
            }
            if (j1.isEmpty() || j2.isEmpty()) {
                throw new Exception("could not find j1 or j2");
            }
            if (j1 != j2) {
                //merge the two junctions
                j1index = junctions.indexOf(j1);
                j2index = junctions.indexOf(j2);
                Set<int[]> unionSet = new HashSet<>();
                unionSet.addAll(j1);
                unionSet.addAll(j2);
    
                List<int[]> junctionUnion = new ArrayList<int[]>(unionSet);
                junctions.remove(j1);
                junctions.remove(j2);
                junctions.add(junctionUnion);
            }
            if (junctions.size() == 1) {
                x1 = coord1[0];
                x2 = coord2[0];
                break;
            }
        }
        // print junctions
        for (List<int[]> j : junctions) {
            for (int[] c : j)
                System.out.print(Arrays.toString(c));
            System.out.print(" | ");
        }
        System.out.println();
        //get 3 largest junctions
        System.out.println(x1*x2);
//        System.out.println("distances "  + sortedDistances);
//        for (List<Integer> coord : coords) {
//            System.out.println(coord.toString());
//        }
//        System.out.println(hashMap);
    }
}