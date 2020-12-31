package adt;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

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
        MyArrayList<String> list = new MyArrayList<String>(Arrays.asList("ku","k","xiao","shuai","bin"));
        list.add("ge");
        /*Iterator<String> iterator = list.iterator();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).contains("xiao")) {
                list.set(i, "da");
            }
        }*/
        ListIterator<String> listIterator = list.listIterator();
        while(listIterator.hasNext()){
            if(listIterator.next() == "bin"){
                System.out.println(listIterator.next());
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
        System.out.println(linkedList);
    }

    @Test
    public void test3(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        int m = 20000;
        for(int i=1;i<=m;i++){
            list.add(i);
        }
        long start = System.nanoTime();
        System.arraycopy(list.toArray(),0,list.toArray(),1,list.size()-1);
        long end = System.nanoTime();
        System.out.println("耗时："+(end-start));
    }

    @Test
    public void test4(){
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<Integer>(Arrays.asList(1,null,6,3));
        int i = list.indexOf(null);
        System.out.println(i);
    }
    @Test
    public void test5(){

    }
}
//14488-3634