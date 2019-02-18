public class MaxHeap<E extends Comparable<E>> {
    //堆排序时间复杂度均是logn，不存在二分搜索树的最坏退化情况
    private Array<E> data;

    public MaxHeap(int capacity){
        data = new Array<>(capacity);
    }

    public MaxHeap(){
        data = new Array<>();
    }

    public int size(){
        return data.getSize();
    }

    public boolean isEmpty(){
        return data.isEmpty();
    }

    private int parent(int index){
        //返回所表示元素父亲节点的索引
        if (index == 0){
            throw new IllegalArgumentException("index-0 doesn't have parent!");
        }
        return (index - 1) / 2;
    }

    //返回左孩子的索引
    private int leftChild(int index){
        return index * 2 + 1;//第一个元素index是0
    }

    //返回右孩子的索引
    private int rightChild(int index){
        return index * 2 + 2;
    }
    //向堆中添加元素
    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    //上浮，找父亲节点
    private void siftUp(int index){
        while (index > 0 && data.get(parent(index)).compareTo(data.get(index)) < 0){
            data.swap(parent(index), index);
            index = parent(index);
        }
    }

    //取出最大元素，只取出堆顶，然后进行数据下沉
    public E extractMax(){
        E ret = findMax();
        //最末尾与堆顶交换
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        //数据下沉
        siftDown(0);

        return ret;
    }

    private void siftDown(int k) {
        // 没有左孩子即左右孩子皆无，停止下沉
        while (leftChild(k) < data.getSize()){
            //有左孩子
            int j = leftChild(k);//此时左孩子的index
            if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0){
                //有右孩子，且右孩子大于左孩子
                j = rightChild(k);
            }
            //此时data[j]是 左右孩子中的最大值
            if (data.get(k).compareTo(data.get(j)) > 0){
                //可以结束了，父节点大于子节点中的最大值
                break;
            }else {
                data.swap(k ,j);
                k = j;//进入新的一轮比较
            }
        }
    }

    //查看堆中最大元素
    public E findMax() {
        if (data.getSize() == 0){
            throw new IllegalArgumentException("Heap is empty");
        }
        return data.get(0);
    }

    //取出最大元素，替换成元素e
    public E replace(E e){
        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }

    //整理成堆
    public MaxHeap(E[] arr){
        //找到最后一个非叶子节点，就是找到最后一个节点的父亲节点
        //不断下沉，直到到0
        //这样比向一个空堆添加要快，理解为有一半的元素压根就没考虑进去。O（n）
        data = new Array<>(arr);

        for (int i = parent(arr.length - 1); i >= 0; i--){
            siftDown(i);
        }
    }


}
