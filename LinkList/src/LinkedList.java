public class LinkedList<E> {
    private class Node{
        public E e;
        public Node next;

        public Node(E e, Node next){
            this.e= e;
            this.next= next;
        }

        public Node(E e){
            this(e, null);
        }

        public Node(){
            this(null, null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }
    private Node dummyHead;//虚拟头节点
    private int size;//链表中的元素

    public LinkedList(){
        dummyHead = new Node(null,null);
        size = 0;
    }

    //获取链表中的元素个数
    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }



    //通过index来插入，不是常用操作
    public void add(int index, E e){
        if (index < 0 || index > size){
            throw new IllegalArgumentException("Add failed;Illegal index.");
        }
        Node prev = dummyHead;//指向0元素之前的节点
        for (int i = 0;i < index;i++){
            prev = prev.next;
        }
        Node node = new Node(e, prev.next);
        prev.next = node;

        size++;
    }

    //通过index来插入，不是常用操作
    public E get(int index){
        if (index < 0 || index > size){
            throw new IllegalArgumentException("Get failed;Illegal index.");
        }
        Node cur = dummyHead.next;
        for (int i = 0; i < index;i++){
            cur = cur.next;
        }
        return cur.e;
    }

    //删除index指定位置元素
    public E remove(int index){
        Node prev = dummyHead;//指向0号元素之前的节点
        for (int i = 0; i < index;i++){
            prev = prev.next;
        }
        Node temp = prev.next;
        prev.next = temp.next;
        temp.next = null;
        size--;
        return temp.e;
    }

    //通过index来修改，不是常用操作
    public void set(int index,E e){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("Set failed;Illegal index.");
        }
        Node cur = dummyHead.next;
        for (int i = 0; i < index;i++){
            cur = cur.next;
        }
        cur.e = e;
    }

    //查找链表中是否有元素e
    public boolean contains(E e){
        Node cur = dummyHead.next;
        while (cur != null){
            if (cur.e.equals(e))return true;
            cur = cur.next;
        }
        return false;
    }

    //获得链表第一个元素
    public E getFirst(){
        return get(0);
    }

    //获得链表最后一个元素
    public E getLast(){
        return get(size - 1);
    }

    public E removeFirst(){
        return remove(0);
    }

    public void removeLast(){
        remove(size-1);
    }

    //在链表的末尾添加
    public void addLast(E e){
        add(size, e);
    }
    //链表头添加元素e
    public void addFirst(E e){
//        Node node = new Node(e);
//        node.next = head;
//        head = node;
//        size ++;
        add(0, e);
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        Node cur = dummyHead.next;
        while (cur != null){
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("Null");
        return res.toString();
    }

}
