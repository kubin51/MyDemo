package adt;

import java.lang.Object;

import java.util.*;

/**
 * 自定义实现 ArrayList
 *
 * @author kubin
 * @version V1.0
 * @Package adt
 * @date 2020/11/26 11:34
 */
public class MyArrayList<T> implements Iterable<T> {
    /**
     * MyArrayList 的大小
     */
    private Integer size;
    /**
     * MyArrayList 的存储容器
     */
    private T[] items;
    /**
     * MyArrayList 的存储容器的初始化容量
     */
    private static final Integer DEFAULT_CAPACITY = 10;

    public MyArrayList(List datas) {
        size = 0;
        ensureCapacity(DEFAULT_CAPACITY);
        addAll(datas);
    }

    public MyArrayList() {
        size = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    /**
     * 添加指定的集合到当前集合的末尾
     *
     * @param items 指定集合
     */
    public void addAll(Iterable<? extends T> items) {
        Iterator<? extends T> iterator = items.iterator();
        while (iterator.hasNext()) {
            add(iterator.next());
        }
    }

    public Boolean add(T item) {
        add(size, item);
        return true;
    }

    /**
     * 向 MyArrayList 中添加元素
     *
     * @param index 添加的新元素的位置
     * @param item  添加的新元素的内容
     */
    public void add(Integer index, T item) {
        // 容量满了，两倍扩容
        if (items.length == size) {
            ensureCapacity(size * 2);
        }
        rangeCheck(index);
        // 集合从添加新元素位置开始向后复制，即整体后移一位
        if (size - index > 0) {
            System.arraycopy(items, index, items, index + 1, size - index);
        }
        items[index] = item;
        size++;
    }

    /**
     * 删除 MyArrayList 中的元素
     *
     * @param index 要删除元素的位置
     * @return 返回已删除的元素
     */
    public T remove(Integer index) {
        rangeCheck(index);
        T removeItem = items[index];
        if (size - index > 0) {
            System.arraycopy(items, index + 1, items, index, size - index);
        }
        size--;
        return removeItem;
    }

    /**
     * 修改指定位置的集合内容
     *
     * @param index 指定的位置
     * @param item  指定的内容
     * @return 返回指定位置的旧值
     */
    public T set(Integer index, T item) {
        rangeCheck(index);
        T oldValue = items[index];
        items[index] = item;
        return oldValue;
    }

    /**
     * 获得指定位置上的集合内容
     *
     * @param index 指定的位置
     * @return 指定位置上集合的内容
     */
    public T get(Integer index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return items[index];
    }

    public Integer size() {
        return size;
    }

    /**
     * 集合扩容
     *
     * @param newCapacity 新的集合大小
     */
    public void ensureCapacity(Integer newCapacity) {
        if (size > newCapacity) {
            return;
        }
        T[] old = items;
        items = (T[]) new Object[newCapacity];
        if (size > 0) {
            System.arraycopy(old, 0, items, 0, size);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    public ListIterator<T> listIterator() {
        return new MyListIterator();
    }

    /**
     * 迭代器类
     */
    private class MyIterator implements Iterator<T> {
        /**
         * 当前位置
         */
        public Integer current = 0;

        /**
         * 判断集合还是否有值
         *
         * @return true:有，false:没有
         */
        @Override
        public boolean hasNext() {
            return current < size;
        }

        /**
         * 获得当前集合中的下一项
         *
         * @return 集合的下一项
         */
        @Override
        public T next() {
            if (!hasNext()) {
                throw new IndexOutOfBoundsException();
            }
            return items[current++];
        }

        /**
         * 删除集合的当前项
         */
        @Override
        public void remove() {
            MyArrayList.this.remove(--current);
        }

    }

    /**
     * 集合迭代器类
     */
    private class MyListIterator extends MyIterator implements ListIterator<T> {

        @Override
        public boolean hasPrevious() {
            return current != 0;
        }

        @Override
        public T previous() {
            rangeCheck(current - 2);
            return MyArrayList.this.get(current - 2);
        }

        @Override
        public int nextIndex() {
            if(current>=size){
                throw new IndexOutOfBoundsException();
            }
            return current;
        }

        @Override
        public int previousIndex() {
            rangeCheck(current - 2);
            return current - 2;
        }

        @Override
        public void set(T t) {
            MyArrayList.this.set(current-1, t);
        }

        @Override
        public void add(T t) {
            MyArrayList.this.add(current-1, t);
            current++;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(items[i]);
            sb.append(",");
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    /**
     * 检查指定数组下标是否越界
     *
     * @param index 指定数组下标
     */
    public void rangeCheck(Integer index) {
        if (index < 0 || index > items.length-1) {
            throw new IndexOutOfBoundsException();
        }
    }
}
