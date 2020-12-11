package exercises.chapter3;

import org.junit.Test;

/**
 * 通过只调整链(而不是数据)来交换两个元素（假设链表中没有重复元素）,使用
 * a.单链表。
 * b.双链表。
 *
 * @author kubin
 * @version V1.0
 * @Package exercises.chapter3
 * @date 2020/12/10 16:40
 */
public class Topic2 {
    /**
     * 双向链表节点类
     *
     * @param <T>
     */
    class DNode<T> {
        T value;
        DNode<T> pre;
        DNode<T> next;
    }

    /**
     * 单向链表节点类
     *
     * @param <T>
     */
    class SNode<T> {
        T value;
        SNode<T> next;
    }

    /**
     * 双链表测试
     */
    @Test
    public void test1() {
        // 初始化双向链表
        DNode<Integer> current = new DNode<Integer>();
        DNode<Integer> node = current;
        for (int i = 0; i < 10; i++) {
            node.next = new DNode<Integer>();
            node.next.pre = node;
            node.next.value = i;
            node = node.next;
        }
        // 交换指定节点与下一个节点 3 <=> 4
//        exchangeDNode(current.next.next.next.next);
        // 交换指定的两个节点 1 <=> 4
        exchangeDData(current.next.next, current.next.next.next.next.next);

        // 打印交换后的节点信息
        DNode<Integer> p = current.next;
        while (p != null) {
            System.out.print(p.value + " ");
            p = p.next;
        }
    }

    /**
     * 单链表测试
     */
    @Test
    public void test2() {
        SNode<Integer> node = new SNode<Integer>();
        SNode<Integer> current = node;
        for (int i = 0; i < 10; i++) {
            current.next = new SNode<Integer>();
            current.next.value = i;
            current = current.next;
        }
//        exchangeSData(node.next.next.next);
        exchangeSNode(node, node.next, node.next);
        SNode<Integer> p = node.next;
        while (p != null) {
            System.out.print(p.value + " ");
            p = p.next;
        }
        System.out.println();
    }

    /**
     * 交换连续的两个双向链表节点（只调整链，不交换数据）
     *
     * @param first 要交换的第一个节点
     * @param <T>   节点内容的类型
     */
    public <T> void exchangeDNode(DNode<T> first) {
        // 暂存第二个节点
        DNode<T> second = first.next;
        // 第二个节点移到第一个节点前面
        first.pre.next = second;

        // 设置第一个节点的前后节点
        first.next = second.next;
        first.pre = second;

        // 设置第二个节点的前后节点
        second.next = first;
        second.pre = first.pre;
    }

    /**
     * 交换指定的两个双向链表节点（还是交换数据更方便）
     *
     * @param first  指定的第一个节点
     * @param second 指定的第二个节点
     * @param <T>    指定节点内容的数据类型
     */
    public <T> void exchangeDData(DNode<T> first, DNode<T> second) {
        T item = first.value;
        first.value = second.value;
        second.value = item;
    }

    /**
     * 交换连续的两个单向链表节点(交换数据)
     *
     * @param first 第一个要交换的节点
     * @param <T>   节点内容类型
     */
    public <T> void exchangeSData(SNode<T> first) {
        T item = first.value;
        first.value = first.next.value;
        first.next.value = item;
    }

    /**
     * 交换指定的两个单向链表节点（只调整链，不交换数据）
     *
     * @param first  指定的第一个节点
     * @param second 指定的第二个节点
     * @param <T>    节点内容类型
     */
    public <T> void exchangeSNode(SNode<T> head, SNode<T> first, SNode<T> second) {
        if (first.value != second.value) {
            SNode<T> firstPre = new SNode<T>();
            SNode<T> secondPre = new SNode<T>();
            while (head.next != null) {
                if (head.next.value == first.value) {
                    firstPre = head;
                }
                if (head.next.value == second.value) {
                    secondPre = head;
                }
                if (firstPre.value != null && secondPre.value != null) {
                    break;
                }
                head = head.next;
            }
            SNode<T> node1 = first.next;
            SNode<T> node2 = second.next;
            if (firstPre.value == second.value || secondPre.value == first.value) {
                // 相邻节点交换
                firstPre.next = second;
                first.next = node2;
                second.next = first;
            }else{
                // 不相邻节点交换
                firstPre.next=second;
                secondPre.next = first;
                first.next = node2;
                second.next = node1;
            }
        }else{
            System.out.println("同一个节点无需交换");
        }
    }
}
