import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.IntStream;

public class Day17 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("day17.txt"));

        long[] reg = new long[3];
        reg[0] = Long.parseLong(sc.nextLine().split(" ")[2]);
        reg[1] = Integer.parseInt(sc.nextLine().split(" ")[2]);
        reg[2] = Integer.parseInt(sc.nextLine().split(" ")[2]);
        sc.nextLine();
        String programLine = sc.nextLine().split(" ")[1];
        int[] program = Arrays.stream(programLine.split(",")).mapToInt(Integer::parseInt).toArray();

        System.out.println("Part 1: "+RunProgram(program, Arrays.copyOf(reg, 3)));

        PriorityQueue<Long> pq = new PriorityQueue<>();
        pq.add(0L);
        HashSet<Long> seen = new HashSet<>();
        while(!pq.isEmpty()){
            long node = pq.poll();
            for (int i=1;i<=12;i++) {
                for (long j=0;j<(1L<<i);j++) {
                    long[] copy = Arrays.copyOf(reg, 3);
                    copy[0] = (node << i) + j;
                    if (seen.contains(copy[0]))continue;
                    String result = RunProgram(program, copy);
                    if (programLine.equals(result)) {
                        System.out.println("Part 2: "+((node << i) + j));
                        return;
                    }
                    if (programLine.endsWith(result)) {
                        seen.add((node << i) + j);
                        pq.add((node << i) + j);
                    }
                }
            }
        }
    }

    private static String RunProgram(int[] program, long[] reg) {
        StringBuilder out = new StringBuilder();
        int pc = 0;
        while(pc < program.length)
        {
            switch(program[pc]) {
                case 0:
                    reg[0] >>= getCombo(program[pc+1], reg);
                    break;
                case 1:
                    reg[1] ^= program[pc+1];
                    break;
                case 2:
                    reg[1] = getCombo(program[pc+1], reg) & (0b111);
                    break;
                case 3:
                    if (reg[0] != 0) pc = program[pc+1] - 2;
                    break;
                case 4:
                    reg[1] ^= reg[2];
                    break;
                case 5:
                    out.append((getCombo(program[pc + 1], reg) & (0b111))).append(",");
                    break;
                case 6:
                    reg[1] = reg[0] >> getCombo(program[pc + 1], reg);
                    break;
                case 7:
                    reg[2] = reg[0] >> getCombo(program[pc + 1], reg);
                    break;
                default:
                    break;
            }

            pc+=2;
        }

        return out.substring(0, out.length()-1);
    }

    private static long getCombo(int combo, long[] reg) {
        return switch (combo) {
            case 0, 1, 2, 3 -> combo;
            case 4, 5, 6 -> reg[combo - 4];
            default -> 0;
        };
    }
}