package arrays;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * SuffixArrayTests
 */
public class SuffixArrayTests {

    @Test
    public void shouldTestSUffixArray() {
        String text = "ABBABAABAA";
        int[] lcpValues = { 0, 1, 2, 1, 4, 2, 0, 3, 2, 1 };

        SuffixArray sa = new SuffixArray(text);

        int length = sa.getSuffixArray().length;
        int[] lcpArray = sa.getLcpArray();

        for (int i = 0; i < length; i++) {
            assertEquals(lcpValues[i], lcpArray[i]);
        }

        System.out.println(sa);
    }
}

