package com.interview.notes.code.misc.test7;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {

        //if (true)
           // break;
               // System.out.println('j' + 'a' + 'v' + 'a');

        Set set = new HashSet<>();
        //set.add();
        set.forEach(System.out::println);

        StringBuilder b = new StringBuilder("abc");

        String s="ABC";

       s=  s.concat(b+"");
        System.out.println(s);
        }


    public   TransferResponse getTransferResponse() {
      //  Function<TransferResponse, Optional<AccountKey>> getFromAccountKey = transferResponse -> Optional.ofNullable(transferResponse).map(n -> n.fromAccount).get();


      //  List<TransferResponse> fromAccountList = new ArrayList<>();
     //  List<Optional<TransferResponse>> res =  fromAccountList.stream().map(getFromAccountKey).collect(Collectors.toList());



return null;
     }



    public TransferResponse getDefault(){
        return new TransferResponse();
    }


    //return new TransferResponse();
}
