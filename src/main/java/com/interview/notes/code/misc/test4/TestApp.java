package com.interview.notes.code.misc.test4;

public class TestApp {

    public static void main(String[] args) {
        String str = "abbbccda";
        int counter = 0;
        int firstOccurence = 0;
        int prevSequenceLength = 0;
//converting string into character array
        char[] vals = str.toCharArray();

        for (int i = 0; i < vals.length; ) {

            for (int j = i + 1; j < vals.length; j++) {
                //if value match then increment counter
                if (vals[i] == str.charAt(j)) {
                    counter++;
                } else {
                    //set the prevSequenceLength to counter value if it is less than counter value
                    if (prevSequenceLength < counter) {
                        prevSequenceLength = counter;
                        //make firstOccurence to  j - counter
                        firstOccurence = j - counter;
                    }

                    //first reset the counter to 0;
                    counter = 0;

                    //else break from inner loop
                    break; //break from inner loop
                }
                //assign the index value of j to the i to start with new substring
                i = vals[j];
            }

            System.out.println(i + " "+counter +" "+firstOccurence);

        }
    }
}
