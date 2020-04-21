import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class date_overflow {

    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(read.readLine());

        int n = Integer.parseInt(tokens.nextToken());
        int[] years = new int[10000];
        int[] as = new int[n];
        int[] bs = new int[n];
        int[] ys = new int[n];

        int yi, ai, bi;
        int aiMax = -1;
        for (int i = 0; i < n; i++) {
            tokens = new StringTokenizer(read.readLine());
            yi = Integer.parseInt(tokens.nextToken());
            ai = Integer.parseInt(tokens.nextToken());
            bi = Integer.parseInt(tokens.nextToken());
            as[i] = ai;
            bs[i] = bi;
            ys[i] = yi;
            if (aiMax < ai) {
                aiMax = ai;
            }
            int diff = bi - ai;
            while (yi < 10000) {
                years[yi]++;
                yi += diff;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (ys[i] > bs[j]) {
                    years[(ys[i] - bs[j]) % (bs[j] - as[j]) + as[j]]++;
                }
            }
        }
        for (int i = aiMax; i < 10000; i++) {
            if (years[i] == n) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(-1);
    }
}
