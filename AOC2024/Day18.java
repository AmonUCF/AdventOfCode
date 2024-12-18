import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day18 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("day18.txt"));

        int N = 71;
        boolean[][] unsafe = new boolean[N][N];
        int count = 0;
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            int x = Integer.parseInt(line.split(",")[0]);
            int y = Integer.parseInt(line.split(",")[1]);
            unsafe[y][x] = true;
            count++;

            ArrayDeque<Integer> q = new ArrayDeque<>();
            q.add(0);
            q.add(0);
            int[][] seen = new int[N][N];
            int oo = Integer.MAX_VALUE/2;
            for (int[] ints : seen) Arrays.fill(ints, oo);
            seen[0][0] = 0;
            int[] dr = {-1,0,1,0}, dc = {0,-1, 0, 1};
            while(!q.isEmpty()) {
                int r = q.poll();
                int c = q.poll();
                for(int k=0;k<4;k++) {
                    int rr = r + dr[k], cc = c + dc[k];
                    if (rr < 0 || rr >= N || cc < 0 || cc >= N) continue;
                    if (seen[rr][cc] != oo) continue;
                    if (unsafe[rr][cc]) continue;
                    seen[rr][cc] = seen[r][c] + 1;
                    q.add(rr);
                    q.add(cc);
                }
            }
            if (count == 1024)
                System.out.println("Byte #"+count+": "+x+","+y+" "+seen[N-1][N-1]);
            if (seen[N-1][N-1] == oo) {
                System.out.println("Byte #"+count+": "+x+","+y+" "+seen[N-1][N-1]);
                break;
            };
        }
    }
}