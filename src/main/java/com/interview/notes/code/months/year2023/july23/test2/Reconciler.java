package com.interview.notes.code.months.year2023.july23.test2;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * In Java 8
 * <p>
 * You are working with a transaction system and your goal is to filter a stream<PendingTransaction> to
 * retrieve only the transactions that have already been processed. You have written a simple Reconciler
 * class that has a reconcile method, which returns stream<PendingTransaction> and accepts two
 * arguments:
 * •  Stream<PendingTransaction>
 * •  Stream<Stream<ProcessedTransaction>>
 * Both arguments can be null, empty or not empty. Whatever the values for the arguments are, you musn't
 * return null. Always return an empty or filtered stream.
 * We have already told the developers responsible for processing transactions that it's pointless to wrap a
 * stream in another stream. In response, they have told us that this is to be fixed, but no earlier than in
 * version 2. Also, they have warned us that the wrapped stream may be null, and it may also contain null
 * values.
 * A PendingTransaction is considered processed if:
 * •  a corresponding object is found in Stream<Stream<ProcessedTransaction».
 * • the corresponding object has the same ID, but the type of PendingTransaction.id is Long whereas
 * the type of ProcessedTransaction.id is string, and it might be null or empty. Luckily, if it has a value,
 * it will certainly be a numeric value.
 * • the processedTransaction.status, which has type Optionai<string>, has a value equal to done, which
 * is case((-))insensitive. This field might be null, too.
 * • the value of PendingTransaction.id field is always set correctly.
 * To access PendingTransaction.id Or ProcessedTransaction.id fields, Call getld(). To access
 * ProcessedTransaction.status, call ProcessedTransaction.getStatus().
 * <p>
 * <p>
 * <p>
 * package com.codility;
 * import java.util.*;
 * import java.util.stream.Stream;
 * class Reconciler {
 * St ream<PendingTransaction> reconcile(Stream<PendingTransaction>
 * pending, Stream<Stream<ProcessedTransaction» processed) {
 * return null;
 * }
 * }
 */
class Reconciler {

    public static void main(String[] args) {
        // Create a Reconciler instance
        Reconciler reconciler = new Reconciler();

        // Create some PendingTransaction objects
        Stream<PendingTransaction> pendingTransactions = Stream.of(
                new PendingTransaction(1L),
                new PendingTransaction(2L),
                new PendingTransaction(3L)
        );

        // Create some ProcessedTransaction objects
        Stream<Stream<ProcessedTransaction>> processedTransactions = Stream.of(
                Stream.of(new ProcessedTransaction("1", Optional.of("done")),
                        new ProcessedTransaction("2", Optional.of("NotDone"))),
                Stream.of(new ProcessedTransaction("3", Optional.of("Done")),
                        new ProcessedTransaction("4", Optional.of("done"))),
                Stream.of(new ProcessedTransaction("5", Optional.of("NotDone")))
        );

        // Use the Reconciler to filter the pending transactions
        Stream<PendingTransaction> reconciled = reconciler.reconcile(pendingTransactions, processedTransactions);

        // Print out the IDs of the reconciled transactions
        reconciled.map(PendingTransaction::getId).forEach(System.out::println);
    }

    Stream<PendingTransaction> reconcile(Stream<PendingTransaction> pending, Stream<Stream<ProcessedTransaction>> processed) {
        // Handling null processed transactions
        if (processed == null) processed = Stream.empty();

        // Flatten Stream<Stream<ProcessedTransaction>> to Stream<ProcessedTransaction> and ignore null processed transactions
        Stream<ProcessedTransaction> flatProcessed = processed
                .filter(Objects::nonNull)
                .flatMap(ptStream -> ptStream.filter(Objects::nonNull));

        // Create a map of processed transactions with their IDs for efficient lookup
        Map<String, ProcessedTransaction> processedMap = flatProcessed
                .filter(pt -> pt.getId() != null && !pt.getId().isEmpty())
                .collect(Collectors.toMap(ProcessedTransaction::getId, pt -> pt));

        // If pending is null return an empty stream, otherwise filter transactions
        return pending == null ? Stream.empty() : pending
                .filter(p -> {
                    ProcessedTransaction correspondingPT = processedMap.get(String.valueOf(p.getId()));
                    return correspondingPT != null
                            && correspondingPT.getStatus().isPresent()
                            && "done".equalsIgnoreCase(correspondingPT.getStatus().get());
                });
    }
}
