package com.interview.notes.code.year.y2024.dec24.amazon.test4; /**
 * Updated solution using an abstract class (BaseEntry) for shared fields:
 * Both Review and Comment now extend BaseEntry, sharing 'text' and 'language' fields.
 * The rest of the logic (filters, search service, tests) remains similar to the previous solution.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Filter Interface
interface SearchFilter {
    boolean matchesReview(Review review);

    boolean matchesComment(Comment comment);
}

// Abstract base class for both Review and Comment
abstract class BaseEntry {
    protected String text;
    protected String language;

    public BaseEntry(String text, String language) {
        this.text = text;
        this.language = language;
    }

    public String getText() {
        return text;
    }

    public String getLanguage() {
        return language;
    }
}

// Comment class extending BaseEntry and having a list of replies
class Comment extends BaseEntry {
    private final List<Comment> replies;

    public Comment(String text, String language, List<Comment> replies) {
        super(text, language);
        this.replies = replies != null ? replies : new ArrayList<>();
    }

    public List<Comment> getReplies() {
        return replies;
    }
}

// Review class extending BaseEntry and having a list of comments
class Review extends BaseEntry {
    private final List<Comment> comments;

    public Review(String text, String language, List<Comment> comments) {
        super(text, language);
        this.comments = comments != null ? comments : new ArrayList<>();
    }

    public List<Comment> getComments() {
        return comments;
    }
}

// Text search filter
class TextSearchFilter implements SearchFilter {
    private final String searchText;

    public TextSearchFilter(String searchText) {
        this.searchText = searchText == null ? "" : searchText.trim();
    }

    @Override
    public boolean matchesReview(Review review) {
        return review.getText().contains(searchText);
    }

    @Override
    public boolean matchesComment(Comment comment) {
        return comment.getText().contains(searchText);
    }
}

// Language filter
class LanguageFilter implements SearchFilter {
    private final String targetLanguage;

    public LanguageFilter(String targetLanguage) {
        this.targetLanguage = targetLanguage == null ? "" : targetLanguage.trim();
    }

    @Override
    public boolean matchesReview(Review review) {
        return review.getLanguage().equalsIgnoreCase(targetLanguage);
    }

    @Override
    public boolean matchesComment(Comment comment) {
        return comment.getLanguage().equalsIgnoreCase(targetLanguage);
    }
}

// Composite filter for combining multiple filters with logical AND
class CompositeFilter implements SearchFilter {
    private final List<SearchFilter> filters;

    public CompositeFilter(List<SearchFilter> filters) {
        this.filters = filters;
    }

    @Override
    public boolean matchesReview(Review review) {
        for (SearchFilter f : filters) {
            if (!f.matchesReview(review)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean matchesComment(Comment comment) {
        for (SearchFilter f : filters) {
            if (!f.matchesComment(comment)) {
                return false;
            }
        }
        return true;
    }
}

// Review Search Service
class ReviewSearchService {
    public List<Review> search(List<Review> reviews, SearchFilter filter) {
        List<Review> result = new ArrayList<>();
        for (Review r : reviews) {
            boolean reviewMatches = filter.matchesReview(r);
            boolean commentMatches = checkComments(r.getComments(), filter);
            if (reviewMatches || commentMatches) {
                result.add(r);
            }
        }
        return result;
    }

    private boolean checkComments(List<Comment> comments, SearchFilter filter) {
        for (Comment c : comments) {
            if (filter.matchesComment(c) || checkComments(c.getReplies(), filter)) {
                return true;
            }
        }
        return false;
    }
}

// Main class for testing
public class ReviewSearchTest {
    public static void main(String[] args) {
        // Prepare test data
        Comment nestedComment = new Comment("Nested reply in English", "en", null);
        Comment comment1 = new Comment("This is a comment in English", "en", List.of(nestedComment));
        Comment comment2 = new Comment("Este es un comentario en Español", "es", null);
        Review review1 = new Review("Great product, I love it!", "en", List.of(comment1));
        Review review2 = new Review("Buen producto, bastante útil", "es", List.of(comment2));
        List<Review> allReviews = Arrays.asList(review1, review2);

        // Test 1: Text Search
        SearchFilter textFilter = new TextSearchFilter("love");
        List<Review> textResult = new ReviewSearchService().search(allReviews, textFilter);
        System.out.println("Test 1 (Text contains 'love') " + (textResult.size() == 1 ? "PASS" : "FAIL"));

        // Test 2: Language Filter
        SearchFilter langFilter = new LanguageFilter("es");
        List<Review> langResult = new ReviewSearchService().search(allReviews, langFilter);
        System.out.println("Test 2 (Language 'es') " + (langResult.size() == 1 ? "PASS" : "FAIL"));

        // Test 3: Composite Filter (text='useful' and language='en' - expecting none)
        SearchFilter textFilterNoMatch = new TextSearchFilter("useful");
        SearchFilter langFilterEn = new LanguageFilter("en");
        CompositeFilter composite = new CompositeFilter(Arrays.asList(textFilterNoMatch, langFilterEn));
        List<Review> compositeResult = new ReviewSearchService().search(allReviews, composite);
        System.out.println("Test 3 (Text='useful' AND lang='en') " + (compositeResult.isEmpty() ? "PASS" : "FAIL"));

        // Edge Case: Empty list
        List<Review> emptyResult = new ReviewSearchService().search(new ArrayList<>(), textFilter);
        System.out.println("Test 4 (Empty reviews list) " + (emptyResult.isEmpty() ? "PASS" : "FAIL"));

        // Large data test (simple simulation)
        List<Review> largeData = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            largeData.add(new Review("Large review dataset " + i, "en", null));
        }
        List<Review> largeDataResult = new ReviewSearchService().search(largeData, new TextSearchFilter("9999"));
        System.out.println("Test 5 (Large data search) " + (largeDataResult.size() == 1 ? "PASS" : "FAIL"));
    }
}
