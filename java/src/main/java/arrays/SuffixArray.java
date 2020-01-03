package arrays;

import java.util.Arrays;

public class SuffixArray {
    final int size;

    String text;
    int[] suffixArray;

    int[] lcp;

    private boolean constructedSa = false;
    private boolean constructedLcpArray = false;
    Object[][] sa;

    public SuffixArray(String text) {
        if (text == null)
            throw new IllegalArgumentException("Text cannot be null.");

        this.text = text;
        size = text.length();

        sa = new Object[size][2];

        suffixArray = new int[size];
        lcp = new int[size];
    }

    public int[] getSuffixArray() {
        buildSuffixArray();
        return suffixArray;
    }

    public int[] getLcpArray() {
        buildLcpArray();
        return lcp;
    }

    void buildSuffixArray() {
        if (constructedSa)
            return;

        constructSimple();

        constructedSa = true;
    }

    private void constructSimple() {
        String prev = "";
        for (int i = size - 1; i >= 0; i--) {
            String temp = prev;
            sa[i][0] = i;
            sa[i][1] = prev = String.format("%s%s", text.charAt(i), temp);
        }

        Arrays.sort(sa, (a, b) -> a[1].toString().compareTo(b[1].toString()));

        for (int i = 0; i < sa.length; i++) {
            suffixArray[i] = (int) sa[i][0];
        }
    }

    void buildLcpArray() {
        if (constructedLcpArray)
            return;

        buildSuffixArray();
        kasai();

        constructedLcpArray = true;
    }

    private void kasai() {
        int[] textArray = text.chars().toArray();
        int[] invertedIndexArray = new int[size];

        String res = "";
        for (int i = 0; i < size; i++) {
            res += suffixArray[i] + "=" + i + "=" + sa[i][1].toString() + "    ";
            invertedIndexArray[suffixArray[i]] = i;
        }
        System.out.println(res);

        res = "";
        for (int i = 0, len = 0; i < size; i++) {
            if (invertedIndexArray[i] > 0) {
                int k = suffixArray[invertedIndexArray[i] - 1];

                while ((i + len < size) && (k + len < size) && textArray[i + len] == textArray[k + len])
                    len++;
                    
                System.out.println(sa[invertedIndexArray[i] - 1][1].toString() + "    "
                        + sa[invertedIndexArray[i]][1].toString() + "=" + len);
                lcp[invertedIndexArray[i]] = len;

                if (len > 0)
                    len--;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("-----i-----SA-----LCP---Suffix\n");
        for (int i = 0; i < size; i++) {
            sb.append(String.format("% 6d % 6d % 6d\t%s\n", i, suffixArray[i], lcp[i], text.substring(suffixArray[i])));
        }
        return sb.toString();
    }
    // i--SA--LCP--Suffix
    // 0--9---0----A
    // 1--8---1----AA
    // 2--5---2----AABAA
    // 3--6---1----ABAA
    // 4--3---4----ABAABAA
    // 5--0---2----ABBABAABAA
    // 6--7---0----BAA
    // 7--4---3----BAABAA
    // 8--2---2----BABAABAA
    // 9--1---1----BBABAABAA
}