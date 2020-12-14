package exercises.chapter3;

import org.junit.Test;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Josephus问题(Josephus problem)是下面的游戏: N 个人编号从1到 N，围坐成一个圆圈。从1号开始传递一一个热土豆。
 * 经过 M 次传递后拿着热土豆的人被清除离座，围坐的圆圈缩紧，由坐在被清除的人后面的人拿起热土豆继续进行游戏。最后剩下的人取胜。
 * 因此，如果 M = 0 和 N = 5,则游戏人依序被清除，5号游戏人获胜。如果 M = 1 和 N = 5，那么被清除的人的顺序是 2，4，1，5。
 * <p>
 * a.编写一个程序解决 M 和 N 在一般值下的Josephus问题，应使程序尽可能地高效率，要确保能够清除各个单元。
 * b.你的程序的运行时间是多少?
 *
 * @author kubin
 * @version V1.0
 * @Package exercises.chapter3
 * @date 2020/12/14 10:59
 */
public class Topic6 {
    @Test
    public void test() {
        int m = 6;
        int n = 8;
        pass(m,n);
    }

    /**
     * 网上的实现，自己不会
     * @param m
     * @param n
     */
    public static void pass(int m, int n) {
        int i, j, mPrime, numLeft;
        ArrayList<Integer> L = new ArrayList<Integer>();
        for (i = 1; i <= n; i++) {
            L.add(i);
        }

        ListIterator<Integer> iter = L.listIterator();
        Integer item = 0;
        numLeft = n;
        /* -------------- 开始 N 次清除 -------------- */
        for (i = 0; i < n; i++) {
            //第一条关系应用，去除不必要的绕圈。
            mPrime = m % numLeft;
            //第一条关系后，根据第二条关系判定要往前还是往后走。
            if (mPrime <= numLeft / 2) {
                //清除过后，热土豆传给下一个人，从下一个人开始传M次
                if (iter.hasNext()) {
                    item = iter.next();
                }
                //传M次
                for (j = 0; j < mPrime; j++) {
                    //在传递途中，若iter遍历到末尾，重新获取iter
                    if (!iter.hasNext()) {
                        iter = L.listIterator();
                    }
                    item = iter.next();
                }
            } else {
                //第一条关系后，根据第二条关系判定要M > N/2, 往后走。
                for (j = 0; j < numLeft - mPrime; j++) {
                    //若iter在指针在第一个元素之前，则获取指向最后一个元素之后的iter
                    if (!iter.hasPrevious()) {
                        iter = L.listIterator(L.size());
                    }
                    item = iter.previous();
                }
            }
            System.out.print("Removed " + item + " ");
            iter.remove();
            //当remove最后一个元素后，重新或去iter，保证逻辑正确
            if (!iter.hasNext()) {
                iter = L.listIterator();
            }
            System.out.println();
            for (Integer x : L) {
                System.out.print(x + " ");
            }
            System.out.println();
            numLeft--;
        }
        /* -------------- 结束 N 次清除 -------------- */
        System.out.println();
    }
}
