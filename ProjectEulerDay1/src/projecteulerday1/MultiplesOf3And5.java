/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecteulerday1;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author fifiteklemedhin
 */
public class MultiplesOf3And5
{
    public static void main(String[] args)
    {
        /* MULTIPLESOF3AND5
        int sum = 0;
        for(int i = 1; i < 1000; i++)
        {
            if(i % 3 == 0 || i % 5 == 0)
                sum += i;
        }	

        System.out.println(sum);
        */
        //System.out.println(fibonnacci(1,1,0,4000000));
        
        /*
        long num2 = 1;
        long num1 = 1;
        long sum = 0;
        while(true)
        {
            long total = num2 + num1;
            if(num2 >= 4000000)
                break;
            if(total % 2 == 0)
                sum += total;
            num2 = num1;
            num1 = total;
        }
        
        System.out.println(sum);
        */
        
        System.out.println(largestPrimeFactor(600851475143L));
        System.out.println(recurLargestPrimeFactor(600851475143L));
        System.out.println(largestPalindrome(3));
    }
    
    public static int gcd(int a, int b)
    {
        if(a > b)
            return gcd(b, a);
        if(b == 0)
            return a;
        return gcd(b/a, b%a);
    }
    
    public static long largestPrimeFactor(long num)
    {
        int i = 2;
        
        while(i * i < num)
        {
            while(num % i == 0)
            {
                num = num / i;
            }
            i = i + 1;
        }
        
    
        return num;
    }
    
    public static long recurLargestPrimeFactor(long n)
    {
        for(int d = 2; d < n; d++)
        {
            if(n % d == 0)
                return largestPrimeFactor(n/d);
        }
       
        return n;
    }
    
    public static long largestPalindrome(long digits)
    {
        HashSet set = new HashSet();
        long max = (long) Math.pow(10, digits);
        System.out.println("MAXL " + max);
        long largest = 0;
        
        for(long i = 1; i <= max; i++)
        {
            for(long j = 1; j <= max; j++)
            {
                System.out.println("j: " + j);
                if(!set.contains(j * i))
                    set.add(j * i);
                if(palindrome(j * i) && (largest < j * i))
                    largest = j * i;

            }
        }
        return largest;
    }
    
    public static boolean palindrome(long n)
    {
        String original = "";
        String palindrome = "";
        while(n != 0)
        {
            palindrome += n % 10 + "";
            n /= 10;
        }
        System.out.println("checked " + n + ": " + original.equals(palindrome));
        return original.equals(palindrome);
    }
    
}
