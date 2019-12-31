package hash_tables;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map.Entry;

/**
 * HashTableSeparateChain
 */
public class HashTableSeparateChain<K, V> {
    static int DEFAULT_CAPACITY = 3;
    static double DEFAULT_LOAD_FACTOR = 0.75;

    private double maxLoadFactor;
    private int capacity = DEFAULT_CAPACITY;
    private int threshold = (int) (this.capacity * maxLoadFactor);
    private int size = 0;
    private LinkedList<Entry<K, V>>[] table = new LinkedList[this.capacity];

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int normalizeIndex(final int keyHash) {
        return (keyHash & 0x7FFFFFFF) % capacity;
    }

    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }

    public boolean containsKey(final K key) {
        return hashKey(key);
    }

    public boolean hashKey(final K key) {
        final int bucketIndex = normalizeIndex(key.hashCode());
        return bucketSeekEntry(bucketIndex, key) != null;
    }

    public V put(final K key, final V value) {
        if (key == null) {
            throw new IllegalArgumentException("key");
        }

        final Entry<K, V> newEntry = new AbstractMap.SimpleEntry<K, V>(key, value);
        final int bucketIndex = normalizeIndex(key.hashCode());
        return bucketInsertEntry(bucketIndex, newEntry);
    }

    public V get(final K key) {
        if (key == null)
            return null;

        final int bucketIndex = normalizeIndex(key.hashCode());
        final Entry<K, V> entry = bucketSeekEntry(bucketIndex, key);

        if (entry != null)
            return entry.getValue();

        return null;
    }

    public V remove(final K key) {
        if (key == null)
            return null;

        final int bucketIndex = normalizeIndex(key.hashCode());
        final Entry<K, V> entry = bucketRemoveEntry(bucketIndex, key);

        if (entry != null)
            return entry.getValue();

        return null;
    }

    private Entry<K, V> bucketRemoveEntry(final int bucketIndex, final K key) {
        final Entry<K, V> entry = bucketSeekEntry(bucketIndex, key);

        if (entry != null) {
            final LinkedList<Entry<K, V>> links = table[bucketIndex];
            links.remove(entry);
            --size;
            return entry;
        } else {
            return null;
        }
    }

    private V bucketInsertEntry(final int bucketIndex, final Entry<K, V> entry) {
        LinkedList<Entry<K, V>> bucket = table[bucketIndex];
        if (bucket == null) {
            table[bucketIndex] = bucket = new LinkedList<>();
        }

        final Entry<K, V> existentEntry = bucketSeekEntry(bucketIndex, entry.getKey());
        if (existentEntry == null) {
            bucket.add(entry);
            if (++size > threshold)
                resizeTable();

            return null;
        } else {
            final V oldValue = existentEntry.getValue();
            existentEntry.setValue(entry.getValue());
            return oldValue;
        }
    }

    private Entry<K, V> bucketSeekEntry(final int bucketIndex, final K key) {
        if (key == null)
            return null;

        final LinkedList<Entry<K, V>> bucket = table[bucketIndex];
        if (bucket == null)
            return null;
        for (final Entry<K, V> entry : bucket) {
            if (entry.getKey() == key) {
                return entry;
            }
        }

        return null;
    }

    private void resizeTable() {
        capacity *= 2;
        threshold = (int) (capacity * maxLoadFactor);

        LinkedList<Entry<K, V>>[] newTable = new LinkedList[capacity];

        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                for (Entry<K, V> entry : table[i]) {
                    int bucketIndex = normalizeIndex(entry.getKey().hashCode());
                    LinkedList<Entry<K, V>> bucket = newTable[bucketIndex];
                    if (bucket == null) {
                        newTable[bucketIndex] = bucket = new LinkedList<>();
                        bucket.add(entry);
                    }
                }
                table[i].clear();
                table[i] = null;
            }
        }

        table = newTable;
    }
}
