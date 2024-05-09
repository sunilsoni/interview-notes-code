package com.interview.notes.code.months.may24.test1;

import reactor.core.publisher.Mono;

public class StringCombineExample {
    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "xyz";

        Mono<String> mono1 = Mono.just(s1);
        Mono<String> mono2 = Mono.just(s2);

        Mono<String> combinedMono = Mono.zip(mono1, mono2)
                .map(tuple -> tuple.getT1() + tuple.getT2());

        combinedMono.subscribe(System.out::println);
    }
}
