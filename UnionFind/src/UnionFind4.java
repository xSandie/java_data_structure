public class UnionFind4 implements UF{
    private int[] parent;//其实是个非常奇怪的树
    private int[] rank;//rank[i]以i为根的集合所表示树的深度
    public UnionFind4(int size){
        parent = new int[size];
        rank = new int[size];
        for (int i=0; i<size;i++){
            parent[i]=i;
            rank[i] = 1;
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
            p = parent[p];//根节点自己等于自己
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
        //根据两个元素所在树的rank判断合并方向
        //将rank低的集合合并到rank高的集合上，减小总的高度
        if (rank[pRoot]<rank[qRoot]){
            parent[pRoot] = qRoot;
        }else if (rank[qRoot]<rank[pRoot]){
            parent[qRoot] = pRoot;
        }else{
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }
    }
}
