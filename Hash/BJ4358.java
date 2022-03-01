package Hash;


import java.io.IOException;
import java.util.*;

public class BJ4358 {
    static int totalCount = 0;
    static Map<String, Integer> treeMAp = new HashMap<>();
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String tree = sc.nextLine();

            if(!treeMAp.containsKey(tree)) treeMAp.put(tree, 0);
            treeMAp.put(tree, treeMAp.get(tree)+1);
            totalCount++;
        }

        List<String> keySet = new ArrayList<>(treeMAp.keySet());
        Collections.sort(keySet);

        for (String key : keySet) {
            float value = treeMAp.get(key) / (float)totalCount * 100.0f;
            System.out.printf("%s %.4f%n", key, value);
        }

    }
}
