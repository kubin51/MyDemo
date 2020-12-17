package exercises.chapter3;

import org.junit.Test;

/**
 * 保持单链表以排序的顺序重复练习Topic11。
 * 在每次添加元素时，遍历链表，当该值比节点值小时，在节点前插入该值；当该值比节点值大时，后移节点，再判断大小，小则插入大则继续后移。
 * 因此只需修改 addWhenNotExist 方法即可。
 * @author kubin
 * @version V1.0
 * @Package exercises.chapter3
 * @date 2020/12/16 16:18
 */
public class Topic12 {
    @Test
    public void test(){
        SNodeList<Integer> list = new SNodeList<Integer>();
        list.addWhenNotExist(4);
        list.addWhenNotExist(5);
        list.addWhenNotExist(3);
        list.addWhenNotExist(1);

        list.removeWhenExist(3);
        System.out.println(list);
    }

    class SNodeList<T extends Comparable<T>> {
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
                Node<T> p = head.next;
                Node<T> pPre = head;
                while(p != null){
                    if(p.value.compareTo(x)>0){
                        break;
                    }
                    pPre = p;
                    p = p.next;
                }
                pPre.next = new Node<T>(x,p);
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
