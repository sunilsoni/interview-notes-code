package com.interview.notes.code.tricky;


/**
 * In java
 *
 * You are given three integers in the form of strings: firstnum , secondnum , and thirdnum . Your task is to check whether it is possible to erase at most one digit from firstnum , so that the resulting string contains at least one digit, has no leading zeros and the value of thirdnum is equal to the sum of the values of firstnum and secondnum .
 * Return true if it's possible, otherwise return false . Note: All three strings are provided without leading zeros.
 *
 *
 * Example
 *
 * • For firstnum = "10534" , secondnum = "67" , and thirdnum = "1120" , the output should be solution(firstnum, secondnum, thirdnum) = true . By erasing the 5th digit of firstnum , the result is 1053 , and 1053 + 67 = 1120. So the answer is true . •
 *
 * For firstnum = "10000" , secondnum = "67" , and thirdnum = "1120" , the output should be solution(firstnum, secondnum, thirdnum) = false . The only possible modified values of
 * firstnum would be l0000 (nothing was deleted), 0000 (first digit was deleted), and l000 (any zero was deleted); none of
 * which would produce the required sum, so the answer is false .
 *
 * • For firstnum = "1067" , secondnum = "33" , and thirdnum = "100" , the output should be solution (firstnum, secondnum, thirdnum) = false . We could delete the first digit of firstnum , resulting in 067 (and 67 + 33 = 100 ), but since in this case new firstnum value has a leading zero, it's
 * considered invalid. So the answer is false .
 *
 * • For firstnum = "153" , secondnum = "153" , and thirdnum = "306" , the output should be solution (firstnum, secondnum, thirdnum) = true .
 *
 *
 * Input/Output
 * • [execution time limit] 3 seconds (Java)
 * • [input] string firstnum A string representing an integer. Guaranteed constraints:
 * 2 firstnum.length < 9 .
 * • [input] string secondnum A string representing an integer. Guaranteed constraints:
 * 1 secondnum.length S 9 .
 * • [input] string thirdnum A string representing an integer. Guaranteed constraints:
 * 1 S thirdnum.length S 9 .
 * • [output] boolean Return true if it's possible to erase at most one digit from firstnum such that the value of thirdnum is equal to the sum of the values of firstnum and
 * secondnum . Otherwise return false .
 *
 * Inn Java
 *
 * Given a string of lowercase English letters, find the length of its longest contiguous substring that doesn't contain any character more than once.
 *
 * Example For s = "abcabba" , the output should be solution(s) = 3 .
 *
 * The longest substrings with unique characters are abc , bca , and cab , so the answer should be 3 .
 *
 * Input/Output
 * • [execution time limit] 1 seconds (java) • [input] string s A string containing only lowercase English letters. Guaranteed constraints:
 * 3 < s.length 106. • [output] integer [Java] Syntax Tips
 */
public class Test3 {
    public static void main(String[] args) {

      //  System.out.println(solution1("10534","67" ,"1120"));
       // System.out.println(solution2("10534","67" ,"1120"));
       // System.out.println(solution3("10534","67" ,"1120"));

        System.out.println(solution6("10534","67" ,"1120"));//true
        System.out.println(solution6("1067","33" ,"100"));//true


    }

    public static boolean solution1(String firstnum, String secondnum, String thirdnum) {
        // check if thirdnum is too small or too large
        if (thirdnum.length() < Math.max(firstnum.length(), secondnum.length()) ||
                thirdnum.length() > Math.max(firstnum.length(), secondnum.length()) + 1) {
            return false;
        }

        // try deleting one digit from firstnum and check if the sum equals thirdnum
        for (int i = 0; i < firstnum.length(); i++) {
            StringBuilder sb = new StringBuilder(firstnum);
            sb.deleteCharAt(i);
            String newFirstNum = sb.toString();
            if (newFirstNum.length() > 0 && newFirstNum.charAt(0) != '0') {
                int sum = Integer.parseInt(newFirstNum) + Integer.parseInt(secondnum);
                if (sum == Integer.parseInt(thirdnum)) {
                    return true;
                }
            }
        }

        // try deleting one digit from secondnum and check if the sum equals thirdnum
        for (int i = 0; i < secondnum.length(); i++) {
            StringBuilder sb = new StringBuilder(secondnum);
            sb.deleteCharAt(i);
            String newSecondNum = sb.toString();
            if (newSecondNum.length() > 0 && newSecondNum.charAt(0) != '0') {
                int sum = Integer.parseInt(firstnum) + Integer.parseInt(newSecondNum);
                if (sum == Integer.parseInt(thirdnum)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean solution2(String firstnum, String secondnum, String thirdnum) {
        int len1 = firstnum.length();
        int len2 = secondnum.length();
        int len3 = thirdnum.length();
        if (len1 + len2 != len3) {
            // The length of thirdnum should be equal to the sum of the lengths of firstnum and secondnum
            return false;
        }
        int i = 0, j = 0, k = 0;
        while (k < len3) {
            int digit = 0;
            if (i < len1) {
                digit += firstnum.charAt(i) - '0';
                i++;
            }
            if (j < len2) {
                digit += secondnum.charAt(j) - '0';
                j++;
            }
            if (digit > 9) {
                // If the sum of two digits is greater than 9, we cannot erase a digit from firstnum and get the sum
                return false;
            }
            if (k < len3 - 1 && digit == 0 && (i < len1 || j < len2)) {
                // The resulting string should have no leading zeros
                return false;
            }
            if (k < len3 - 1 && digit != (thirdnum.charAt(k) - '0')) {
                // If the current digit in thirdnum does not match the sum of the digits, we can try deleting one digit from firstnum and try again
                if (i < len1 && solution2(firstnum.substring(0, i-1) + firstnum.substring(i), secondnum, thirdnum.substring(k+1))) {
                    return true;
                }
                if (j < len2 && solution2(firstnum.substring(0, i), secondnum.substring(0, j-1) + secondnum.substring(j), thirdnum.substring(k+1))) {
                    return true;
                }
                return false;
            }
            k++;
        }
        return true;
    }

    public static boolean solution3(String firstnum, String secondnum, String thirdnum) {
        // check if thirdnum is too small or too large
        if (thirdnum.length() < Math.max(firstnum.length(), secondnum.length()) ||
                thirdnum.length() > Math.max(firstnum.length(), secondnum.length()) + 1) {
            return false;
        }

        // try deleting one digit from firstnum and check if the sum equals thirdnum
        for (int i = 0; i < firstnum.length(); i++) {
            if (firstnum.charAt(i) != '0') {
                String newFirstNum = firstnum.substring(0, i) + firstnum.substring(i+1);
                if (isValid(newFirstNum, secondnum, thirdnum)) {
                    return true;
                }
            }
        }

        // try deleting one digit from secondnum and check if the sum equals thirdnum
        for (int i = 0; i < secondnum.length(); i++) {
            if (secondnum.charAt(i) != '0') {
                String newSecondNum = secondnum.substring(0, i) + secondnum.substring(i+1);
                if (isValid(firstnum, newSecondNum, thirdnum)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isValid(String firstnum, String secondnum, String thirdnum) {
        int sum = Integer.parseInt(firstnum) + Integer.parseInt(secondnum);
        return sum == Integer.parseInt(thirdnum);
    }

    public static boolean solution4(String firstnum, String secondnum, String thirdnum) {
        int sum = Integer.parseInt(thirdnum);
        int len1 = firstnum.length(), len2 = secondnum.length();
        for (int i = 0; i < len1; i++) {
            String sub = firstnum.substring(0, i) + firstnum.substring(i + 1); // remove i-th digit
            if (sub.length() == 0 || sub.charAt(0) != '0') { // check for leading zeros
                int num1 = Integer.parseInt(sub);
                int num2 = sum - num1;
                String str2 = String.valueOf(num2);
                if (str2.length() == len2 && (str2.length() == 1 || str2.charAt(0) != '0')) { // check for leading zeros and length
                    if (num1 + num2 == sum) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean solution5(String firstnum, String secondnum, String thirdnum) {
        int sum = Integer.parseInt(thirdnum);
        int len1 = firstnum.length(), len2 = secondnum.length();
        for (int i = 0; i < len1; i++) {
            String sub = firstnum.substring(0, i) + firstnum.substring(i + 1); // remove i-th digit
            if (sub.length() == 0 || sub.charAt(0) != '0') { // check for leading zeros
                int num1 = Integer.parseInt(sub);
                int num2 = sum - num1;
                String str2 = String.valueOf(num2);
                if (str2.length() == len2 && (str2.length() == 1 || str2.charAt(0) != '0')) { // check for leading zeros and length
                    if (num1 + num2 == sum) {
                        return true;
                    }
                }
            }
        }
        for (int i = len1 - 1; i >= 0; i--) {
            String sub = firstnum.substring(0, i) + firstnum.substring(i + 1); // remove i-th digit
            if (sub.length() == 0 || sub.charAt(0) != '0') { // check for leading zeros
                int num1 = Integer.parseInt(sub);
                int num2 = sum - num1;
                String str2 = String.valueOf(num2);
                if (str2.length() == len2 && (str2.length() == 1 || str2.charAt(0) != '0')) { // check for leading zeros and length
                    if (num1 + num2 == sum) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean solution6(String first, String second, String third) {
        int target = Integer.parseInt(third);
        int n = first.length();

        for (int i = 0; i < n; i++) {
            String modified = first.substring(0, i) + first.substring(i + 1);
            if (modified.length() == 0 || modified.charAt(0) == '0') {
                continue; // skip empty string or leading zero
            }
            int a = Integer.parseInt(modified);
            int b = Integer.parseInt(second);
            if (a + b == target) {
                return true;
            }
        }
        // check original strings
        if (!first.equals("0") && first.charAt(0) != '0') {
            int a = Integer.parseInt(first);
            int b = Integer.parseInt(second);
            if (a + b == target) {
                return true;
            }
        }
        return false;
    }

}
