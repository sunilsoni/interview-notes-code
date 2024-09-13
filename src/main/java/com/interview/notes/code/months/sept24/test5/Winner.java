package com.interview.notes.code.months.sept24.test5;

import java.util.*;

/*

Find the Winner!
Description
Andrea and Maria each have a deck of numbered cards in a pile face down. They play a game where they each alternately discard and flip the cards on the pile from top to bottom.
At the beginning of the game, someone will call out "Even" or "Odd"
". The first move
depends on which is called. If "Even" is called, the player's top cards are flipped so they can see the face value. If instead "Odd" is called, the top card is removed from each deck and discarded, then each flips her next card. Andrea subtracts the value of Maria's card from her own and adds the result to her score. Likewise, Maria subtracts the value of Andrea's card from her own and adds the result to her score.
From this point forward, each alternately discards then flips a card. Each time two cards are flipped, the players' scores are computed as before. Once all the cards have been played, whoever has the most points wins.
As an example, Maria's cards, face down, are [3, 5, 6] and Andrea's are [4, 5, 71. After calling "Even" at random, the game begins. The following table represents game play with cumulative score at the bottom. Maria's score is -2, Andrea's is +2 so Andrea wins.

Maria's Andrea's
Maria's
Andrea's
Card
Card
Score
Score
3
4
3 - 4 = -1
4 - 3 = 1
5
5
Discard
Discard
6
7
6 - 7 =-1
7 - 6 =1
Cumulative scores
-2
2

Determine the name of the winner if there is one, otherwise they tie. Return the string Andrea, Maria or Tie.
Function Description
Complete the function winner in the editor below.
winner has the following parameters):
int andrean]: Andrea's array of card values. int maria[n]: Maria's array of card values. strings: the starting called out word
Return
string: either 'Maria', 'Andrea' or 'Tie'
Constraints
• 2≤n≤ 105
• 1 ≤ alil, mlil ≤ 103, where 0 ≤ i < n
• Strings will be either the word 'Odd' or 'Even'.


• Input Format For Custom Testing
Input from stdin will be processed as follows and passed to the function:
The first line contains an integer n, denoting the number of elements in andrea.
The next n lines each contain an integer describing a, where O si < n.
The next line contains an integer, n, denoting the number of elements in maria.
The next n lines each contain an integer describing m; where O si<n.
The next line contains string s, Even or Odd.
• Sample Case 0
Sample Input 0
Function
andreall size n = 3
→
andrea = 11, 2, 31
STDIN
3
1
2332
1
3
Even
→
→
mariall size n = 3
maria = 12, 1, 3]
s = 'Even'
Sample Output 0
Maria
Explanation 0
The indices range from O through 2. Since s = 'Even', the only cards flipped are at
indices 0 and 2


At the end of play, Andrea's cumulative score is -1 and Maria's is 1 so Maria wins.
• Sample Case 1
Sample Input 1
STDIN
Function
3
1
2
3
3
2
1
3
Odd
friend_ 1[] size n = 3
→ friend_ 1 = [1, 2, 31
→ friend_2[] size n = 3
→ friend_2 = 12, 1, 31
s= 'Odd'
Sample Output 1
Andrea
Explanation 1
Since s = 'Odd', the only index flipped is at index 1.
• When i = 1, Andrea gets andrea 1] - marial 1] = 2 - 1 = 1 point and Maria gets marial 1] -
andrea[1] = 1 - 2 = - 1 point.
Andrea ends with 1 point, and Maria with -1. Andrea wins.


 */
public class Winner {
    
    public static String winner(List<Integer> andrea, List<Integer> maria, String s) {
        int andreaScore = 0;
        int mariaScore = 0;
        int n = andrea.size();
        
        // Determine starting index based on the called word
        int startIndex = s.equals("Even") ? 0 : 1;
        
        for (int i = startIndex; i < n; i += 2) {
            int andreaCard = andrea.get(i);
            int mariaCard = maria.get(i);
            
            andreaScore += andreaCard - mariaCard;
            mariaScore += mariaCard - andreaCard;
        }
        
        if (andreaScore > mariaScore) {
            return "Andrea";
        } else if (mariaScore > andreaScore) {
            return "Maria";
        } else {
            return "Tie";
        }
    }
    
    public static void main(String[] args) {
        // Test Case 1: Sample Case 0
        List<Integer> andrea1 = Arrays.asList(1, 2, 3);
        List<Integer> maria1 = Arrays.asList(2, 1, 3);
        String s1 = "Even";
        System.out.println("Test Case 1: " + (winner(andrea1, maria1, s1).equals("Maria") ? "Passed" : "Failed"));
        
        // Test Case 2: Sample Case 1
        List<Integer> andrea2 = Arrays.asList(1, 2, 3);
        List<Integer> maria2 = Arrays.asList(2, 1, 3);
        String s2 = "Odd";
        System.out.println("Test Case 2: " + (winner(andrea2, maria2, s2).equals("Andrea") ? "Passed" : "Failed"));
        
        // Test Case 3: Edge case - Empty lists
        List<Integer> andrea3 = new ArrayList<>();
        List<Integer> maria3 = new ArrayList<>();
        String s3 = "Even";
        System.out.println("Test Case 3: " + (winner(andrea3, maria3, s3).equals("Tie") ? "Passed" : "Failed"));
        
        // Test Case 4: Large input
        List<Integer> andrea4 = new ArrayList<>();
        List<Integer> maria4 = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            andrea4.add(i);
            maria4.add(i);
        }
        String s4 = "Odd";
        long startTime = System.currentTimeMillis();
        String result = winner(andrea4, maria4, s4);
        long endTime = System.currentTimeMillis();
        System.out.println("Test Case 4: " + (result.equals("Tie") && (endTime - startTime) < 3000 ? "Passed" : "Failed"));
        
        // Test Case 5: Edge case - Tie with different card values
        List<Integer> andrea5 = Arrays.asList(1, 5, 3);
        List<Integer> maria5 = Arrays.asList(2, 4, 3);
        String s5 = "Even";
        System.out.println("Test Case 5: " + (winner(andrea5, maria5, s5).equals("Tie") ? "Passed" : "Failed"));
    }
}
