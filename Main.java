import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cnt = sc.nextInt();
        int[][] costs = new int[cnt][3]; // i번째 집을 각 색으로 칠하는데 드는 비용 (0행은 Red, 1행은 Green, 2행은 Blue)
        int[][] A = new int[cnt][3]; // 1~n번째 집들을 각 색으로 칠하는데 드는 최소 비용 (0행은 Red, 1행은 Green, 2행은 Blue)
        for (int i=0;i<cnt;i++) {
            costs[i][0] = sc.nextInt();
            costs[i][1] = sc.nextInt();
            costs[i][2] = sc.nextInt();
        }
        // Bottom-Up 방식
        // 점화식 : A[i][0] = min(A[i-1][1], A[i-1][2]) + costs[i][0]
        //         A[i][1] = min(A[i-1][0], A[i-1][2]) + costs[i][1]
        //         A[i][2] = min(A[i-1][0], A[i-1][1]) + costs[i][2]
        A[0][0] = costs[0][0];
        A[0][1] = costs[0][1];
        A[0][2] = costs[0][2];
        for (int i=1;i<cnt;i++) {
            // Red로 칠할 경우
            if (A[i-1][1] < A[i-1][2]) A[i][0] = A[i-1][1] + costs[i][0];
            else A[i][0] = A[i-1][2] + costs[i][0];
            // Green으로 칠할 경우
            if (A[i-1][0] < A[i-1][2]) A[i][1] = A[i-1][0] + costs[i][1];
            else A[i][1] = A[i-1][2] + costs[i][1];
            // Blue로 칠할 경우
            if (A[i-1][0] < A[i-1][1]) A[i][2] = A[i-1][0] + costs[i][2];
            else A[i][2] = A[i-1][1] + costs[i][2];
        }
        int min = A[cnt-1][0];
        for (int i=1;i<3;i++) {
            if (min > A[cnt-1][i]) min = A[cnt-1][i];
        }
        System.out.println(min);
    }
}
