public class UnionFind2 implements UF {
    private int[] parent;//其实是个非常奇怪的树
    public UnionFind2(int size){
        parent = new int[size];
        for (int i=0; i<size;i++){
            parent[i]=i;
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
        parent[pRoot] = qRoot;
    }
}
