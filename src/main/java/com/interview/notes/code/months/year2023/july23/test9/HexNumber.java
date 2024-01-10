package com.interview.notes.code.months.year2023.july23.test9;

class HexNumber {
    private char[] _val;

    HexNumber(char[] val) {
        _val = val;
    }

    public static void main(String[] args) {
        HexNumber operandA = new HexNumber(new char[]{'3', 'f', '5'});
        HexNumber operandB = new HexNumber(new char[]{'0', 'a', 'c'});
        HexNumber sum = operandA.add(operandB);
        System.out.println(sum);
        // 4a1
    }

    public String toString() {
        return new String(_val);
    }

    /**
     * @param operand
     * @return a new HexNumber representing the sum of this instance and the given operand
     */
    HexNumber add1(HexNumber operand) {
        int sum = 0;
        for (int i = _val.length - 1; i >= 0; i--) {
            int a = _val[i] - '0';
            int b = operand._val[i] - '0';
            sum += a + b;
            _val[i] = (char) (sum % 16 + '0');
            sum = sum / 16;
        }
        if (sum > 0) {
            char[] newVal = new char[_val.length + 1];
            newVal[0] = (char) (sum + '0');
            System.arraycopy(_val, 0, newVal, 1, _val.length);
            _val = newVal;
        }
        return this;
    }

    HexNumber add(HexNumber operand) {
        // initialize an array to store the result
        char[] result = new char[_val.length];
        // initialize a carry variable to store the carry over
        int carry = 0;
        // loop through the digits from right to left
        for (int i = _val.length - 1; i >= 0; i--) {
            // convert the current digits to decimal values
            int a = Character.digit(_val[i], 16);
            int b = Character.digit(operand._val[i], 16);
            // add the digits and the carry
            int sum = a + b + carry;
            // update the carry
            carry = sum / 16;
            // convert the sum to a hex digit and store it in the result array
            result[i] = Character.forDigit(sum % 16, 16);
        }
        // return a new HexNumber with the result array
        return new HexNumber(result);
    }
}
