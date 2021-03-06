package adt;

import java.util.*;

/**
 * 自定义实现LinkedList
 *
 * @author kubin
 * @version V1.0
 * @Package adt
 * @date 2020/11/27 13:56
 */
public class MyLinkedList<T> implements Iterable<T> {
    /**
     * MyLinkedList 的大小
     */
    private Integer size;
    /**
     * MyLinkedList 被改变的次数
     */
    private Integer modCount = 0;
    /**
     * 头节点
     */
    private Node<T> beginNode;
    /**
     * 尾节点
     */
    private Node<T> endNode;

    public MyLinkedList() {
        init();
    }

    /**
     * 初始化函数
     */
    public void init() {
        beginNode = new Node<T>(null, null, null);
        endNode = new Node<T>(null, beginNode, null);
        endNode.prevNode = beginNode;
        size = 0;
        modCount++;
    }

    class Node<T> {
        private T data;
        private Node<T> prevNode;
        private Node<T> nextNode;

        public Node(T data, Node<T> prevNode, Node<T> nextNode) {
            this.data = data;
            this.prevNode = prevNode;
            this.nextNode = nextNode;
        }
    }

    /**
     * 获得集合大小
     *
     * @return 返回集合的大小
     */
    public Integer size() {
        return size;
    }

    /**
     * 在集合的末尾添加元素
     *
     * @param item
     * @return
     */
    public Boolean add(T item) {
        add(size(), item);
        return true;
    }

    /**
     * 在集合的指定位置添加元素
     *
     * @param index 指定的位置
     * @param item  要添加的集合内容
     */
    public void add(Integer index, T item) {
        addBefore(getNode(index, 0, size()), item);
    }

    /**
     * 清除当前集合下包含指定集合的项
     * @param items 指定集合
     */
    public void removeAll(Iterable<? extends T> items){
        modCount++;
        for (T item : items) {
            Iterator<T> iterator = this.iterator();
            while(iterator.hasNext()){
                if(iterator.next() == item){
                    iterator.remove();
                }
            }
        }
    }

    /**
     * 删除指定的节点
     *
     * @param index 指定的节点索引
     * @return 返回被删除的节点
     */
    public T remove(Integer index) {
        return remove(getNode(index));
    }

    /**
     * 删除节点
     *
     * @param node 要被删除的节点
     * @return 返回被删除的节点
     */
    public T remove(Node<T> node) {
        node.prevNode.nextNode = node.nextNode;
        node.nextNode.prevNode = node.prevNode;
        size--;
        modCount++;
        return node.data;
    }

    /**
     * 设置集合指定位置的节点内容
     *
     * @param index 要设置的节点索引
     * @param item  新的节点内容
     * @return 返回原节点的内容
     */
    public T set(Integer index, T item) {
        Node<T> node = getNode(index);
        T old = node.data;
        node.data = item;
        return old;
    }

    /**
     * 设置集合指定节点的节点内容
     * @param node 要设置内容的节点
     * @param item 指定的内容
     * @return 返回原节点内容
     */
    public T set(Node<T> node,T item){
        T old = node.data;
        node.data = item;
        return old;
    }

    /**
     * 在集合的指定节点前添加节点
     *
     * @param node 被添加的节点的后一个节点
     * @param item 被添加的节点内容
     */
    public void addBefore(Node<T> node, T item) {
        Node<T> newNode = new Node<T>(item, node.prevNode, node);
        newNode.prevNode.nextNode = newNode;
        node.prevNode = newNode;
        size++;
        modCount++;
    }

    /**
     * 获得指定节点的内容
     *
     * @param index 指定节点的索引
     * @return 返回指定节点的内容
     */
    public T get(Integer index) {
        Node<T> node = getNode(index);
        return node.data;
    }

    public Node<T> getNode(Integer index) {
        return getNode(index, 0, size() - 1);
    }

    /**
     * 获得节点
     *
     * @param index 节点所在位置的索引
     * @param lower 搜索节点位置的下限
     * @param upper 搜索节点位置的上限
     * @return 返回指定的节点
     */
    public Node<T> getNode(Integer index, Integer lower, Integer upper) {
        Node<T> p;
        if (index < lower || index > upper) {
            throw new IndexOutOfBoundsException();
        }
        if (index < size() / 2) {
            p = beginNode.nextNode;
            for (int i = 0; i < index; i++) {
                p = p.nextNode;
            }
        } else {
            p = endNode;
            for (int i = size(); i > index; i--) {
                p = p.prevNode;
            }
        }
        return p;
    }

    /**
     * 自己写的，很low，运行也很慢
     * @param data
     * @return
     */
    public Boolean contains2(T data){
        Iterator<T> iterator = this.iterator();
        while(iterator.hasNext()){
            if(iterator.next() == data){
                return true;
            }
        }
        return false;
    }

    /**
     * 自己写的，也很low，不过耗时跟网上的差不多
     * @param data
     * @return
     */
    public Boolean contains3(T data){
        Node<T> p = beginNode.nextNode;
        while(p!=endNode){
            if(p.data == data){
                return true;
            }
            p = p.nextNode;
        }
        return false;
    }

    /**
     * 网上搜的，很快（羞愧）
     * @param x
     * @return
     */
    public boolean contains(T x) {
        Node<T> p = beginNode.nextNode;
        while (p != endNode && !(p.data == x)) { //将判断置于while循环条件里。
            p = p.nextNode;
        }
        return (p != endNode); //这里返回的是 是否到达末尾。更简化了，而不是上述答案中的两个return。
    }

    public void addFirst(T x){
        addBefore(beginNode.nextNode,x);
    }
    public void addLast(T x){
        add(x);
    }
    public T removeFirst(){
        T data = beginNode.nextNode.data;
        if(data == null){
            throw new RuntimeException("暂无数据");
        }
        remove(beginNode.nextNode);
        return data;
    }
    public T removeLast(){
        T data = endNode.prevNode.data;
        if(data == null){
            throw new RuntimeException("暂无数据");
        }
        remove(endNode.prevNode);
        return data;
    }
    public T getFirst(){
        T data = get(0);
        if(data == null){
            throw new RuntimeException("暂无数据");
        }
        return data;
    }
    public T getLest(){
        T data = endNode.prevNode.data;
        if(data == null){
            throw new RuntimeException("暂无数据");
        }
        return data;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    public ListIterator<T> listIterator(){
        return new MyListIterator();
    }

    /**
     * 迭代器类
     */
    class MyIterator implements Iterator<T> {
        public Node<T> current = beginNode.nextNode;
        public Integer expectedModCount = modCount;
        private Boolean okToRemove = false;

        /**
         * 判断集合是否还有值
         *
         * @return true:有值，false:没有值
         */
        @Override
        public boolean hasNext() {
            return current != endNode;
        }

        @Override
        public T next() {
            if (!modCount.equals(expectedModCount)) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = current.data;
            current = current.nextNode;
            okToRemove = true;
            return data;
        }

        @Override
        public void remove() {
            if (!modCount.equals(expectedModCount)) {
                throw new ConcurrentModificationException();
            }
            if (!okToRemove) {
                throw new IllegalStateException();
            }
            MyLinkedList.this.remove(current.prevNode);
            expectedModCount++;
            okToRemove = false;
        }
    }

    /**
     * 集合迭代器类
     */
    class MyListIterator extends MyIterator implements ListIterator<T>{

        @Override
        public boolean hasPrevious() {
            return current.prevNode != beginNode.nextNode;
        }

        @Override
        public T previous() {
            if(hasPrevious()){
                return current.prevNode.prevNode.data;
            }else{
                throw new IndexOutOfBoundsException();
            }
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(T t) {
            expectedModCount++;
            MyLinkedList.this.set(current.prevNode,t);
        }

        @Override
        public void add(T t) {
            expectedModCount++;
            MyLinkedList.this.addBefore(current,t);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            sb.append(getNode(i).data);
            sb.append(",");
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }
}
