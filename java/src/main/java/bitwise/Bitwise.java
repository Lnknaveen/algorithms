package bitwise;

import java.io.PrintStream;

/**
 * Bitwise
 */
public class Bitwise {
    public static void main(String[] args) {
        PrintStream out = System.out;

        StringBuilder sbBits = new StringBuilder();
        StringBuilder sbValues = new StringBuilder();
        for (int i = 8; i >= 0; i--) {
            sbBits.append(String.format("2^%s\t", i));
            sbValues.append(String.format("%s\t", (int) Math.pow(2, i)));
        }
        out.println(sbBits);
        out.println(sbValues);

        out.println();
        out.println("2 = 0000 0010");
        out.println();

        // Binary AND Operator copies a bit to the result if it exists in both operands.
        out.println(String.format("%s & %s = %s", toBin(2), toBin(1), toBin(2 & 1)));

        // Binary OR Operator copies a bit if it exists in either operand.
        out.println(String.format("%s | %s = %s", toBin(2), toBin(1), toBin(2 | 1)));

        // Binary XOR Operator copies the bit if it is set in one operand but not both.
        out.println(String.format("%s ^ %s = %s", toBin(2), toBin(1), toBin(2 ^ 1)));

        // Binary Ones Complement Operator is unary and has the effect of 'flipping'
        // bits.
        out.println(String.format("~%s = %s", toBin(1), ~1));
        out.println(String.format("~%s = %s", toBin(-1), ~1));

        // Binary Left Shift Operator. The left operands value is moved left by the
        // number of bits specified by the right operand.
        out.println(String.format("%s << 1 = %s", toBin(4), toBin(4 << 1)));

        // Binary Right Shift Operator. The left operands value is moved right by the
        // number of bits specified by the right operand.
        out.println(String.format("%s >> 1 = %s", toBin(4), toBin(4 >> 1)));

        // Shift right zero fill operator. The left operands value is moved right by the
        // number of bits specified by the right operand and shifted values are filled
        // up with zeros.
        out.println(String.format("%s >>> 1 = %s", toBin(4), toBin(4 >>> 1)));
    }

    private static String toBin(int value) {
        return Integer.toBinaryString(value);
    }
}