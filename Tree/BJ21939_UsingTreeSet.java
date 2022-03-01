package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ21939_UsingTreeSet {
    static class Problem implements Comparable<Problem> {
        int number;
        int level;

        Problem(int num, int lev) {
            this.number = num;
            this.level = lev;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Problem problem = (Problem) o;
            return number == problem.number;
        }

        @Override
        public int hashCode() {
            return Objects.hash(number);
        }

        @Override
        public int compareTo(Problem o) {
            if (this.level != o.level) return this.level - o.level;
            return this.number - o.number;
        }
    }

    static final int LAST = 1;
    static TreeSet<Problem> problems = new TreeSet<>();
    static Map<Integer, Integer> problem_level_map = new HashMap<>();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        while(N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int problem = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());
            problems.add(new Problem(problem, level));
            problem_level_map.put(problem, level);
        }

        int M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            String op = st.nextToken();

            if(op.equals("add")) add(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            else if(op.equals("recommend")) recommend(Integer.parseInt(st.nextToken()));
            else solved(Integer.parseInt(st.nextToken()));
        }

    }

    static public void add(int problem, int level) {
        problems.add(new Problem(problem, level));
        problem_level_map.put(problem, level);
    }

    static public void recommend(int dir) {
        if (dir == LAST) System.out.println(problems.last().number);
        else System.out.println(problems.first().number);
    }

    static public void solved(int problem) {
        problems.remove(new Problem(problem, problem_level_map.get(problem)));
    }
}
