package Dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ12865 {
    static int totalItem;
    static int[][] items;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        totalItem = Integer.parseInt(st.nextToken());
        int weight = Integer.parseInt(st.nextToken());
        items = new int[totalItem][2];

        int count = totalItem;
        while (count > 0){
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            items[totalItem-count][0] = w;
            items[totalItem-count][1] = v;
            count--;
        }

        Arrays.sort(items, (v1, v2) -> {
            if(v1[0] != v2[0]) return v1[0] - v2[0];
            return v2[1] - v1[1];
        });

        System.out.println(pack(weight, 0, 0));

    }

    public static int pack(int capacity, int item, int value) {
        if(item == totalItem) return value;

        // 포함된 경우
        int include = value;
        if(capacity - items[item][0] >= 0)
            include = pack(capacity - items[item][0], item + 1, value + items[item][1]);

        // 포함되지 않은 경우
        int notInclude = pack(capacity, item + 1, value);

        return Math.max(include, notInclude);

    }
}
