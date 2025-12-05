package solutions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
class Day05_2 {
    static String[] mergeRanges(String[] a, String[] b) {
        return
                  new String[]{""+Math.min(Long.parseLong(a[0]),Long.parseLong(b[0]))
                ,""+Math.max(Long.parseLong(a[1]),Long.parseLong(b[1]))};
    }
    static boolean numInRange(Long num, String[] range) {
        return Long.parseLong(range[0]) <= num && num <= Long.parseLong(range[1]);
    }
    public static void main(String[] args) throws IOException {
        String input = Files.readString(Path.of("src/inputs/day05.txt"));
        List<String> inputRanges = Arrays.asList(input.split("\\.")[0].split("\n")); //do not use
        List<String[]> ranges = new ArrayList<>();
        inputRanges.forEach(range -> ranges.add(range.split("-"))); //populate ranges list
        LinkedList<String[]> consolidated = new LinkedList<>();
        for (int i = 0; i < ranges.size(); i++) { //for index i in the given ranges
            System.out.println("checking " + Arrays.toString(ranges.get(i)));
            long startIndex = Long.parseLong(ranges.get(i)[0]); //what is the start number? 5-2 -> 5
            long endIndex = Long.parseLong(ranges.get(i)[1]); //what is the end number?
            int startIndexIndex = -99; //what range is the start index in?
            int endIndexIndex = -99; //what range is the end index in?
            for (int c = 0; c < consolidated.size(); c++) { //Check every consolidated range we have and assign start and end indices.
                if (numInRange(startIndex, consolidated.get(c))) {
                    startIndexIndex = c;
                }
                if (numInRange(endIndex, consolidated.get(c))) {
                    endIndexIndex = c;
                }
            }
            if (startIndexIndex == endIndexIndex && startIndexIndex != -99 && endIndexIndex != -99) { //if the start and end are both part of the same range
                System.out.println("both same range: " + startIndexIndex + "and" + endIndexIndex);
            }
            else if (startIndexIndex != -99 && endIndexIndex != -99) { //if the start and the end are both part of any range, different ones
                System.out.println("start and end both in range: " + startIndexIndex + "and" + endIndexIndex);
                String[] range1 = consolidated.get(startIndexIndex);
                String[] range2 = consolidated.get(endIndexIndex);
//                long range1start = Long.parseLong(range1[0]);
//                long range1end = Long.parseLong(range1[1]);
//                long range2start = Long.parseLong(range2[0]);
//                long range2end = Long.parseLong(range2[1]);
                int max = Math.max(startIndexIndex, endIndexIndex);
                int min = Math.min(startIndexIndex, endIndexIndex);

                consolidated.remove(max);
                consolidated.remove(min);
                consolidated.add(mergeRanges(range1, range2));

                //We remove both ranges and add them merged together.
            }
            else if (startIndexIndex != -99 && endIndexIndex == -99) { //if start is in a range but end is not
                System.out.println("start yes range no: " + startIndexIndex + "and" + endIndexIndex);
                consolidated.set(startIndexIndex, new String[]{consolidated.get(startIndexIndex)[0], String.valueOf(endIndex)});
            }
            else if (startIndexIndex == -99 && endIndexIndex != -99) { //if end is in a range but start is not
                System.out.println("end yes start no: " + startIndexIndex + "and" + endIndexIndex);
                consolidated.set(endIndexIndex, new String[]{String.valueOf(startIndex), consolidated.get(endIndexIndex)[1]});
            }
            else if (startIndexIndex == -99 && endIndexIndex == -99) { //if start and end are both not in list
                System.out.println("start and end not in range: " + startIndexIndex + "and" + endIndexIndex);
                consolidated.add(ranges.get(i)); //add the  range String[] to the list
                //then prune (very hacky)
                int removeIndex = -9999;
                prune: for (int m_e = 0; m_e < consolidated.size(); m_e++) { //for every consolidated range:
                    String[] me = consolidated.get(m_e);
                    //check every other consolidated range:
                    for (int i_t = 0; i_t < consolidated.size(); i_t++) {
                        String[] it = consolidated.get(i_t);
                        //if it is same then idc
                        if (me == it) continue;
                        //is its start lower than my start and its end higher than my end?
                        if (Long.parseLong(it[0]) < Long.parseLong(me[0]) && Long.parseLong(it[1]) > Long.parseLong(me[1])) {
                            //if so, remove myself
                            removeIndex = m_e;
                            break prune;
                        }

                    }
                }
                if (removeIndex != -9999) consolidated.remove(removeIndex);
            }
            System.out.print("consolidated: ");
            for (String[] range : consolidated) {
                System.out.print(Arrays.toString(range) + ",");
            }
            System.out.println("\n");

        }

        for (String[] range : consolidated) {
            System.out.print(Arrays.toString(range) + ",");
        }
        System.out.println();
        long numIDs = 0;
        for (String[] range : consolidated) {
            numIDs += Long.parseLong(range[1]) - Long.parseLong(range[0]) + 1;
        }
        System.out.println(numIDs);
        //many cases
        //tldr of below:
        //start and end not in list -> add to list
        //start but not end in list -> set range at start's end to end
        //not start but end in list -> set range at end's start to start
        //start and end in list: delete range at start, delete range at end, add range (min(startrange,start),max(endrange,end))
        /*
            1. range already encompassed
                L: --#########----#########--
                N: ----##--------------------
                start and end are both inside of the same Range()
                dont do anything
                actually its a special case of #2:
            2. range connects 2 ranges
                L: --#########----#########--
                N: -------###########--------
                start and end are both inside of different ranges
                replace both ranges with their ranges merged
            3. range overlaps once
                L: --#########----#########--
                N: #########-----------------
                start is outside of any range
                end is within a range
                replace that range with it and N merged
            4. range not overlapping
                L: --#########----#########--
                N: ------------#############-
                add it to list
                prune each range: if its start/end are encompassed, remove
         */

    }
}