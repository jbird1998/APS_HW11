import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class batch_processing {

    static long[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(read.readLine());

        int N = Integer.parseInt(tokens.nextToken());
        int M = Integer.parseInt(tokens.nextToken());

        tokens = new StringTokenizer(read.readLine());
        list = new long[N];

        long sum = 0L;
        long global = 0L;
        for (int i = 0; i < N; i++) {
            long num = Long.parseLong(tokens.nextToken());
            sum += num;
            list[i] = num;
            if (num > global) {
                global = num;
            }
        }
        long storedSum = sum;
        long[] bounds = {global, sum};
        long mid = (bounds[0] + bounds[1]) / 2;
        long curr = 0L;
        int i = 0;
        int partitions = M;
        long lastSuccess = sum;
        long last;
        while (bounds[0] < bounds[1]) {
            while (partitions > 1 && i < N) {
                long num = list[i];
                if (curr + num > mid) {
                    curr = 0L;
                    --partitions;
                } else {
                    sum -= num;
                    curr += num;
                    i++;
                }
            }
            if (sum <= mid) {
                bounds[1] = mid;
                lastSuccess = mid;
            } else {
                bounds[0] = mid;
            }
            last = mid;
            mid = (bounds[0] + bounds[1]) / 2;
            if (last == mid) {
                break;
            }
            curr = 0L;
            i = 0;
            partitions = M;
            sum = storedSum;
        }
        int[] parts = new int[M - 1];
        long tolerance = 0L;
        int partsIndex = 0;
        for (int j = N - 1; j >= 0; j--) {
            if (partsIndex == M - 1) {
                break;
            }
            tolerance += list[j];
            // System.out.println(tolerance);
            if (tolerance > lastSuccess) {
                parts[partsIndex] = j;
                partsIndex++;
                tolerance = list[j];
            } else if (j == (M - 1 - partsIndex)) {
                parts[partsIndex] = j - 1;
                partsIndex++;
                tolerance = 0;
            }
        }
        StringBuilder output = new StringBuilder(6000);
        partsIndex = M - 2;
        for (int j = 0; j < N; j++) {
            output.append(list[j]);
            if (j != N - 1) {
                output.append(' ');
            }
            if (partsIndex >= 0 && parts[partsIndex] == j) {
                output.append("/ ");
                partsIndex--;
            }
        }
        System.out.println(output.toString());
//        System.out.println(lastSuccess);
//        for (int j = 0; j < M - 1; j++) {
//            System.out.println("\t" + parts[j]);
//        }
    }
}
