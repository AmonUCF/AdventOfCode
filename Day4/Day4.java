import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day4 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("day4.txt"));

        ArrayList<String> input = new ArrayList<>();
        while (sc.hasNextLine()) {
            input.add(sc.nextLine());
        }

        int N = input.size(), M = input.getFirst().length();
        char[][] mat = new char[N][M];
        for(int i=0;i<N;i++)mat[i] = input.get(i).toCharArray();

        char[] XMAS = {'X', 'M', 'A', 'S'};
        int sum1 = 0;
        for(int i=0;i<N;i++)
            for (int j=0;j<M;j++)
                for (int di=-1;di<=1;di++)
                    for (int dj=-1;dj<=1;dj++)
                        for (int k=0;k<=XMAS.length;k++)
                            try {
                                if (k == XMAS.length) sum1++;
                                if (mat[i+di*k][j+dj*k] != XMAS[k]) break;
                            } catch (Exception e) { break;}

        int sum2=0;
        for (int i=0;i<N;i++)
            for (int j = 0; j < M; j++)
                try {
                    if (mat[i][j] != 'A')continue;
                    HashSet<Character> a = new HashSet<>(), b = new HashSet<>();
                    a.add(mat[i-1][j-1]); a.add(mat[i+1][j+1]);
                    b.add(mat[i+1][j-1]); b.add(mat[i-1][j+1]);
                    if (a.contains('M') && a.contains('S') && b.contains('M') && b.contains('S'))sum2++;
                } catch (Exception e) {}

        System.out.println("Part 1:" + sum1);
        System.out.println("Part 2:" + sum2);
    }
}
