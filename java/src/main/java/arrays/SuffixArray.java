package arrays;

public class SuffixArray {

    int alphabetSize = 256;
    int[] sa2, rank, tmp, c;

    protected final int N;

    protected int[] T;

    protected int[] sa;

    protected int[] lcp;

    private boolean constructedSa = false;
    private boolean constructedLcpArray = false;

    public SuffixArray(String text) {
        if (text == null)
            throw new IllegalArgumentException("Text cannot be null.");

        int[] textArray = toIntArray(text);
        this.T = textArray;
        this.N = textArray.length;
    }

    public int getTextLength() {
        return T.length;
    }

    // Returns the suffix array.
    public int[] getSa() {
        buildSuffixArray();
        return sa;
    }

    // Returns the LCP array.
    public int[] getLcpArray() {
        buildLcpArray();
        return lcp;
    }

    // Builds the suffix array by calling the construct() method.
    protected void buildSuffixArray() {
        if (constructedSa)
            return;
        construct();
        constructedSa = true;
    }

    // Builds the LCP array by first creating the SA and then running the kasai
    // algorithm.
    protected void buildLcpArray() {
        if (constructedLcpArray)
            return;
        buildSuffixArray();
        kasai();
        constructedLcpArray = true;
    }

    protected static int[] toIntArray(String s) {
        if (s == null)
            return null;
        int[] t = new int[s.length()];
        for (int i = 0; i < s.length(); i++)
            t[i] = s.charAt(i);
        return t;
    }

    private void kasai() {
        lcp = new int[N];
        int[] inv = new int[N];
        for (int i = 0; i < N; i++)
            inv[sa[i]] = i;
        for (int i = 0, len = 0; i < N; i++) {
            if (inv[i] > 0) {
                int k = sa[inv[i] - 1];
                while ((i + len < N) && (k + len < N) && T[i + len] == T[k + len])
                    len++;
                lcp[inv[i]] = len;
                if (len > 0)
                    len--;
            }
        }
    }

    protected void construct() {
        sa = new int[N];
        sa2 = new int[N];
        rank = new int[N];
        c = new int[Math.max(alphabetSize, N)];

        int i, p, r;
        for (i = 0; i < N; ++i) {
            c[rank[i] = T[i]]++;
        }

        for (i = 1; i < alphabetSize; ++i) {
            c[i] += c[i - 1];
        }

        for (i = N - 1; i >= 0; --i) {
            sa[--c[T[i]]] = i;
        }

        for (p = 1; p < N; p <<= 1) {
            for (r = 0, i = N - p; i < N; ++i) {
                sa2[r++] = i;
            }

            for (i = 0; i < N; ++i) {
                if (sa[i] >= p)
                    sa2[r++] = sa[i] - p;
            }

            java.util.Arrays.fill(c, 0, alphabetSize, 0);

            for (i = 0; i < N; ++i) {
                c[rank[i]]++;
            }

            for (i = 1; i < alphabetSize; ++i) {
                c[i] += c[i - 1];
            }

            for (i = N - 1; i >= 0; --i) {
                sa[--c[rank[sa2[i]]]] = sa2[i];
            }

            for (sa2[sa[0]] = r = 0, i = 1; i < N; ++i) {
                if (!(rank[sa[i - 1]] == rank[sa[i]] && sa[i - 1] + p < N && sa[i] + p < N
                        && rank[sa[i - 1] + p] == rank[sa[i] + p]))
                    r++;
                sa2[sa[i]] = r;
            }

            tmp = rank;
            rank = sa2;
            sa2 = tmp;

            if (r == N - 1)
                break;

            alphabetSize = r + 1;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("-----i-----SA-----LCP---Suffix\n");

        for (int i = 0; i < N; i++) {
            int suffixLen = N - sa[i];
            char[] suffixArray = new char[suffixLen];
            for (int j = sa[i], k = 0; j < N; j++, k++)
                suffixArray[k] = (char) T[j];
            String suffix = new String(suffixArray);
            String formattedStr = String.format("% 6d % 6d % 6d\t%s\n", i, sa[i], lcp[i], suffix);
            sb.append(formattedStr);
        }
        return sb.toString();
    }
}