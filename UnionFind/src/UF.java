/*
    并查集
        特殊的树
            孩子指向父亲的树
 */
public interface UF {

    int getSize();
    boolean isConnected(int p,int q);
    void unionElements(int p, int q);
}
