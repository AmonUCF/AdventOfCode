import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.IntStream;

public class Day16 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("day16.txt"));

        int N = 141;
        StringBuilder board = new StringBuilder();
        while (sc.hasNextLine()) {
            board.append(sc.nextLine());
        }

        int[] delta = {1, N, -1, -N};
        int[] dist = new int[board.length() * 4];
        Arrays.fill(dist, Integer.MAX_VALUE / 2);
        PriorityQueue<Integer> q = new PriorityQueue<>(Comparator.comparingInt(a -> dist[a]));
        q.add(board.indexOf("S") * 4);
        dist[q.peek()] = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
            if (board.charAt(node / 4 + delta[node & 3]) != '#' && dist[node] + 1 < dist[(node / 4 + delta[node & 3]) * 4 + (node & 3)]) {
                dist[(node / 4 + delta[node & 3]) * 4 + (node & 3)] = dist[node] + 1;
                q.add((node / 4 + delta[node & 3]) * 4 + (node & 3));
            }
            if (dist[(node / 4) * 4 + (node + 1) % 4] > dist[node] + 1000) {
                dist[(node / 4) * 4 + (node + 1) % 4] = dist[node] + 1000;
                q.add((node / 4) * 4 + (node + 1) % 4);
            }
            if (dist[(node / 4) * 4 + (node - 1) % 4] > dist[node] + 1000) {
                dist[(node / 4) * 4 + (node - 1) % 4] = dist[node] + 1000;
                q.add((node / 4) * 4 + (node - 1) % 4);
            }
        }
        int sol = IntStream.range(board.indexOf("E") * 4, board.indexOf("E") * 4 + 4).map(i -> dist[i]).min().getAsInt();

        boolean[] isBest = new boolean[4 * board.length()];
        for (int k = 0; k < 4; k++)
            if (dist[board.indexOf("E") * 4 + k] == sol) {
                isBest[board.indexOf("E")*4 + k] = true;
                q.add(board.indexOf("E") * 4 + k);
            }

        while (!q.isEmpty()) {
            int node = q.poll();
            int walkback = (node / 4 - delta[node & 3]) * 4 + (node & 3);
            if (board.charAt(walkback/4) != '#' && dist[walkback] + 1 == dist[node] && !isBest[walkback]) {
                isBest[walkback] = true;
                q.add(walkback);
            }
            for (int k : Set.of(-1, 1)) {
                int rotate = (node / 4) * 4 + ((node + k) & 3);
                if (dist[rotate] + 1000 == dist[node] && !isBest[rotate]) {
                    isBest[rotate] = true;
                    q.add(rotate);
                }
            }
        }

        int count = 0;
        for (int i = 0; i < board.length(); i++) {
            boolean isTrue = false;
            for (int k = 0; k < 4; k++) isTrue |= isBest[i * 4 + k];
            if (isTrue) count++;
        }

        System.out.println(sol+" "+count);
    }
}