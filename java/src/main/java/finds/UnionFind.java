
package finds;

/**
 * UnionFind
 */
public class UnionFind {
    private int numberOfComponents;
    private int[] componentSizes;
    private int[] components;

    public UnionFind(int size) {
        if (size <= 0)
            throw new IllegalArgumentException("Size <= 0 is not allowed");

        numberOfComponents = size;
        componentSizes = new int[size];
        components = new int[size];

        for (int i = 0; i < size; i++) {
            components[i] = i;
            componentSizes[i] = 1;
        }
    }

    public int findAndMapRoot(int p) {
        int root = p;
        while (root != components[root])
            root = components[root];

        while (p != root) {
            int next = components[p];
            components[p] = root;
            p = next;
        }

        return root;
    }

    public boolean connected(int p, int q) {
        return findAndMapRoot(p) == findAndMapRoot(q);
    }

    public int componentSize(int p) {
        return componentSizes[findAndMapRoot(p)];
    }

    public int components() {
        return numberOfComponents;
    }

    public void unify(int p, int q) {

        int root1 = findAndMapRoot(p);
        int root2 = findAndMapRoot(q);

        if (root1 == root2)
            return;

        if (componentSizes[root1] < componentSizes[root2]) {
            componentSizes[root2] += componentSizes[root1];
            components[root1] = root2;
        } else {
            componentSizes[root1] += componentSizes[root2];
            components[root2] = root1;
        }

        numberOfComponents--;
    }
}