import java.util.TreeMap;

public class MapSum {
    private class Node{
        public int value;
        public TreeMap<Character,Node> next;

        public Node(int value){
            this.value = value;
            next = new TreeMap<>();
        }
        public Node(){
            this(0);
        }
    }

    private Node root;
    public MapSum() {
        root = new Node();
    }

    public void insert(String key, int val) {
        Node cur = root;
        for(int i=0;i<key.length();i++){
            char c = key.charAt(i);
            if(cur.next.get(c)==null)cur.next.put(c,new Node());//不存在，添加进去
            cur = cur.next.get(c);
        }
        cur.value = val;
    }

    public int sum(String prefix) {
        Node cur = root;
        for (int i=0;i<prefix.length();i++){
            char c=prefix.charAt(i);
            if (cur.next.get(c)==null){
                return 0;
            }
            cur = cur.next.get(c);
        }
        return sum(cur);
    }
    private int sum(Node node){
        int res = node.value;//其实考虑了递归到底的情况
        for(char c:node.next.keySet()){
            res+=sum(node.next.get(c));
        }
        return res;
    }
}
