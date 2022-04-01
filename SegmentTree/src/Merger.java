/*
    融合器：
        帮助用户使用线段树

    类似于Java底层实现的Comparator
 */

public interface Merger<E> {
    E merge(E a, E b);
}
