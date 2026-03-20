package com.interview.notes.code.year.y2026.march.Hackerank.tes1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class AlternatingPrimeThreads
{
    private static boolean isPrime(int n)
    {
        if (n < 2)
        {
            return false;
        }
        if (n == 2)
        {
            return true;
        }
        if (n % 2 == 0)
        {
            return false;
        }
        for (int i = 3; i * i <= n; i += 2)
        {
            if (n % i == 0)
            {
                return false;
            }
        }
        return true;
    }

    private static List<Integer> primesInRange(int start, int end)
    {
        if (start > end)
        {
            return List.of();
        }
        return IntStream.rangeClosed(start, end)
                .filter(AlternatingPrimeThreads::isPrime)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    private static String solve(int n)
    {
        int mid = (n + 1) / 2;
        List<Integer> first = new ArrayList<>();
        List<Integer> second = new ArrayList<>();

        Thread firstThread = new Thread(() -> first.addAll(primesInRange(1, mid)));
        Thread secondThread = new Thread(() -> second.addAll(primesInRange(mid + 1, n)));

        firstThread.start();
        secondThread.start();

        try
        {
            firstThread.join();
            secondThread.join();
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            return "";
        }

        int limit = Math.min(first.size(), second.size());
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < limit; i++)
        {
            out.append(first.get(i)).append('\n');
            out.append(second.get(i)).append('\n');
        }

        if (first.size() > second.size())
        {
            out.append(first.get(limit)).append('\n');
        }

        if (out.length() > 0)
        {
            out.setLength(out.length() - 1);
        }
        return out.toString();
    }

    private static void runTest(int n, String expected)
    {
        String actual = solve(n);
        System.out.println("INPUT: " + n);
        System.out.println(actual.equals(expected) ? "PASS" : "FAIL");
        if (!actual.equals(expected))
        {
            System.out.println("EXPECTED:");
            System.out.println(expected);
            System.out.println("ACTUAL:");
            System.out.println(actual);
        }
        System.out.println();
    }

    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        if (input != null && !input.trim().isEmpty())
        {
            System.out.print(solve(Integer.parseInt(input.trim())));
            return;
        }

        runTest(10, "2\n7\n3");
        runTest(21, "2\n13\n3\n17\n5\n19\n7");
        runTest(1, "");
        runTest(2, "");
        runTest(3, "2");
        runTest(30, "2\n17\n3\n19\n5\n23\n7\n29");
        runTest(100000, solve(100000));
    }
}