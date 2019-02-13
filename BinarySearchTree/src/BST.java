import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringStack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST<E extends Comparable<E>> {//泛型必须可比较
    //todo 自己练习非递归实现
    private class Node{
        public E e;
        public Node left,right;

        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(E e){
//        if(root == null){
//            root = new Node(e);
//            size++;
//        }
//        else {
//            add(root, e);
//        }
        root = add(root, e);
    }
    //向以node为根的BST添加元素e，递归算法
    //返回插入新节点后的BST的根
    private Node add(Node node, E e){
        //递归终止条件
//        if (e.equals(node.e))return;
//        else if (e.compareTo(node.e)<0 && node.left == null){
//            node.left = new Node(e);
//            size++;
//            return;
//        }
//        else if (e.compareTo(node.e)>0 && node.right == null){//e比node.e大
//            node.right = new Node(e);
//            size++;
//            return;
//        }
        if (node == null){
            size++;
            return new Node(e);
        }

        //进行递归调用
        if (e.compareTo(node.e)<0) node.left = add(node.left, e);
        else if (e.compareTo(node.e)>0) node.right = add(node.right, e);

        return node;//插入了以后的node还是得返回，重新串一遍
    }

    //查询元素
    public boolean contains(E e){
        return contains(root, e);
    }
    //以node为根的子树是否包含e，递归算法
    private boolean contains(Node node, E e){
        if (node == null)return false;
        if (e.compareTo(node.e) == 0) return true;

        else if (e.compareTo(node.e) < 0) return contains(node.left, e);
        else return contains(node.right, e);
    }

    //先序遍历
    public void preOrder(){
        preOrder(root);
    }

    //先序遍历node为根的树
    private void preOrder(Node node){
//        if (node != null){//隐式的终止条件
//            System.out.println(node.e);
//            preOrder(node.left);
//            preOrder(node.right);
//        }
        if (node == null)return;
        System.out.println(node.e);//先输出本节点
        preOrder(node.left);//然后遍历左子树
        preOrder(node.right);//最后遍历右子树
    }

    //非递归前序遍历，不常用，通过模拟系统的栈，自己控制栈
    public void preOrderNR(){
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.e);

            //压栈
            if (cur.right != null)stack.push(cur.right);//顺序别写错了，因为先pop栈顶
            if (cur.left != null)stack.push(cur.left);
        }
    }


    //中序遍历
    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null)return;

        inOrder(node.left);
        System.out.println(node.e);
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
        System.out.println(node.e);
    }


    //层序遍历,广度优先遍历可以更快找到问题的解（算法设计中的最短路径）
    public void levelOrder(){
        Queue<Node> queue = new LinkedList<>();//queue是一个接口，要选择底层数据结构
        ((LinkedList<Node>) queue).add(root);
        while (!queue.isEmpty()){
            Node cur = queue.remove();
            System.out.println(cur.e);
            if (cur.left != null) ((LinkedList<Node>) queue).add(cur.left);
            if (cur.right != null)((LinkedList<Node>) queue).add(cur.right);
        }
    }

    //删除任意一个节点
    //删除的四种情况：①只有右孩子②只有左孩子③左右孩子都有④左右孩子都无（可以理解为只有左或者只有右）
    public void remove(E e){
        remove(root, e);
    }

    private Node remove(Node node, E e) {
        if (node == null)return null;//没找到
        if (e.compareTo(node.e) < 0 ){
            node.left = remove(node.left, e);
            return node;
        }else if (e.compareTo(node.e) > 0){
            node.right = remove(node.right, e);
            return node;
        }else {
            //找到要删除的元素了
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }else if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }else {
                //hibbert deletion
                //①找到比待删除元素大的节点，即右子树最小的节点
                //②断开其与其父节点的关系（即删除这个节点）
                //③将待删除的节点的左子树和右子树连接上去
                //④断开待删除节点与原来树的联系

                //size--不需要，因为removeMin做了
                Node successor = minimum(node.right);//①后继节点
                successor.right = removeMin(node.right);//②删除这个节点，将删除后的子树作为其右子树。
                //③
                successor.left = node.left;
                //④
                node.left = node.right = null;

                return successor;
            }


        }
    }


    //寻找最小值
    public E minimum(){
        if (size == 0) throw new IllegalArgumentException("BST is empty!");

        return minimum(root).e;
    }
    //返回node为根的最小值所在的节点
    private Node minimum(Node node) {
        if (node.left == null)return node;
        return minimum(node.left);//其实就类似于访问一个链表，自己写非递归算法。
    }

    //寻找最大值
    public E maximum(){
        if (size == 0) throw new IllegalArgumentException("BST is empty!");

        return maximum(root).e;
    }
    //返回node为根的最大值所在的节点
    private Node maximum(Node node) {
        if (node.right == null)return node;
        return maximum(node.right);//其实就类似于访问一个链表，自己写非递归算法。
    }

    //删除最小值所在节点，返回最小值
    public E removeMin(){
        E ret = minimum();//返回
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
    public E removeMax(){
        E ret = maximum();//返回
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
        res.append(generateDepthSting(depth) + node.e + "\n");

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
