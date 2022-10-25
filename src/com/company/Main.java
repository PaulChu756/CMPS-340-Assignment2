// Paul Chu
// C00124175
// CMPS 340
// Assignment 2
// Due 10-27-2022
package com.company;
import java.lang.*;

public class Main {

    public static void main(String[] args)
    {
        String s1 = "NKZDCBSMNNBPYCNMRNJNSTHTKADDSWMOQLEPLUFUWOXCCNMUKY";
        String s2 = "FFYUYNAJQSWCUUUTLINQHPIVGPKFIWJEYCYKJRMHSWAMUZCVJT";

        int m = s1.length();
        int n = s2.length();

        long startTime = System.currentTimeMillis();
        System.out.println("Length of LCS Recursion is" + " " + lcsRecursion( s1, s2, m, n ) );
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        System.out.println("LCS Recursion : " + duration + " milliseconds");

        //System.out.println("Length of LCS DP is");
        System.out.println();
        long startTimeDP = System.currentTimeMillis();
        lcsDP( s1, s2, m, n );
        long endTimeDP = System.currentTimeMillis();
        long durationDP = (endTimeDP - startTimeDP);
        System.out.println("LCS DP : " + durationDP + " milliseconds");


    }

    static int lcsRecursion(String X, String Y, int m, int n)
    {
        if (m == 0 || n == 0)
            return 0;
        if (X.charAt(m-1) == Y.charAt(n-1))
            return 1 + lcsRecursion(X, Y, m-1, n-1);
        else
            return Math.max(lcsRecursion(X, Y, m, n-1), lcsRecursion(X, Y, m-1, n));
    }

    static void lcsDP( String X, String Y, int m, int n )
    {
        int L[][] = new int[m+1][n+1];

        for (int i=0; i<=m; i++) {
            for (int j=0; j<=n; j++) {
                if (i == 0 || j == 0)
                    L[i][j] = 0;
                else if (X.charAt(i-1) == Y.charAt(j-1))
                    L[i][j] = L[i-1][j-1] + 1;
                else
                    L[i][j] = Math.max(L[i-1][j], L[i][j-1]);
            }
        }
        //return L[m][n];

        int index = L[m][n];
        int temp = index;

        char[] lcs = new char[index + 1];

        int i = m;
        int j = n;
        while (i > 0 && j > 0) {
            // If current character in X[] and Y are same,
            // then current character is part of LCS
            if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                // Put current character in result
                lcs[index - 1] = X.charAt(i - 1);

                // reduce values of i, j and index
                i--;
                j--;
                index--;
            }

            // If not same, then find the larger of two and
            // go in the direction of larger value
            else if (L[i - 1][j] > L[i][j - 1])
                i--;
            else
                j--;
        }
        // Print the lcs
        System.out.print("LCS DP of " + X + " and " + Y + " is ");
        for (int k = 0; k < temp; k++)
            System.out.print(lcs[k]);
        System.out.println("\nLength of LCS : " + (lcs.length-1));
    }
}




