package exercises.chapter3;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定两个已排序的表L1和L2，只使用基本的表操作编写计算L1 ∩ L2的过程。
 * 给定两个已排序的表L1和L2，只使用基本的表操作编写计算L1 u L2的过程。
 * @author kubin
 * @version V1.0
 * @Package exercises.chapter3
 * @date 2020/12/14 9:59
 */
public class Topic4_5 {
    @Test
    public void test1(){
        List<String> l1 = new ArrayList<String>(Arrays.asList("c", "e", "e", "e", "i", "r", "s", "s", "x"));
        List<String> l2 = new ArrayList<String>(Arrays.asList("a","c","e","h","p","r","t","3"));
        long start = System.nanoTime();
//        List<String> res = printAandB(l1, l2);
        List<String> res = printAorB(l1, l2);
        long end = System.nanoTime();
        System.out.println("耗时："+(end-start));
        System.out.println(res);
    }

    /**
     * L1 ∩ L2
     * @param l1
     * @param l2
     * @param <T>
     * @return
     */
    public <T> List<T> printAandB(List<T> l1, List<T> l2) {
        ArrayList<T> result = new ArrayList<T>();
        for (T t1 : l1) {
            for (T t2 : l2) {
                if(t1==t2){
                    result.add(t1);
                }
            }
        }
        return result;
    }

    /**
     * L1 u L2
     * @param l1
     * @param l2
     * @param <T>
     * @return
     */
    public <T> List<T> printAorB(List<T> l1,List<T> l2){
        ArrayList<T> result = new ArrayList<T>();
        for (T t1 : l1) {
            result.add(t1);
        }
        for (T t2 : l2) {
            result.add(t2);
        }
        return result;
    }
}
