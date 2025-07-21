package com.interview.notes.code.year.y2025.july.amazon.test4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/*


### ❓ Question:

Design and implement the necessary **APIs and classes** to run **text processing pipelines** with the following specifications.

---

### 📘 **Operations**

#### **Document Level:**

* `d1.` Replace tabs with white spaces
* `d2.` Convert all words to lower case
* `d3.` Break the whole document into sentences
  *(Assume sentences only end with a line break)*

#### **Sentence Level:**

* `s1.` Count the number of words in a sentence
* `s2.` Reverse the sentence letter by letter
  *(e.g., `"abc def"` → `"fed cba"`)*

#### **More Document Level:**

* `d4.` Calculate the average number of words per sentence
* `d5.` Reverse the whole document letter by letter
* `d6.` Print the result from `d4`
* `d7.` Print the whole document

---

### 🔁 **Types of Pipelines:**

* `p1:`
  Run `d1`, `d2`, `d3`,
  Then for **each sentence**, run `s1`, `s2`,
  Then `d4`, `d5`, `d6`, `d7`

* `p2:`
  Run `d1`, `d2`, `d3`,
  Then `s2`, `d5`, `d6`, `d7`

---

### 📌 Requirements:

* The code **must be extensible** to support more operations and pipeline types in the future.
* The code should be **maintainable** and **testable**.

---

 */
public class TextProcessingPipeline {

    // Main for testing PASS/FAIL and performance
    public static void main(String[] args) {
        // sample document
        String sample = "Hello\tWorld\nThis is Java\nStreams are cool";
        Document doc1 = new Document(sample);
        System.out.println("=== Running Pipeline p1 ===");
        PipelineFactory.p1().run(doc1);

        // expected checks (very simple assertions)
        boolean pass1 = doc1.avgWords == ((2 + 3 + 3) / 3.0);
        System.out.println(pass1 ? "p1 PASS" : "p1 FAIL");

        // pipeline 2
        Document doc2 = new Document(sample);
        System.out.println("\n=== Running Pipeline p2 ===");
        PipelineFactory.p2().run(doc2);
        boolean pass2 = doc2.avgWords == doc1.avgWords;
        System.out.println(pass2 ? "p2 PASS" : "p2 FAIL");

        // large input performance test
        StringBuilder large = new StringBuilder();
        for (int i = 0; i < 100_000; i++) {
            large.append("word1 word2 word3\n");
        }
        Document bigDoc = new Document(large.toString());
        long start = System.currentTimeMillis();
        PipelineFactory.p1().run(bigDoc);
        long duration = System.currentTimeMillis() - start;
        System.out.println("Large doc p1 took " + duration + " ms");
    }

    // Document-level operation interface
    interface DocumentOperation {
        void apply(Document doc);
    }

    // Sentence-level operation interface
    interface SentenceOperation {
        void apply(Sentence sentence);
    }

    // Model for a sentence
    static class Sentence {
        String content;    // sentence text
        int wordCount;     // number of words

        Sentence(String content) {
            this.content = content;               // initialize content
            this.wordCount = 0;                   // default count
        }
    }

    // Model for a document
    static class Document {
        String content;                 // full document text
        List<Sentence> sentences;       // split sentences
        double avgWords;                // average words per sentence

        Document(String content) {
            this.content = content;      // initialize raw text
            this.sentences = new ArrayList<>();
            this.avgWords = 0.0;
        }
    }

    // d1: Replace tabs with spaces
    static class ReplaceTabs implements DocumentOperation {
        @Override
        public void apply(Document doc) {
            doc.content = doc.content.replace("\t", " ");  // replace all tabs
        }
    }

    // d2: Convert to lower case
    static class ToLowerCase implements DocumentOperation {
        @Override
        public void apply(Document doc) {
            doc.content = doc.content.toLowerCase();       // lowercase entire text
        }
    }

    // d3: Split into sentences (by newline)
    static class SplitSentences implements DocumentOperation {
        @Override
        public void apply(Document doc) {
            // stream lines, wrap each as Sentence
            doc.sentences = Arrays.stream(doc.content.split("\\r?\\n"))
                    .map(Sentence::new)
                    .collect(Collectors.toList());
        }
    }

    // s1: Count words in a sentence
    static class CountWords implements SentenceOperation {
        @Override
        public void apply(Sentence s) {
            // split on whitespace, filter out empty, count
            s.wordCount = (int) Arrays.stream(s.content.trim().split("\\s+"))
                    .filter(w -> !w.isEmpty())
                    .count();
            System.out.println("Word count: " + s.wordCount);  // print count
        }
    }

    // s2: Reverse sentence letters
    static class ReverseLetters implements SentenceOperation {
        @Override
        public void apply(Sentence s) {
            // reverse chars
            s.content = new StringBuilder(s.content)
                    .reverse()
                    .toString();
            System.out.println("Reversed sentence: " + s.content);
        }
    }

    // d4: Compute average words per sentence
    static class ComputeAverage implements DocumentOperation {
        @Override
        public void apply(Document doc) {
            // sum wordCount, divide by sentence count
            doc.avgWords = doc.sentences.stream()
                    .mapToInt(s -> s.wordCount)
                    .average()
                    .orElse(0.0);
        }
    }

    // d5: Reverse entire document letters
    static class ReverseDocument implements DocumentOperation {
        @Override
        public void apply(Document doc) {
            doc.content = new StringBuilder(doc.content)
                    .reverse()
                    .toString();
        }
    }

    // d6: Print average
    static class PrintAverage implements DocumentOperation {
        @Override
        public void apply(Document doc) {
            System.out.println("Average words/sentence: " + doc.avgWords);
        }
    }

    // d7: Print document
    static class PrintDocument implements DocumentOperation {
        @Override
        public void apply(Document doc) {
            System.out.println("Document content:\n" + doc.content);
        }
    }

    // Pipeline runner
    static class Pipeline {
        List<DocumentOperation> docOps;
        List<SentenceOperation> sentOps;

        Pipeline(List<DocumentOperation> dOps, List<SentenceOperation> sOps) {
            this.docOps = dOps;        // document ops in order
            this.sentOps = sOps;       // sentence ops in order
        }

        void run(Document doc) {
            docOps.forEach(op -> op.apply(doc));  // run all doc ops

            // for each sentence, run all sentence ops
            doc.sentences.forEach(s -> sentOps.forEach(op -> op.apply(s)));

            // note: no further action
        }
    }

    // Factory for pipelines
    static class PipelineFactory {
        // p1: d1,d2,d3, then s1,s2, then d4,d5,d6,d7
        static Pipeline p1() {
            return new Pipeline(
                    Arrays.asList(
                            new ReplaceTabs(), new ToLowerCase(), new SplitSentences(),
                            new ComputeAverage(), new ReverseDocument(), new PrintAverage(), new PrintDocument()
                    ),
                    Arrays.asList(new CountWords(), new ReverseLetters())
            );
        }

        // p2: d1,d2,d3, then s2, then d4,d5,d6,d7  (we added d4 so d6 works)
        static Pipeline p2() {
            return new Pipeline(
                    Arrays.asList(
                            new ReplaceTabs(), new ToLowerCase(), new SplitSentences(),
                            new ComputeAverage(), new ReverseDocument(), new PrintAverage(), new PrintDocument()
                    ),
                    Collections.singletonList(new ReverseLetters())
            );
        }
    }
}