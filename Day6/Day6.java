import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day6 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("day6.txt"));

        ArrayList<String> list = new ArrayList<>();
        while(sc.hasNextLine()) {
            list.add(sc.nextLine());
        }

        int N = list.size(), M = list.get(0).length();
        char[][] map = new char[N][M];
        int idx = 0;
        for(String s : list)map[idx++] = s.toCharArray();

        int[] dr = {-1,0,1,0}, dc = {0,1,0,-1};
        boolean[][] seen = new boolean[N][M];

        int curR = -1, curC = -1;
        int dir = 0;
        for(int r=0;r<N;r++)
            for(int c=0;c<M;c++)
                if(map[r][c]=='^')
                    {curR = r; curC = c;}

        int startR = curR, startC = curC;

        seen[curR][curC] = true;
        while(true)
        {
            int rr = curR + dr[dir], cc = curC + dc[dir];
            if (rr < 0 || rr >= N || cc < 0 || cc >= M)break;
            if (map[rr][cc]=='#'){
                dir = (dir+1)%4;
                continue;
            }
            curR = rr;
            curC = cc;
            seen[curR][curC] = true;
        }
        int count = 0;
        for(int i=0;i<N;i++)
            for(int j=0;j<M;j++)
                if(seen[i][j])count++;

        System.out.println(count);


        int count2 = 0;
        for (int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if (i==startR && j==startC)continue;
                if (map[i][j] == '#')continue;

                map[i][j] = '#';
                curR = startR;
                curC = startC;
                dir = 0;
                boolean[][][] cycleSeen = new boolean[4][N][M];
                cycleSeen[0][curR][curC] = true;
                boolean foundCycle = false;
                while(true)
                {
                    int rr = curR + dr[dir], cc = curC + dc[dir];
                    if (rr < 0 || rr >= N || cc < 0 || cc >= M)break;
                    if (map[rr][cc]=='#'){
                        dir = (dir+1)%4;
                        if (cycleSeen[dir][curR][curC]){
                            foundCycle = true;
                            break;
                        }
                        cycleSeen[dir][curR][curC] = true;
                        continue;
                    }
                    if (cycleSeen[dir][rr][cc]){
                        foundCycle = true;
                        break;
                    }
                    curR = rr;
                    curC = cc;
                    cycleSeen[dir][curR][curC] = true;
                }
                if (foundCycle)count2++;
                map[i][j] = '.';
            }
        }
        System.out.println(count2);

    }
}