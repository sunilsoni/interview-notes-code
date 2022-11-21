package com.interview.notes.code.misc.IKMTest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Letters {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

     //   var client = HttpClient.newHttpClient();
     //   var request = HttpRequest.newBuilder().uri(new URI("ur1")).version(HttpClient.Version.HTTP_2).GET().build(); var response = client.send(request, HttpResponse.BodyHandlers.ofString());



        List<String> Letters = new ArrayList<>(Arrays.asList("D", "B", "A", "C", "F", "G"));
        Predicate<String> p1 = (s) -> s.compareTo("C") > 0;
        Predicate<String> p2 = (s) -> s.equals("B");
        Letters.removeIf(p1.negate().or(p2));
        Letters.sort((sl, s2) -> sl.compareTo(s2));
        System.out.println(Letters);

        //
        int i = 10, j = 12;
        for (; ; ) {
            if (i++ < j--) continue;
            else
                break;

           // System.out.println(1 + j);
        }


    }
}
