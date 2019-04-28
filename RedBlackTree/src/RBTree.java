import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringStack;

import java.util.*;

public class RBTree<K extends Comparable<K>,V> {//泛型必须可比较
    //todo 自己练习非递归实现
    private class Node{
        public K key;
        public V value;
        public Node left,right;
        public int height;//当前节点所处高度值
        public boolean color;

        public Node(K key,V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;//叶子节点
            color = RED;//默认红色
        }
    }

    public static final boolean RED = true;
    public static final boolean BLACK = false;

    private Node root;
    private int size;

    public RBTree(){
        root = null;
        size = 0;
    }
    private int getHeight(Node node){
        if (node == null)return 0;
        return node.height;
    }
    //计算节点的平衡因子
    private int getBalanceFactor(Node node){
        if (node==null)return 0;
        return getHeight(node.left) - getHeight(node.right);
    }
    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(K key,V value){
        root = add(root, key, value);
        root.color = BLACK;//保持最终的根节点为黑色节点
    }
    //判断节点node的元素
    private boolean isRed(Node node){
        if (node == null){
            return BLACK;
        }
        return node.color;
    }
    //向以node为根的红黑树添加元素e，递归算法
    //返回插入新节点后的红黑树的根
    //先插入，再判断，再调整
    private Node add(Node node, K key, V value){
        //递归终止条件

        if (node == null){
            size++;
            return new Node(key, value);//默认插入红节点
        }

        //进行递归调用,连接回去。判断插入哪
        if (key.compareTo(node.key)<0) node.left = add(node.left, key, value);
        else if (key.compareTo(node.key)>0) node.right = add(node.right, key, value);
        else node.value = value;

        //维护，ppt逻辑顺序执行，不是互斥关系
        if (isRed(node.right) && !isRed(node.left))//右孩子是红色，左孩子不是红色，左旋转操作
            node = leftRotate(node);
        if (isRed(node.left) && isRed(node.left.left))
            node = rightRotate(node);
        if (isRed(node.left) && isRed(node.right))
            flipColors(node);

        return node;//插入了以后的node还是得返回，重新串一遍，根节点返回上一层
    }
    //左旋转
    private Node leftRotate(Node node){
        Node x = node.right;
        node.right = x.left;
        x.left = node;

        x.color = node.color;//
        node.color = RED;
        return x;
    }
    //颜色反转
    private void flipColors(Node node){
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }
    //右旋转
    private Node rightRotate(Node node){
        Node x = node.left;
        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    private Node remove(Node node, K key){
        if (node == null)return null;

        Node retNode;
        if (key.compareTo(node.key)<0){
            node.left = remove(node.left, key);
            retNode = node;
        }
        else if (key.compareTo(node.key)>0){
            node.right = remove(node.right,key);
            retNode = node;
        }
        else {
            //找到
            if (node.left == null){//左子树为空
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;//返回的时候跳过这个节点
            }else if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            }else {
                //左右均不为空
                Node successor = minimum(node.right);//大于待删除节点的最小值
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;

                node.left = node.right = null;

                retNode = successor;
            }
        }

        if (retNode==null){//删除节点为叶子节点时，retNode可能为空。
            return null;
        }
        //判断是否需要维护平衡

        //更新height值
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));

        //计算平衡因子
        int banlanceFactor = getBalanceFactor(retNode);
        //不满足平衡二叉树条件，维护平衡性
        if (banlanceFactor>1 && getBalanceFactor(retNode.left)>=0) {//根左侧的左侧添加，导致不平衡,右旋转 node左子树高度>=右子树高度；左-右
            return rightRotate(retNode);
        }
        if (banlanceFactor<-1 && getBalanceFactor(retNode.right)<=0){//根右侧的右侧添加
            return leftRotate(retNode);
        }
        if (banlanceFactor>1 && getBalanceFactor(retNode.left)<0){//根左侧的右侧添加，导致不平衡
            retNode.left = leftRotate(retNode.left);//先对根节点左孩子左旋转
            return rightRotate(retNode);
        }
        if (banlanceFactor<-1 && getBalanceFactor(retNode.right)>0){//根右侧的左侧添加
            retNode.right = rightRotate(retNode.right);//先对根节点右孩子右旋转
            return leftRotate(retNode);
        }

        return retNode;//插入了以后的node还是得返回，重新串一遍，根节点返回上一层

    }


    //寻找最小值
    public V minimum(){
        if (size == 0) throw new IllegalArgumentException("BST is empty!");

        return minimum(root).value;
    }
    //返回node为根的最小值所在的节点
    private Node minimum(Node node) {
        if (node.left == null)return node;
        return minimum(node.left);//其实就类似于访问一个链表，自己写非递归算法。
    }

    //寻找最大值
    public V maximum(){
        if (size == 0) throw new IllegalArgumentException("BST is empty!");

        return maximum(root).value;
    }
    //返回node为根的最大值所在的节点
    private Node maximum(Node node) {
        if (node.right == null)return node;
        return maximum(node.right);//其实就类似于访问一个链表，自己写非递归算法。
    }

    //删除最小值所在节点，返回最小值
    public V removeMin(){
        V ret = minimum();//返回
        root = removeMin(root);
        return ret;
    }

    // 删除node为根的最小节点
    // 返回删除节点后新的BST的根
    private Node removeMin(Node node) {
        if (node.left == null){
            //当前是最小值节点，可能有右子树
            Node rightNode = node.right;//新的BST的根
            node.right = null;//断链右子树
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);//返回的根连在左节点下
        return node;//返回根
    }

    //删除最大值所在节点，返回最大值
    public V removeMax(){
        V ret = maximum();//返回
        root = removeMax(root);
        return ret;
    }

    // 删除node为根的最大节点
    // 返回删除节点后新的BST的根
    private Node removeMax(Node node) {
        if (node.right == null){
            //当前是最大值节点，可能有左子树
            Node leftNode = node.left;//新的BST的根
            node.left = null;//断链左子树
            size--;
            return leftNode;//返回左子树为 根
        }
        node.right = removeMax(node.right);//返回的 根 连在右节点下
        return node;//返回该节点继续链接
    }


    public boolean isBST(){
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root,keys);
        for (int i=0; i<keys.size();i++){
            if (keys.get(i-1).compareTo(keys.get(i))>0)return false;
        }
        return true;
    }
    //判断是否是平衡二叉树
    public boolean isBalanced(){
        return isBalanced(root);
    }
    //递归判断是否是平衡二叉树
    private boolean isBalanced(Node node) {
        if (node==null)return true;//空树是平衡二叉树
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor)>1)return false;
        return isBalanced(node.left) && isBalanced(node.right);
    }


    //中序遍历
    public void inOrder(Node node ,ArrayList<K> keys){
        if (node == null)return;
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    private void inOrder(Node node) {
        if (node == null)return;

        inOrder(node.left);
        System.out.println(node.value);
        inOrder(node.right);
    }

    //后序遍历，应用：内存释放，先处理完孩子进程，再处理根进程
    public void postOrder(){
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node == null){
            return;
        }

        inOrder(node.left);
        inOrder(node.right);
        System.out.println(node.value);
    }






    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if (node == null){
            res.append(generateDepthSting(depth) + "null\n");
            return;//别忘了return
        }
        res.append(generateDepthSting(depth) + node.value + "\n");

        //递归访问左右子树
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthSting(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++){
            res.append("--");
        }
        return res.toString();
    }

}
