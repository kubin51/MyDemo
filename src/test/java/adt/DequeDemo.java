package adt;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 *
 * @author kubin
 * @version V1.0
 * @Package adt
 * @date 2020/12/15 9:28
 */
public class DequeDemo {
    @Test
    public void test(){
        MaxQueue queue = new MaxQueue();
        queue.push_back(26);
        queue.push_back(18);
        queue.push_back(16);
        queue.push_back(25);
        queue.push_back(1);
        queue.push_back(13);
        // [26, 18, 16, 25, 1, 13]
        System.out.println(queue.q);
        // [26, 25, 13]
        System.out.println(queue.d);
    }
    class MaxQueue {
        Queue<Integer> q;
        Deque<Integer> d;

        public MaxQueue() {
            // 存储用户输入的队列
            q = new LinkedList<Integer>();
            // 存储最大值的双端队列
            d = new LinkedList<Integer>();
        }

        public int max_value() {
            if (d.isEmpty()) {
                return -1;
            }
            // 最大值队列的第一项即最大值
            return d.peekFirst();
        }

        public void push_back(int value) {
            // 在每次插入元素前先判断最大值队列中的最后一项是否小于新插入的值，小则弹出该双端队列的最后一项
            while (!d.isEmpty() && d.peekLast() < value) {
                d.pollLast();
            }
            // 插入队列的后端
            d.offer(value);
            q.offer(value);
        }

        public int pop_front() {
            if (q.isEmpty()) {
                return -1;
            }
            // 取出用户输入队列的前端值
            int ans = q.poll();
            // 如果取出的值是最大值队列的前端值，则需要取出最大值队列的前端值（表示该最大值已取出）
            if (ans == d.peekFirst()) {
                d.pollFirst();
            }
            return ans;
        }
    }
}
