package PrefixSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ10800 {
    static final int COLOR = 0;
    static final int SIZE = 1;
    static int size;
    static int[][] original;
    static List<Integer> sortedTotalSize = new ArrayList<>();
    static int[] totalPrefixSumSize;
    // 색 별 정렬된 구슬 크기를 저장
    static Map<Integer, List<Integer>> sizeByColor = new HashMap<>();
    // 색 별 구슬 크기의 누적합 저장
    static Map<Integer, List<Integer>> sizePrefixSumByColor = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        size = Integer.parseInt(br.readLine());
        original = new int[size][2];
        totalPrefixSumSize = new int[size];

        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            // 색
            original[i][COLOR] = Integer.parseInt(st.nextToken());
            // 크기
            original[i][SIZE] = Integer.parseInt(st.nextToken());

            if(!sizeByColor.containsKey(original[i][0])) {
                sizeByColor.put(original[i][0], new ArrayList<>());
                sizePrefixSumByColor.put(original[i][0], new ArrayList<>());
            }
            sizeByColor.get(original[i][0]).add(original[i][1]);
            sortedTotalSize.add(original[i][SIZE]);
        }

        for (Integer color : sizeByColor.keySet()) {
            List<Integer> group = sizeByColor.get(color);
            List<Integer> prefixGroup = sizePrefixSumByColor.get(color);
            Collections.sort(group);
            int partial = 0;
            for (Integer integer : group) {
                partial += integer;
                prefixGroup.add(partial);
            }
        }
        Collections.sort(sortedTotalSize);
        int partial = 0;
        for (int i = 0; i < size; i++) {
            partial += sortedTotalSize.get(i);
            totalPrefixSumSize[i] = partial;
        }
        solution();
    }

    public static void solution() {
        for (int[] marble : original) {
            // get target index in original total size
            int targetIndexInTotal = getTargetIndex(marble[SIZE], sortedTotalSize);
            if (targetIndexInTotal < 0) {
                System.out.println(0);
                continue;
            }

            // get target index in size by color
            int targetIndexInSpecificColor = getTargetIndex(marble[SIZE], sizeByColor.get(marble[COLOR]));

            if(targetIndexInSpecificColor < 0) System.out.println(totalPrefixSumSize[targetIndexInTotal]);
            // return prefix sum of target index
            else System.out.println(totalPrefixSumSize[targetIndexInTotal] - sizePrefixSumByColor.get(marble[COLOR]).get(targetIndexInSpecificColor));
        }
    }

    public static int getTargetIndex(int size, List<Integer> searchingTarget) {
        int start = 0;
        int end = searchingTarget.size();

        while (start <= end) {
            int mid = (start + end) / 2;
            if(searchingTarget.get(mid) < size) start = mid + 1;
            else end = mid - 1;
        }

        return start-1;
    }
}
