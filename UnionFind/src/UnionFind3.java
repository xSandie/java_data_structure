public class UnionFind3 implements UF {
    private int[] parent;//其实是个非常奇怪的树
    private int[] sz;//s[i]以i为根的集合中的元素的个数
    public UnionFind3(int size){
        parent = new int[size];
        sz = new int[size];
        for (int i=0; i<size;i++){
            parent[i]=i;
            sz[i] = 1;
        }
    }
    @Override
    public int getSize(){
        return parent.length;
    }
    //查找p元素所对应的集和的编号
    //O(h)复杂度，h为树的高度
    private int find(int p){
        if (p<0 || p>= parent.length)throw new IllegalArgumentException("p is out of bound.");

        while (p != parent[p])
            p = parent[p];
        return p;
    }

    @Override
    public boolean isConnected(int p, int q){
        return find(p)==find(q);
    }

    @Override
    public void unionElements(int p, int q){
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot){
            return;
        }
        if (sz[pRoot]<sz[qRoot]){
            parent[pRoot] = qRoot;
            sz[qRoot]+=sz[pRoot];
        }else{
            parent[qRoot] = pRoot;
            sz[pRoot]+=sz[qRoot];
        }
    }
}
