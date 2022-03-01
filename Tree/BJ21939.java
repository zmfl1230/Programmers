package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ21939 {

    static final int LAST = 1;
    static List<Integer> levels;
    static Map<Integer, Set<Integer>> problem_level_map = new HashMap<>();
    static Map<Integer, Set<Integer>> level_problem_map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Set<Integer> levelSet = new HashSet<>();
        while(N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int problem = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());

            addOnMap(problem, level);
            levelSet.add(level);
        }
        levels = new LinkedList<>(levelSet);
        Collections.sort(levels);

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
        addOnMap(problem, level);

        if (levels.get(0).equals(level) || levels.get(levels.size()-1).equals(level)) return;
        if (levels.get(0) > level) levels.add(0, level);
        else if(levels.get(levels.size()-1) < level) levels.add(level);
        else {
            levels.add(level);
            Collections.sort(levels);
        }
    }

    static public void addOnMap(int problem, int level) {
        if(!problem_level_map.containsKey(problem)) problem_level_map.put(problem, new TreeSet<>());
        problem_level_map.get(problem).add(level);

        if(!level_problem_map.containsKey(level)) level_problem_map.put(level, new TreeSet<>());
        level_problem_map.get(level).add(problem);
    }

    static public void recommend(int dir) {
        int neededToPrintLevel;
        if(dir == LAST) neededToPrintLevel = levels.get(levels.size()-1);
        else neededToPrintLevel = levels.get(0);

        List<Integer> problems = new ArrayList<>(level_problem_map.get(neededToPrintLevel));
        Collections.sort(problems);

        if(dir == LAST) System.out.println(problems.get(problems.size()-1));
        else System.out.println(problems.get(0));
    }

    static public void solved(int problem) {
        for (Integer level : problem_level_map.get(problem)) {
            level_problem_map.get(level).remove(problem);
            if(level_problem_map.get(level).size() != 0) continue;
            levels.remove(level);
        }
        problem_level_map.remove(problem);
    }
}
