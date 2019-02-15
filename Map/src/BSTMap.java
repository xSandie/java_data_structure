public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {
    private class Node{
        public K key;
        public V value;
        public Node left, right;

        public Node(K key, V value, Node left, Node right){
            this.key = key;
            this.value = value;
            this.left= left;
            this.right = right;
        }

        public Node(K key, V value){
            this(key, value, null, null);
        }

        public Node(){
            this(null, null, null, null);
        }

        @Override
        public String toString(){
            return key.toString() + " : " + value.toString();
        }
    }

    private int size;
    private Node root;

    public BSTMap(){
        root = null;
        size = 0;
    }

    @Override
    public int getSize(){
        return size;
    }

    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public void add(K key, V value){
        add(key, value ,root);
    }

    private Node add(K key, V value, Node node) {
        if (node == null){
            size ++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0){
            node.left = add(key, value, node.left);
        }else if (key.compareTo(node.key) > 0){
            node.right = add(key, value ,node.right);
        }else {//相等替代
            node.value = value;
        }
        return node;
    }

    @Override
    public V get(K key){
        Node node = getNode(root, key);
        return node != null? node.value:null;
    }
    @Override
    public boolean contains(K key){
        return getNode(root, key) != null;
    }

    private Node getNode(Node node, K key) {
        if (node == null)return null;

        if (key.compareTo(node.key) == 0){
            //找到
            return node;
        }else if (key.compareTo(node.key) > 0){
            return getNode(node.right, key);
        }else {
            return getNode(node.left, key);
        }
    }

    @Override
    public void set(K key, V newValue){
        Node node = getNode(root, key);
        if (node != null){
            node.value = newValue;
        }else {
            throw new IllegalArgumentException(key + "doesn't exist");
        }
    }

    @Override
    public V remove(K key){
        Node node = getNode(root, key);
        if (node != null){
            root = remove(root ,key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key) {
        if (node == null)return null;
        if (key.compareTo(node.key) > 0){
            node.right = remove(node.right, key);
            return node;
        }else if (key.compareTo(node.key) < 0){
            node.left = remove(node.left, key);
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


    //返回node为根的最小值所在的节点
    private Node minimum(Node node) {
        if (node.left == null)return node;
        return minimum(node.left);//其实就类似于访问一个链表，自己写非递归算法。
    }

    //返回node为根的最大值所在的节点
    private Node maximum(Node node) {
        if (node.right == null)return node;
        return maximum(node.right);//其实就类似于访问一个链表，自己写非递归算法。
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


}
