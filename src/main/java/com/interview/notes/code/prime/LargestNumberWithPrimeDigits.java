package com.interview.notes.code.prime;

//https://www.geeksforgeeks.org/largest-number-prime-digits/
public class LargestNumberWithPrimeDigits {

    // check if character is prime
    public static boolean isPrime(char c)
    {
        return (c == '2' || c == '3' || c == '5' || c == '7');
    }

    // replace with previous prime character
    public static void decrease(StringBuilder s, int i)
    {
        if (s.charAt(i) <= '2')
        {

            // if 2 erase s[i] and replace next with 7
            s.deleteCharAt(i);
            s.setCharAt(i, '7');
        }
        else if (s.charAt(i) == '3')
            s.setCharAt(i, '2');
        else if (s.charAt(i) <= '5')
            s.setCharAt(i, '3');
        else if (s.charAt(i) <= '7')
            s.setCharAt(i, '5');
        else
            s.setCharAt(i, '7');

        return;
    }

    public static String primeDigits(StringBuilder s)
    {
        for (int i = 0; i < s.length(); i++)
        {

            // find first non prime char
            if (!isPrime(s.charAt(i)))
            {

                // find first char greater than 2
                while (i >= 0 && s.charAt(i) <= '2')
                    i--;

                // like 20
                if (i < 0)
                {
                    i = 0;
                    decrease(s, i);
                }

                // like 7721
                else
                    decrease(s, i);

                // replace remaining with 7
                for (int j = i + 1; j < s.length(); j++)
                    s.setCharAt(j, '7');
                break;
            }
        }

        return s.toString();
    }

    // Driver code
    public static void main(String[] args)
    {
        StringBuilder s = new StringBuilder("45");
        System.out.println(primeDigits(s));//37

        s = new StringBuilder("1000");
        System.out.println(primeDigits(s));//777

        s = new StringBuilder("7721");
        System.out.println(primeDigits(s));//7577

        s = new StringBuilder("7221");
        System.out.println(primeDigits(s));

        s = new StringBuilder("74545678912345689748593275897894708927680");
        System.out.println(primeDigits(s));
    }
}
