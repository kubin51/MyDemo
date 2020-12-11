package exercises.chapter3;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * 给定一个表L和另一个表P，它们包含以升序排列的整数。操作printLots(L，P)将打印L中那些由P所指定的位置上的元素。例如，
 * 如果P=1，3，4，6，那么，L中位于第1、第3、第4和第6个位置上的元素被打印出来。写出过程printLots(L，P)。
 * 只可使用public型的Collection API容器操作。该过程的运行时间是多少?
 * @author kubin
 * @version V1.0
 * @Package exercises.chapter3
 * @date 2020/12/10 13:27
 */
public class Topic1 {
    @Test
    public void test(){
        ArrayList<Integer> datas = new ArrayList<Integer>(Arrays.asList(6, 79, 2, 8, 0, 32, 87, 34, 762, 12, 54, 21, 54, 987, 3, 2, 4, 7, 9));
        ArrayList<Integer> indexs = new ArrayList<Integer>(Arrays.asList(1,2,3,7,10));
        long start = System.nanoTime();
        printLots2(datas,indexs);
        long end = System.nanoTime();
        System.out.println("耗时: "+(end-start));
    }

    /**
     * 自己写的，有点垃圾
     * @param L
     * @param P
     * @param <T>
     */
    public static <T> void printLots(Collection<T> L,Collection<Integer> P){
        Iterator<Integer> iterator = P.iterator();
        while(iterator.hasNext()){
            Integer index = iterator.next();
            int i=0;
            for(T data :L){
                if(index-1==i){
                    System.out.println(data);
                    break;
                }
                i++;
            }
        }
    }

    /**
     * 网上抄的，比自己写的快
     * @param L
     * @param P
     * @param <T>
     */
    public static <T> void printLots2(Collection<T> L, Collection<Integer> P) {
        Iterator<Integer> iterP = P.iterator();
        Iterator<T> iterL = L.iterator();
        int idxL = 0;
        T itemL = null;
        while (iterP.hasNext() && iterL.hasNext()) {
            Integer idx = iterP.next();
            while (iterL.hasNext() && idxL < idx) {
                itemL = iterL.next();
                idxL++;
            }
            System.out.print(itemL + " ");
        }
        System.out.println();
    }
}
