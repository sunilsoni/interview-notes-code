package com.interview.notes.code.misc.test9;

import java.util.*;
//https://github.com/Seanforfun/Algorithm-and-Leetcode/blob/master/leetcode/140.%20Word%20Break%20II.md

//Missing Spaces Question: You are given a sentence with no spaces and dictionary containing thousands of words. Write an algorithm to reconstruct the sentence by inserting spaces in the appropriate positions.

class MissingSpaces {
    public static void main(String[] args) {
        String s = "pineapplepenapple";
        List<String> wordDict = Arrays.asList("apple", "pen", "applepen", "pine", "pineapple");

        System.out.println(wordBreak(s,wordDict));
    }
    public static List<String> wordBreak(String s, List<String> wordDict) {
        List<String>[] dp = new List[s.length() + 1];
        int len = s.length();
        dp[0] = new ArrayList<>();
        if(len == 0) return dp[0];
        Set<Integer> set = new HashSet<>();
        for(String ss : wordDict) {
            set.add(ss.length());
        }
        for(int i = 1; i <= len; i++){
            dp[i] = new ArrayList<>();
            for(Integer in : set){
                if(i - in >= 0){
                    String sub = s.substring(i - in, i);
                    if(wordDict.contains(sub)){
                        List<String> tmp = dp[i - in];
                        if(i - in == 0)
                            dp[i].add(sub);
                        else{
                            for(String ss : tmp){
                                dp[i].add(ss + " " + sub);
                            }
                        }
                    }
                }
            }
        }
        return dp[len];
    }
}