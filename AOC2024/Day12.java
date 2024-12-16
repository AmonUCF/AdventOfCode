import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day12 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("day12.txt"));

        int N = 140;
       char[][] mat = new char[N][N];
       for(int i=0;i<mat.length;i++)mat[i] = sc.nextLine().toCharArray();


       int[] dr = {-1,0,1,0}, dc = {0,-1,0,1};
       int[][] region = new int[N][N];
       ArrayList<Integer> regionArea = new ArrayList<>();
       for(int i=0;i<N;i++)Arrays.fill(region[i], -1);
       int sol1 = 0;
       int reg = 0;
       for(int i=0;i<N;i++){
           for(int j=0;j<N;j++){
               if (region[i][j]!=-1)continue;

               region[i][j] = reg;
               ArrayDeque<Integer> q=  new ArrayDeque<>();
               q.add(i);
               q.add(j);
               int area = 0, perimeter = 0;
               while(!q.isEmpty()){
                   int r = q.poll();
                   int c = q.poll();
                   area++;
                   for(int k=0;k<4;k++){
                       int rr = r + dr[k], cc = c + dc[k];
                       if (rr < 0 || rr >= N || cc < 0 || cc >= N) continue;
                       if (mat[rr][cc] != mat[r][c])continue;
                       if (region[rr][cc]!=-1){
                           perimeter--;
                           continue;
                       }
                       perimeter--;
                       region[rr][cc] = reg;
                       q.add(rr);
                       q.add(cc);
                   }
               }

               perimeter += 4*area;
               sol1 += area * perimeter;
               regionArea.add(area);
               reg++;
           }
       }

       int sol2 = 0;
       int[][] dx = {{1,0,1},{-1,0,-1},{0,-1,-1,}, {0,-1,-1}}, dy = {{0,-1,-1},{0,-1,-1},{1,0,1},{-1,0,-1}};
       for(int r = 0; r < reg; r++)
       {
           int perimeter2 = 0;
           for(int i=0;i<N;i++) {
               for(int j=0;j<N;j++){
                   if (region[i][j] == r)
                   {
                        for(int k=0;k<4;k++){
                            int x = i+dx[k][0], x1 = i+dx[k][1], x2 = i+dx[k][2];
                            int y = j+dy[k][0], y1 = j+dy[k][1], y2 = j+dy[k][2];
                            if (!inRegion(x,y,r,region) && (!inRegion(x1,y1,r,region) || inRegion(x2,y2,r,region)))
                                perimeter2++;
                        }
                   }
               }
           }
           sol2 += perimeter2 * regionArea.get(r);
       }

       System.out.println(sol1+" "+sol2);
    }

    private static boolean inRegion(int x, int y, int reg, int[][] region)
    {
        if (x < 0 || x >= region.length || y < 0 || y >= region[0].length) return false;
        return region[x][y] == reg;
    }
}