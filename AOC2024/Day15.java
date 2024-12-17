import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day15 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("day15.txt"));

        StringBuilder board = new StringBuilder(), moves = new StringBuilder();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.isEmpty()) break;
            board.append(line);
        }
        while (sc.hasNextLine()) moves.append(sc.nextLine());

        StringBuilder largeBoard = new StringBuilder(board.chars().mapToObj(c -> {
            if ((char)c == 'O') return "[]";
            else if ((char)c == '@') return "@.";
            else return String.valueOf((char)c).repeat(2);
        }).collect(Collectors.joining()));

        System.out.println(solve(board, moves, 50));
        System.out.println(solve(largeBoard, moves, 100));
    }

    private static int solve(StringBuilder board, StringBuilder moves, int COLS)
    {
        String DIR = "^>v<";
        int[] delta = {-COLS, 1, COLS, -1};
        for (char dir : moves.toString().toCharArray())
        {
            ArrayDeque<Integer> q = new ArrayDeque<>();
            q.add(board.indexOf("@"));
            TreeSet<Integer> seen = new TreeSet<>(Collections.singletonList(q.peek()));
            while(!q.isEmpty())
            {
                int next = q.poll()+delta[DIR.indexOf(dir)];
                if (board.charAt(next) == '.') continue;
                if (board.charAt(next) == '#') { seen.clear(); break; }
                if (seen.add(next)) q.add(next);
                if (board.charAt(next) == '[' && seen.add(next+1)) q.add(next+1);
                if (board.charAt(next) == ']' && seen.add(next-1)) q.add(next-1);
            }
            for (int pos : dir=='>' || dir=='v' ? seen.reversed() : seen) {
                board.setCharAt(pos + delta[DIR.indexOf(dir)], board.charAt(pos));
                board.setCharAt(pos, '.');
            }
        }
        return IntStream.range(0,board.length()).filter(i->board.charAt(i)=='['||board.charAt(i)=='O').map( i ->100*(i/COLS)+(i%COLS)).sum();
    }
}