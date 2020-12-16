package exercises.chapter3;

import org.junit.Test;

import java.util.*;

/**
 * 假设单链表使用一个头节点实现，但无尾节点，并假设它只保留对该头节点的引用。编写一个类，包含：
 * a.返回链表大小的方法
 * b.打印链表的方法
 * c.测试值x是否含于链表的方法
 * d.如果值尚未含于链表，添加值x到链表的方法
 * e.如果值含于链表，将x从链表中删除的方法
 *
 * @author kubin
 * @version V1.0
 * @Package exercises.chapter3
 * @date 2020/12/14 15:09
 */
public class Topic11 {
    @Test
    public void test() {
        SNodeList<Integer> list = new SNodeList<Integer>();
        list.addWhenNotExist(4);
        list.addWhenNotExist(3);
        list.addWhenNotExist(1);
        list.addWhenNotExist(2);
        list.addWhenNotExist(5);

        list.removeWhenExist(2);
        System.out.println(list);
    }

    class SNodeList<T> {
        private Node<T> head;

        public SNodeList() {
            head = new Node<T>(null, null);
        }

        /**
         * a.返回链表大小的方法
         *
         * @return
         */
        public Integer size() {
            Integer result = 0;
            Node<T> p = head;
            while (p.next != null) {
                result++;
                p = p.next;
            }
            return result;
        }

        /**
         * b.打印链表的方法
         *
         * @return
         */
        @Override
        public String toString() {
            StringBuffer sb = new StringBuffer();
            Node<T> p = head;
            while (p.next != null) {
                sb.append(p.next.value + " ");
                p = p.next;
            }
            return sb.toString();
        }

        /**
         * c.判断值x是否含于链表的方法
         *
         * @param x
         * @return
         */
        public Boolean contains(T x) {
            Node<T> p = head;
            while (p.next != null) {
                if (p.next.value == x) {
                    return true;
                }
                p = p.next;
            }
            return false;
        }

        /**
         * d.如果值尚未含于链表，添加值x到链表的方法
         *
         * @param x
         */
        public void addWhenNotExist(T x) {
            if (!contains(x)) {
                Node<T> p = head;
                while (p.next != null) {
                    p = p.next;
                }
                p.next = new Node<T>(x, null);
            } else {
                throw new RuntimeException("该值已存在");
            }
        }

        /**
         * e.如果值含于链表，将x从链表中删除的方法
         *
         * @param x
         */
        public void removeWhenExist(T x) {
            if (contains(x)) {
                Node<T> p = head.next;
                Node<T> pPre = head;
                while (p != null) {
                    if (p.value == x) {
                        pPre.next = pPre.next.next;
                        break;
                    }
                    pPre = p;
                    p = p.next;
                }
            } else {
                throw new RuntimeException("该值不存在");
            }
        }

        class Node<T> {
            private T value;
            private Node<T> next;

            public Node(T value, Node<T> next) {
                this.value = value;
                this.next = next;
            }
        }
    }
}
