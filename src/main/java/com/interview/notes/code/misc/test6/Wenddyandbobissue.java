package com.interview.notes.code.misc.test6;

public class Wenddyandbobissue {
    /*
     * Complete the 'gameWinner' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING colors as parameter.
     */
    static boolean turn = false;

    public static int findAdjacentIndexwhichCanBeRemoved(String a, char gameChangerName) {
        char[] array = a.toCharArray();
        int sameCount = 0;
        for (int i = 0; i < array.length - 1; i++) {

            if ((array[i] == 'w' || array[i] == 'b') && (array[i + 1] == 'w' || array[i + 1] == 'b')) {
                // incase we have any other color
                return -2;
            }
            if (array[i] == array[i + 1] && array[i] == gameChangerName) {
                sameCount++;
            } else {
                sameCount = 0;
            }
            if (sameCount == 2) {
                return i;
            }
        }
        return -1;
    }

    public static String gameWinner(String colors) {
        // Write your code here

        turn = !turn;
        char name = turn ? 'w' : 'b';

        int indexToBeRemoved = findAdjacentIndexwhichCanBeRemoved(colors, name);
        if (indexToBeRemoved == -2) {
            return "";
        }
        if (indexToBeRemoved == -1) {
            return name != 'w' ? "wendy" : "Bob";
        } else {
            StringBuilder sb = new StringBuilder(colors);
            sb.deleteCharAt(indexToBeRemoved);
            colors = sb.toString();
            return gameWinner(colors);
        }
    }

    public static void main(String[] args) {
        Wenddyandbobissue l = new Wenddyandbobissue();
        String s = l.findWinner("wwwbb");

        System.out.println(s);
    }

    public String findWinner(String s) {
        int wendy_moves = 0, bob_moves = 0;
        int n = s.length();
        int i = 0;
        while (i < n) {
            int j = i, c = 0;
            while (j < n && s.charAt(i) == s.charAt(j)) {
                c++;
                j++;
            }
            if (c > 2) {
                if (s.charAt(i) == 'w') wendy_moves += c - 2;
                else bob_moves += c - 2;
            }
            i = j;
        }
        if (bob_moves >= wendy_moves) return "bob";
        else return "wendy";
    }
}
