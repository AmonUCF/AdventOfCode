import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.IntStream;

public class Day20 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("day20.txt"));

        int N = 141;
        StringBuilder mat = new StringBuilder();
        for(int i=0;i<N;i++)mat.append(sc.nextLine());

        int start = mat.indexOf("S"), end = mat.indexOf("E");
        int[] distFromStart = solve(N, start, end, mat.toString());
        int[] distFromEnd = solve(N, end, start, mat.toString());

        int p1Max = 2, p2Max = 20;
        int count1 = 0, count2 = 0;
        for(int i=0;i<mat.length();i++) {
            for(int j=0;j<mat.length();j++){
                int hamDist = Math.abs(i/N - j/N)+Math.abs(i%N - j%N);
                if (distFromStart[i] + distFromEnd[j] + hamDist + 100 <= distFromStart[end]) {
                    if (hamDist <= p1Max) count1++;
                    if (hamDist <= p2Max) count2++;
                }
            }
        }

        System.out.println(count1+" "+count2);
    }

    private static int[] solve(int N, int start, int end, String mat) {
        int oo = Integer.MAX_VALUE/4;
        int[] delta = {-1,-N,1,N};
        int[] dist = new int[mat.length()];
        Arrays.fill(dist,oo);
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(start);
        dist[start] = 0;
        while(!q.isEmpty()) {
            int node = q.poll();
            for(int i=0;i<4;i++) {
                int next = node + delta[i];
                if (mat.charAt(next)=='#')continue;
                if (dist[next] <= dist[node]+1)continue;
                dist[next] = dist[node]+1;
                q.add(next);
            }
        }

        return dist;
    }
}