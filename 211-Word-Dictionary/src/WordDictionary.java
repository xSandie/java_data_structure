import java.util.TreeMap;
public class WordDictionary {
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
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new Node();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        Node cur = root;
        for(int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if(cur.next.get(c)==null)cur.next.put(c,new Node());//不存在，添加进去
            cur = cur.next.get(c);
        }
        if(!cur.isWord){
            cur.isWord = true;//到字符串末尾了
        }
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return match(root, word, 0);
    }
    private boolean match(Node node, String word, int index){
        if (index==word.length())return node.isWord;
        char c = word.charAt(index);
        if (c!='.'){
            if (node.next.get(c)==null){
                return false;
            }
            return match(node.next.get(c), word, index+1);
        }else{//.
            for(char nextChar:node.next.keySet()){//遍历每种可能,其实复杂度太高了
                if (match(node.next.get(nextChar), word, index+1))
                    return true;
            }
            return false;
        }
    }
}
