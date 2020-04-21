import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ayus_candies {

    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(read.readLine());
        long[][] candies = new long[3][3];

        for (int i = 0; i < 9; i++) {
            candies[i / 3][i % 3] = Long.parseLong(tokens.nextToken());
        }
        // rows represent boxes, columns represent candy type

        // column orderings
        int[][] options = new int[6][];
        options[0] = new int[]{0, 2, 1};
        options[1] = new int[]{0, 1, 2};
        options[2] = new int[]{2, 0, 1};
        options[3] = new int[]{2, 1, 0};
        options[4] = new int[]{1, 0, 2};
        options[5] = new int[]{1, 2, 0};

        String[] reps = {"BCG", "BGC", "CBG", "CGB", "GBC", "GCB"};
        String rep = "";
        long[] results = new long[6];
        long min = Long.MAX_VALUE;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                int index = options[i][j];
                results[i] += candies[(j + 1) % 3][index];
                results[i] += candies[(j + 2) % 3][index];
            }
            // System.out.println(results[i]);
            if (min > results[i]) {
                min = results[i];
                rep = reps[i];
            }
        }
        System.out.println(rep + " " + min);
    }
}
