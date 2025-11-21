package com.interview.notes.code.spring;

/*

Given an array of strings, return all groups of strings that are anagrams. Represent a group by a list of integers representing the index in the original list. Look at the sample case for clarification.

Anagram : a word, phrase, or name formed by rearranging the letters of another, such as 'spar', formed from 'rasp'

Note:  All inputs will be in lower-case.

Example :

Input : cat dog god tca
Output : [[1, 4], [2, 3]]
cat and tca are anagrams which correspond to index 1 and 4.

dog and god are another set of anagrams which correspond to index 2 and 3.

The indices are 1 based ( the first element has index 1 instead of index 0).

Ordering of the result : You should not change the relative ordering of the words / phrases within the group. Within a group containing A[i] and A[j], A[i] comes before A[j] if i < j.

Note:You only need to implement the given function. Do not read input, instead use the arguments to the function. Do not print the output, instead return values as specified. Still have a doubt? Check

 */
public class Test {
    public static void main(String[] args) {


        String s1 = "hello";
        String s2 = "hello";
        String s3 = "hello";

        System.out.println(s1 == s3);
        System.out.println(s2 == s1);
        System.out.println(s3);
    }

}

