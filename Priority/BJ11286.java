package Priority;

import java.util.PriorityQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

class Number {
  int num;
  int order;

  protected Number(int num, int order) {
    this.num = num;
    this.order = order;
  }

  @Override
  public int hashCode() {
    StringBuffer buffer = new StringBuffer();
    buffer.append(this.num);
    buffer.append(this.order);
    // System.out.println("hash code" + buffer.toString());
    return buffer.toString().hashCode();
  }

  @Override
  public boolean equals(Object object) {
    if (object == null)
      return false;
    if (object == this)
      return true;
    CustomNumber number = (CustomNumber) object;
    // System.out.println("this: " + this.hashCode() + "number: " +
    // number.hashCode());
    if (this.hashCode() == number.hashCode() && this.order == number.order && this.num == number.num)
      return true;
    return false;
  }

}

//class HighPriority extends CustomNumber implements Comparable<CustomNumber> {
//
//  HighPriority(int num, int order) {
//    super(num, order);
//  }
//
//  @Override
//  public int compareTo(CustomNumber o) {
//    return o.num - num;
//  }
//}
//
//class LowPriority extends CustomNumber implements Comparable<CustomNumber> {
//
//  LowPriority(int num, int order) {
//    super(num, order);
//  }
//
//  @Override
//  public int compareTo(CustomNumber o) {
//    return num - o.num;
//  }
//}

public class BJ11286 {

  static Set<CustomNumber> shared = new HashSet<>();
  static BufferedReader br;

  static final String INSERT = "I";
  static final String DELETE = "D";

  public static void main(String[] args) throws NumberFormatException, IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    int testcase = Integer.parseInt(br.readLine());
    while (testcase-- > 0) {
      solution();
    }

  }

  public static void solution() throws NumberFormatException, IOException {
    StringTokenizer st;

    Queue<CustomNumber> highPQueue = new PriorityQueue<>();
    Queue<CustomNumber> lowPQueue = new PriorityQueue<>();

    int k = Integer.parseInt(br.readLine());

    while (k-- > 0) {
      st = new StringTokenizer(br.readLine());

      String op = st.nextToken();
      int num = Integer.parseInt(st.nextToken());

      if (op.equals(INSERT)) {
        highPQueue.add(new HighPriority(num, k));
        lowPQueue.add(new LowPriority(num, k));
        continue;
      }

      // 높은 우선순위에서 제거
      if (num == 1) {
        deleteQueue(highPQueue);
      } else {
        deleteQueue(lowPQueue);
      }

    }

    while (!highPQueue.isEmpty() && shared.contains(highPQueue.peek()))
      highPQueue.poll();
    while (!lowPQueue.isEmpty() && shared.contains(lowPQueue.peek()))
      lowPQueue.poll();

    // Integer high = deleteQueue(highPQueue);
    // Integer low = deleteQueue(lowPQueue);

    if (highPQueue.isEmpty() || lowPQueue.isEmpty())
      System.out.println("출력" + "EMPTY");
    else {
      System.out.println("출력" + highPQueue.peek().num + " " + lowPQueue.peek().num);
    }

  }

  public static Integer deleteQueue(Queue<CustomNumber> queue) {
    while (!queue.isEmpty() && shared.contains(queue.peek()))
      queue.poll();

    if (!queue.isEmpty()) {
      CustomNumber val = queue.poll();
      shared.add(val);

      return val.num;
    }

    return null;
  }

}
