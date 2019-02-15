public interface Map<K, V> {

    void add(K key, V value);
    V remove(K key);
    V get(K key);
    void set(K key, V newValue);
    int getSize();
    boolean isEmpty();
    boolean contains(K key);
}
