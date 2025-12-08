package solutions;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.CharBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

class Day07_2 {
    public static void main(String[] args) throws IOException {
        String[] input = Files.readString(Path.of("src/inputs/day07.txt")).split("\n");
        BigInteger[] tachyon = new BigInteger[input[0].length()];
        Arrays.fill(tachyon, BigInteger.ZERO);
        for (String line : input) {
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == 'S') {
                    tachyon[j] = BigInteger.valueOf(1);
                }
                boolean isSplitter = tachyon[j].equals(BigInteger.valueOf(-1));
                if (line.charAt(j) == '^' && !tachyon[j].equals(BigInteger.ZERO) && !isSplitter) {
                    tachyon[j - 1] = tachyon[j-1].add(tachyon[j]);
                    tachyon[j + 1] = tachyon[j+1].add(tachyon[j]);
                    tachyon[j] = BigInteger.valueOf(-1);
                } else if (isSplitter) {
                    tachyon[j] = BigInteger.valueOf(0);
                }
            }
            System.out.println(line);
        }
        Optional<BigInteger> timelines = Arrays.stream(tachyon).reduce(BigInteger::add);
//        CharBuffer.wrap(tachyon).chars().map(c -> c - '0').forEach(c -> System.out.print(c + " "));
        System.out.println();
        System.out.println(Arrays.toString(tachyon));
        System.out.println(timelines.get());
        
    }
}