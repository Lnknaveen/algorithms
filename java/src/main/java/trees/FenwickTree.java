
package trees;

/**
 * FenwickTree
 */
public class FenwickTree {

    private long[] tree;

    public FenwickTree(long[] values) {
        if (values == null) {
            throw new IllegalArgumentException("Values :)");
        }

        this.tree = values.clone();

        for (int i = 0; i < tree.length; i++) {
            int j = i + lsb(i);

            if (j < tree.length)
                tree[j] += tree[i];
        }
    }

    private int lsb(int i) {
        return Integer.lowestOneBit(i);
    }

    public long prefixSum(int i) {
        long sum = 0L;

        while (i != 0) {
            sum += tree[i];
            i &= ~lsb(i);
        }

        return sum;
    }

    public long sum(int i, int j) {
        if (j < i)
            throw new IllegalArgumentException("must be j < i");

        return prefixSum(j) - prefixSum(i - 1);
    }

    public void add(int i, long k) {
        while (i <= tree.length) {
            tree[i] += k;
            i += lsb(i);
        }
    }

    public void set(int i, long k) {
        long value = sum(i, i);
        add(i, k - value);
    }
}