package Sort;

public class QuickSort {
    static int[] arrays = {5, 3, 8, 4, 9, 1, 6, 2, 7};
    public static void main(String[] args) {
        quickSort(0, arrays.length-1);

        for(int elm: arrays) {
            System.out.println(elm);
        }
    }

    public static void quickSort(int start, int end) {

        if (start >= end) return;

        int pivot = arrays[start];
        int low = start + 1;
        int high = end;


        while(low < high) {
            while (low <= end && arrays[low] <= pivot) low++;
            while (high >= start && arrays[high] > pivot) high--;

            if(low < high) changeValue(low, high);
        }

        // high 위치에 피봇 값 넣기
        changeValue(start, high);

        quickSort(start, high-1);
        quickSort(high+1, end);

    }
    
    public static void changeValue(int s, int e) {
        int temp = arrays[s];
        arrays[s] = arrays[e];
        arrays[e] = temp;
    }
}
