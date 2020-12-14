package adt;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author kubin
 * @version V1.0
 * @Package adt
 * @date 2020/11/25 14:19
 */
public class ADTTest {
    @Test
    public void test() {
//        ArrayList<Integer> list = new ArrayList<Integer>();
        LinkedList<Integer> list = new LinkedList<Integer>();
        for (int i = 0; i < 1600000; i++) {
            list.add(i);
        }
        long start = System.currentTimeMillis();
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() % 2 == 0) {
                iterator.remove();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
    }

    @Test
    public void test1() {
        MyArrayList<String> list = new MyArrayList<String>(Arrays.asList("ku","ku","xiao","shuai","bin"));
        list.add("ge");
        Iterator<String> iterator = list.iterator();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).contains("xiao")) {
                list.set(i, "da");
            }
        }
        System.out.println(list);
    }

    @Test
    public void test2() {
        MyLinkedList<Integer> linkedList = new MyLinkedList<Integer>();
        linkedList.add(0);
        linkedList.add(3);
        linkedList.add(6);
        linkedList.add(2);
        linkedList.add(4);
        linkedList.add(5);
        linkedList.removeAll(new ArrayList<Integer>(Arrays.asList(0,4,6)));
        System.out.println(linkedList);
        /*Iterator<Integer> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            Integer item = iterator.next();
            if (item % 2 == 0) {
                iterator.remove();
            }
        }*/
        /*for(int i=0;i<linkedList.size();i++){
            if(linkedList.get(i)%2==0){
                linkedList.set(i,1000);
            }
        }*/
//        1016500
//        long start = System.nanoTime();
//        Boolean contains = linkedList.contains3(2);
//        long end = System.nanoTime();
//        System.out.println("耗时："+(end-start)+"，结果为："+contains);
//        System.out.println(linkedList);
    }
}
//14488-3634