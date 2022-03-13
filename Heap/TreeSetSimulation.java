package Heap;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetSimulation {

    public static class reverseComparator  implements Comparator<Integer>{
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }

    }
    public static void main(String[] args) {
        Set<Integer> treeSet = new TreeSet<>(new reverseComparator());

        treeSet.add(20);
        treeSet.add(10);
        treeSet.add(30);

        for(Iterator<Integer> itr = treeSet.iterator(); itr.hasNext();){
            System.out.println(itr.next());
        }


    }
}
