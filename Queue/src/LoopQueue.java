public class LoopQueue<E> implements Queue<E> {
    private E[] data;
    private int front, tail;
    private int size;

    public LoopQueue(int capacity){
        data  = (E[])new Object[capacity+1];//要浪费一个空间来判断是否队列满，详情见截图。并且要做强制类型转换。
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue(){
        this(10);
    }

    public int getCapacity(){
        return data.length - 1;
    }
    @Override
    public boolean isEmpty(){
        return front == tail;
    }
    @Override
    public int getSize(){
        return size;
    }

    @Override
    public void enqueue(E e){
        //首先判断是否是满的
        if ((tail + 1)% data.length == front){
            resize(getCapacity()*2);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    @Override
    public E dequeue(){
        if (isEmpty()){
            throw new IllegalArgumentException("can not dequeue from an empty queue!");
        }
        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        if(size == getCapacity()/4 && getCapacity()/2 !=0){
            resize(getCapacity()/2);
        }
        return ret;
    }

    @Override
    public E getFront(){
        if (isEmpty()){
            throw new IllegalArgumentException("can not dequeue from an empty queue!");
        }
        return data[front];
    }

    private void resize(int newCapacity) {
        E[] newData = (E[])new Object[newCapacity+1];
        for (int i = 0;i < size;i++ ){
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();//Constructs a string builder with no characters in it and an initial capacity of 16 characters.
        res.append(String.format("LoopQueue:size = %d , capacity = %d\n", size,getCapacity()));
        res.append("front [");
        for (int i = front; i != tail; i = (i+1) % data.length) {
            res.append(data[i]);
            if((i + 1) % data.length != tail){
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args){
        LoopQueue<Integer> queue = new LoopQueue<>();
        for (int i = 0;i < 10;i++){
            queue.enqueue(i);
            System.out.println(queue);
            if (i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }

    }

}
