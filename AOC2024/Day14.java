import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day14 {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner sc = new Scanner(new File("day14.txt"));

        ArrayList<Integer> x = new ArrayList<>(), y = new ArrayList<>(), dx = new ArrayList<>(), dy = new ArrayList<>();

        while(sc.hasNextLine())
        {
            String[] line = sc.nextLine().split(" ");
            int X = Integer.parseInt(line[0].split(",")[0].replaceAll("[^0-9-]", ""));
            int Y = Integer.parseInt(line[0].split(",")[1].replaceAll("[^0-9-]", ""));
            int DX = Integer.parseInt(line[1].split(",")[0].replaceAll("[^0-9-]", ""));
            int DY = Integer.parseInt(line[1].split(",")[1].replaceAll("[^0-9-]", ""));

            x.add(X);
            y.add(Y);
            dx.add(DX);
            dy.add(DY);
        }

        int N = 101, M = 103;
        int[][] count = new int[N][M];
        for(int i=0;i<x.size();i++){
            int curX = x.get(i), curY = y.get(i);
            int DX = dx.get(i), DY = dy.get(i);

            count[((curX+DX*100)%N+N)%N][((curY+DY*100)%M + M)%M]++;
        }

        int[][] quad = new int[2][2];
        for(int i=0;i<count.length;i++){
            for(int j=0;j<count[0].length;j++){
                if (i == count.length/2)continue;
                if (j== count[0].length/2)continue;
                int a = (i < count.length/2) ? 0 : 1;
                int b = (j < count[0].length/2) ? 0 : 1;
                quad[a][b] += count[i][j];
            }
        }


        int sol = 1;
        for(int i=0;i<2;i++)for(int j=0;j<2;j++)sol*=quad[i][j];

        System.out.println(sol);

        for(int Z=0;Z<10000;Z++){
            char[][] mat = new char[M][N];
            for(int i=0;i<M;i++)Arrays.fill(mat[i], '.');
            for(int i=0;i<x.size();i++){
                int curX = x.get(i), curY = y.get(i);
                int DX = dx.get(i), DY = dy.get(i);

                mat[((curY+DY*Z)%M + M)%M][((curX+DX*Z)%N+N)%N] = '#';
            }

            int totClose = 0;
            for(int i=0;i<M;i++){
                for(int j=0;j<N;j++){
                    if (mat[i][j]!='#')continue;
                    int close = 0;
                    for(int k=-5;k<=5;k++){
                        for(int l=-5;l<=5;l++){
                            int r = i+k, c = j+l;
                            if (r < 0 || r >= M || c < 0 || c >= N) continue;
                            if (mat[r][c]=='#')close++;
                        }
                    }
                    totClose += close;
                }
            }

            if(totClose > 10000) {
                System.out.println(Z+" !!! "+totClose);
                for (int i = 0; i < M; i++) {
                    StringBuilder line = new StringBuilder();
                    for (int j = 0; j < N; j++)
                        line.append(mat[i][j]);
                    System.out.println(line);
                }
            }

        }

    }
}