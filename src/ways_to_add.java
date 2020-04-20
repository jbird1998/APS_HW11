import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ways_to_add {

    static long[][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokens.nextToken());
        int K = Integer.parseInt(tokens.nextToken());

        memo = new long[N + 1][K];

        for (int i = 0; i <= N; i++) {
            memo[i][0] = 1L;
        }

        System.out.println(count(N, K - 1));
    }

    static long count(int N, int K) {
        if (N >= 0 && (memo[N][K] != 0 || K == 0)) {
            return memo[N][K];
        }
        long sum = 0L;
        for (int i = 0; i <= N; i++) {
            sum += count(N - i, K - 1) % 1000000007L;
            sum = sum % 1000000007L;
        }
        memo[N][K] = sum;
        return sum;
    }
}
