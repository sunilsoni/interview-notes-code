package com.interview.notes.code.misc.IKMTest;

import java.util.ArrayList;
import java.util.List;

class OuterList {
    public static void main(String[] args) {
        OuterList containClassList = new OuterList();
        //OuterList.IntericrList classList = containClassList.new IntericrList().list.add("s ring");

        OuterList containClassList1 = new OuterList();
        //OuterList.IntericrList classList2 = containClassList1.IntericrList().list.add("string");


        OuterList containClassList2 = new OuterList();

        OuterList.IntericrList classList3 = containClassList2.new IntericrList();
        classList3.list.add("string");

      //  OuterList.IntericrList classList4 = (new OuterList).(new IntericrList());
     //   classList4.list.add("string");

       // OuterList.IntericrList classList5 = OuterList.new IntericrList();
    //    classList5.list.add("string");
    }
    public class IntericrList {
        public List<String> list = new ArrayList<>();
    }
} 