public class SegmentTree<E> {

    private E[] data;//存传入数组
    private E[] tree;//存线段树
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger){//传入一个融合器来确定 左右子树得到 根节点的值
        this.merger = merger;
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < data.length; i++){
            data[i] = arr[i];
        }

        tree = (E[]) new Object[4 * arr.length];//最坏情况4倍。

        buildSegmentTree(0, 0, data.length - 1);
    }
    public E get(int index){
        if(index<0 || index>=data.length){
            throw new IllegalArgumentException("index is illegal");
        }
        return data[index];
    }

    //递归创建
    private void buildSegmentTree(int treeIndex, int l, int r) {
        // treeIndex要创建的根节点对应的index
        // 创建表示区间从【l...r】的线段树，lr是左右端点的索引
        // 递归创建，根节点是左右两节点的和（根据业务逻辑而定）
        if (r == l){//递归到底，长度为1，存储元素本身的值。
            tree[treeIndex] = data[l];//存储的信息是元素本身r也行。
            return;
        }
        int leftTree = leftChild(treeIndex);//左孩子的索引
        int rightTree = rightChild(treeIndex);//右孩子的索引
        //还要得到左右孩子各自表示的区间范围
//        int mid = (l + r) / 2;//可能产生整形溢出
        int mid = l + (r - l) / 2;//不会产生整形溢出，两边界中间位置
//        左孩子l，mid  右孩子mid + 1，r
        //创建左右两棵子树
        buildSegmentTree(leftTree, l, mid);
        buildSegmentTree(rightTree, mid + 1, r);

        tree[treeIndex] = merger.merge(tree[leftTree], tree[rightTree]);//当前节点的值

    }

    public E query(int queryL, int queryR){
        //返回取件l r的值
        if (queryL < 0 || queryR >= data.length || queryL > queryR){
            throw new IllegalArgumentException("index out of range");
        }
        //递归实现
        return query(0, 0, data.length - 1, queryL, queryR);
    }
    //在以treeIndex为根的线段树中[l, r]的范围里，搜索区间[queryL, queryR]的值
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        //递归到底
        if (l == queryL && r == queryR){
            return tree[treeIndex];//找到了返回此节点的值
        }
        int mid = l + (r - l) / 2;
        int leftChildIndex = leftChild(treeIndex);
        int rightChildIndex = rightChild(treeIndex);

        if (queryL >= mid + 1){
            //此时用户不关心左子树
            return query(rightChildIndex, mid + 1, r, queryL, queryR);
        }else if (queryR <= mid){
            return query(leftChildIndex, l, mid, queryL, queryR);
        }
        //两边都有涉及
        E leftResult = query(leftChildIndex, l, mid, queryL, mid);
        E rightResult = query(rightChildIndex, mid + 1, r, mid + 1, queryR);
        return merger.merge(leftResult, rightResult);
    }

    public int getSize(){
        return data.length;
    }

    //返回完全二叉树的数组表示中，一个索引所表示元素的左孩子节点的索引。
    private int leftChild(int index){
        return 2 * index + 1;
    }
    //返回完全二叉树的数组表示中，一个索引所表示元素的左孩子节点的索引。
    private int rightChild(int index){
        return 2 * index + 2;
    }
    //更新操作
    public void set(int index, E e){
        if (index < 0 || index >= data.length){
            throw new IllegalArgumentException("index out of range");
        }
        data[index] = e;
        set(0,0,data.length-1,index,e);
    }

    private void set(int treeIndex, int l, int r, int index, E e) {
        //针对treeIndex为根的线段树进行更新，[l,r]范围中，将index更新为e
        if(l==r){tree[treeIndex]=e;return;}
        int mid = l+(r-l)/2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (index>=mid+1){
            set(rightTreeIndex,mid+1,r,index,e);
        }else{
            set(leftTreeIndex, l,mid,index,e);
        }

        tree[treeIndex] = merger.merge(tree[rightTreeIndex],tree[leftTreeIndex]);
    }


    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append('[');
        for (int i = 0; i < tree.length ; i++){
            if (tree[i] != null){
                res.append(tree[i]);
            }else {
                res.append("null");
            }

            if (i != tree.length -1){
                //不是最后一个元素，什么都不做
                res.append(", ");
            }else {
                res.append(']');
            }
        }

        return res.toString();
    }

}
