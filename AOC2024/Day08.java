import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day08 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("day8.txt"));

        char[][] mat = new char[50][50];
        for(int i=0;i<mat.length;i++)mat[i] = sc.nextLine().toCharArray();

        System.out.println(getAntiNodes(mat, true)+" "+getAntiNodes(mat, false));
    }

    private static int getAntiNodes(char[][] mat, boolean isPart1) {
        boolean[][] anti = new boolean[mat.length][mat[0].length];

        for(int i = 0; i< mat.length; i++){
            for(int j = 0; j< mat[0].length; j++){
                if (mat[i][j] == '.')continue;
                if(mat[i][j]=='#')continue;
                for(int k = i; k< mat.length; k++){
                    for(int l = 0; l< mat[0].length; l++){
                        if (i==k && j==l)continue;
                        if (mat[i][j] == mat[k][l])
                        {
                            int dx = k-i, dy = l-j;
                            int min = isPart1 ? 1 : 0;
                            int max = isPart1 ? 1 : 100;
                            for (int count = min;count<=max;count++){
                                int x1 = i-count*dx, y1 = j-count*dy;
                                int x2 = k+count*dx, y2 = l+count*dy;
                                if (x1 >=0 && x1 < mat.length && y1 >= 0 && y1 < mat[0].length)anti[x1][y1]=true;
                                if (x2 >=0 && x2 < mat.length && y2 >= 0 && y2 < mat[0].length)anti[x2][y2]=true;
                            }
                        }
                    }
                }
            }
        }

        int sol = 0;
        for(int i=0;i<mat.length;i++)for(int j=0;j<mat[0].length;j++)if(anti[i][j])sol++;
        return sol;
    }
}