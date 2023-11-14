package com.interview.notes.code.months.nov23.test5;

import java.util.Scanner;

class Solution1
{
    public static void main (String[] args) throws java.lang.Exception
    {
        //BT1 bt = new BT1();
        BinaryTree bt = new BinaryTree();
        Scanner in = new Scanner(System.in);
        int edges, parent, child;
        char direction;
        int[] levelSpiral;
        edges = in.nextInt();       
        for(int i=0; i<edges; i++){
            parent = in.nextInt();
            child = in.nextInt();
            direction = in.next().charAt(0);
            bt.addNode(parent, child, direction);
        }
        levelSpiral = bt.getLevelSpiral();
        for (int i=0; i<levelSpiral.length; i++){
            System.out.print(levelSpiral[i]+" ");
        }
    }

    /**
     *
     2
     10 20 R 10 30 L


     4
     2 4 L 2 6 R 8 L 4 10 R
     */
}