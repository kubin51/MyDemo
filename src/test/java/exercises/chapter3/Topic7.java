package exercises.chapter3;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 下列程序的运行时间：
 * public static List<Integer> makeList(int N) {
 *     ArrayList<Integer> lst = new ArrayList<Integer>();
 *     for (int i = 0; i < N; i++) {
 *         lst.add(i);
 *         lst.trimToSize();
 *     }
 *     return lst;
 * }
 * @author kubin
 * @version V1.0
 * @Package exercises.chapter3
 * @date 2020/12/14 14:17
 */
public class Topic7 {
    @Test
    public void test(){
        System.out.println("运行时间为：O(N^2)");
    }

    public static List<Integer> makeList(int N) {
        ArrayList<Integer> lst = new ArrayList<Integer>();
        // O(n)
        for (int i = 0; i < N; i++) {
            // O(1)
            lst.add(i);
            // O(n)
            // Arrays.copyOf(elementData, size);
            lst.trimToSize();
        }
        return lst;
    }

}
