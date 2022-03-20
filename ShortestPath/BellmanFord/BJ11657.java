package ShortestPath.BellmanFord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 다익스트라에 대한 이야기
 * 일반적으로 다익스트라는 모든 경로 상의 가중치가 양수인 경우에만 적용이 가능하다.
 * 그 이유는 다익스트라 알고리즘은 출발 노드(s)에서 도착 노드(d)까지 이동할 수 있는 노드 중 방문하지 않는 노드에서 그 가중치가 최솟값인 노드(m)를 선택한다.
 * 최솟값을 선택하는 과정에서 그 w(s, *) 까지 이동하는 모든 경로 중 그 사이에 음수의 가중치를 가진 값이 없기 때문에 반드시 선택된 m의 가중치 값이 최솟값임을 보장할 수 있다.
 * 이러한 이유하에 다익스트라는 s에서 d까지 이동하는 최단 경로를 조사하는 중에 현재 정의된 가중치 중 최솟값을 조사해서 그 최소값을 기준으로 각 연관 노드들의 값을 갱신하는 과정을 반복할 수 있다.
 * 단, 반드시 모든 가중치들이 양수의 값을 가진다는 가정하다.
 *
 * 하지만 이 타임머신 문제는 전형적인 벨만포드 알고리즘을 위한 유형으로 보인다.
 * 우선, 그렇게 판단한 생각의 흐름을 정리해 보면
 * 첫번째로 "가장 빠른 시간을 구하는" 이라는 구절을 통해 최단 경로 알고리즘이라는 판단을 했다.
 * 최단 경로 알고리즘이라면 아래와 같은 크게 4가지 정도의 알고리즘을 떠올려 볼 수 있었다.
 * 1. 다익스트라
 * 2. 벨만 포드
 * 3. 플로이드 와샬
 * 4. 최소 신장 트리
 * 두번째로 최소 신장 트리는 사이클의 형태가 존재하지 않는 모든 정점을 연결하는 최소 경로를 구하는 트리의 형태임으로 내용에서 배제한다.
 * 세번째로 문제에서 요구하는 바와 같이 모든 정점끼리의 연결이 필요한 상황이 아닌 1번 노드에서 다른 정점으로 가는 최단 경로를 구하는 문제임으로 플로이드 와샬 또한 배제한다.
 * 마지막으로 각 가중치가 양수만 존재하는 것이 아닌 음수 또한 존재함으로 다익스트라를 적용할 수 없는 구조임으로 벨만 포드를 이용해야 겠다는 생각에 이르렀다.
 * 뿐만 아니라 문제에서 조차 "시간을 무한히 오래 전으로 되돌릴 수 있다면" 이라는 구절로 negative cycle에 대한 언급을 해주었기때문에 두말할 것도 없이 벨만 포드를 적용해야겠다는 확신이 섰다.
 *
 */

public class BJ11657 {
    static final int START = 1;
    static final long NOT_ARRIVED = Long.MAX_VALUE;
    static int node;
    static int edge;
    static int[][] edges;
    static long[][] minWight;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        node = Integer.parseInt(st.nextToken());
        edge = Integer.parseInt(st.nextToken());
        edges = new int[edge][];
        minWight = new long[node+1][node+1];

        for (long[] weight : minWight) {
            Arrays.fill(weight, NOT_ARRIVED);
        }

        for (int e = 0; e < edge; e++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges[e] = new int[] {start, dest, weight};
        }

        solution();

    }

    public static void solution() {
        boolean hasNegativeCycle = bellmanFord();
        if(hasNegativeCycle) System.out.println(-1);
        else {
            for (int i = START + 1; i < node + 1; i++) {
                if(minWight[START][i] == NOT_ARRIVED) System.out.println(-1);
                else System.out.println(minWight[START][i]);
            }
        }


    }

    public static boolean bellmanFord() {
        for (int i = 0; i < node; i++) {
            for (int[] targetEdge : edges) {
                if(minWight[START][targetEdge[0]] == NOT_ARRIVED) continue;

                if (minWight[START][targetEdge[1]] > minWight[START][targetEdge[0]] + targetEdge[2]) {
                    minWight[START][targetEdge[1]] = minWight[START][targetEdge[0]] + targetEdge[2];

                    if (i == node-1) return true;
                }
            }
        }
        return false;
    }
}
