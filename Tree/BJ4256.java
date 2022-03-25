package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ4256 {
    static int T;
    static int[] preOrder;
    static int[] inOrder;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            preOrder = new int[N];
            inOrder = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                preOrder[j] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                inOrder[j] = Integer.parseInt(st.nextToken());
            }

            Node root = new Node(preOrder[0]);
            divideAndConquer(root, 0, N-1, 0, N-1);
            Tree tree = new Tree(root);
            tree.postOrder(root);
        }
    }
    public static void divideAndConquer(Node root, int preOrderStart, int preOrderEnd, int inOrderStart, int inOrderEnd) {
        if(root == null) return;
        if(preOrderStart >= preOrderEnd || inOrderStart >= inOrderEnd) return;
        // inOrder에서 root 값을 가지고 오른쪽 왼쪽 분리
        Set<Integer> leftSet = new HashSet<>();
        int i = inOrderStart;
        while (inOrder[i] != root.value) {
            leftSet.add(inOrder[i++]);
        }

        int idx = preOrderStart+1;
        while(idx < preOrder.length && leftSet.contains(preOrder[idx])){idx++;};

        if(idx-1 != preOrderStart) root.left = new Node(preOrder[preOrderStart+1]);
        if(preOrderStart+1 < idx && idx <= preOrderEnd) root.right = new Node(preOrder[idx]);
        divideAndConquer(root.left,preOrderStart+1, idx-1, inOrderStart, i-1);
        divideAndConquer(root.right, idx, preOrderEnd, i+1, inOrderEnd);
    }

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    static class Tree {
        Node root;

        Tree(Node root) {
            this.root = root;
        }

        void postOrder(Node target) {
            if(target.left != null) postOrder(target.left);
            if(target.right != null) postOrder(target.right);
            System.out.printf("%d ",target.value);
        }
    }
}
