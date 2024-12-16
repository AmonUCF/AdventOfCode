import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day10 {
    static int[] dr = {-1,0,1,0}, dc = {0,-1,0,1};
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("day10.txt"));

        int[][] mat = new int[40][40];
        for (int i=0;i<mat.length;i++)mat[i] = sc.nextLine().chars().map(Character::getNumericValue).toArray();

        int sol1 = 0, sol2 = 0;
        for(int i=0;i<mat.length;i++)
            for(int j=0;j<mat[0].length;j++)
                if (mat[i][j] == 0) {
                    sol1 += solve(i, j, mat, new HashSet<>());
                    sol2 += solve(i, j, mat, null);
                }

        System.out.println(sol1+" "+sol2);
    }

    private static int solve(int r, int c, int[][] mat, HashSet<Integer> seen)
    {
        if (seen != null && seen.contains(r*mat.length+c))return 0;
        if (mat[r][c] == 9 ) {
            if (seen != null) seen.add(r*mat.length+c);
            return 1;
        }
        int sol = 0;
        for(int k=0;k<4;k++) {
            try {
                int rr = r+dr[k], cc = c+dc[k];
                if (mat[rr][cc] == mat[r][c] + 1)
                    sol += solve(rr,cc,mat, seen);
            } catch (Exception e){}
        }
        return sol;
    }
}