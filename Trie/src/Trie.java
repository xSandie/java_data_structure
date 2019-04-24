import java.util.TreeMap;
public class Trie {
    //数据量越大性能优势越明显
    //本课不采用泛型
    private class Node{
        public boolean isWord;
        public TreeMap<Character,Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }
        public Node(){
            this(false);
        }
    }

    private  Node root;
    private int size;

    public Trie(){
        root = new Node();
        size = 0;
    }

    public int getSize(){
        return size;
    }
    //向字典树中添加一个单词
    public void add(String word){
        Node cur = root;
        for(int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if(cur.next.get(c)==null)cur.next.put(c,new Node());//不存在，添加进去
            cur = cur.next.get(c);
        }
        if(!cur.isWord){
            cur.isWord = true;//到字符串末尾了
            size++;
        }
    }
    //查询一个单词是否存在
    public boolean contains(String word){
        Node cur = root;
        for(int i=0;i<word.length();i++){
            char c= word.charAt(i);
            if (cur.next.get(c)==null){
                return false;
            }
            cur= cur.next.get(c);
        }
        return cur.isWord;
    }
    //查找是否有前缀
    public boolean isPrefix(String prefix){
        Node cur= root;
        for(int i=0;i<prefix.length();i++){
            char c = prefix.charAt(i);
            if(cur.next.get(c)==null){
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }

}
