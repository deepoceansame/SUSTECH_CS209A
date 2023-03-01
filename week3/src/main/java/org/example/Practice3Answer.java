package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Practice3Answer {
    public static void main(String[] args) {
        String opCodeHint = "Please input the function No:\n" +
                "1 - Get even numbers\n" +
                "2 - Get odd numbers\n" +
                "3 - Get prime numbers\n" +
                "4 - Get prime numbers that are bigger than 5\n" +
                "0 - Quit";
        Scanner in = new Scanner(System.in);
        int opCode = -1;
        int listLength = -1;
        List<Integer> lis = new ArrayList<>();
        while(true){
            System.out.println(opCodeHint);
            opCode = in.nextInt();
            if (opCode==0){
                break;
            }
            System.out.println("Input size of the list:");
            listLength = in.nextInt();
            System.out.println("Input elements of the list:");
            for (int i=0;i<listLength;i++){
                lis.add(in.nextInt());
            }
            System.out.println("Filter results:");
            if (opCode==1){
                System.out.println(lis.stream().filter(o -> o%2==0).collect(Collectors.toList()));
            }
            else if(opCode==2){
                System.out.println(lis.stream().filter(o -> o%2!=0).collect(Collectors.toList()));
            }
            else if (opCode==3){
                System.out.println(lis.stream().filter(o -> isPrime(o)).collect(Collectors.toList()));
            }
            else{
                System.out.println(lis.stream().filter(o->isPrime(o)&&o>5).collect(Collectors.toList()));
            }
            lis.clear();
        }
    }

    static boolean isPrime(int n)
    {
        // Corner case
        if (n <= 1)
            return false;

        // Check from 2 to n-1
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;

        return true;
    }
}
