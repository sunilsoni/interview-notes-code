package com.interview.notes.code.year.y2024.dec24.amazon.test3; /**
 * Problem Analysis:
 * We need an API to search through reviews and their comments, which can be nested.
 * Two initial use cases:
 * 1) Find all reviews/comments containing a given search text.
 * 2) Find all reviews/comments written in a specific language.
 * The solution should be extensible for additional filtering criteria in the future.
 * Key considerations:
 * - Reviews can have multiple levels of comments.
 * - The search should apply to both reviews and comments.
 * - The design should allow combining multiple filters (e.g., text AND language).
 * - Consider efficiency and clarity. For large inputs, filters should not do excessive repeated scans.
 * <p>
 * Approach:
 * - Define simple data models for Review and Comment.
 * - Implement a flexible filtering interface (SearchFilter) applicable to both Review and Comment.
 * - Provide concrete filters: TextSearchFilter and LanguageFilter.
 * - Support composite filtering by combining multiple filters logically (e.g. AND operation).
 * - Implement a search service that:
 * 1) Iterates over all reviews.
 * 2) Checks the review itself against the filter.
 * 3) Recursively checks comments and nested replies against the filter.
 * 4) Collects matches in a result list.
 * <p>
 * Feasibility:
 * - The approach is straightforward and relies on simple iteration and predicate checks.
 * - Memory usage is proportional to data size; no special constraints were given, so a list-based approach is fine.
 * - For large data sets, consider streaming or indexing, but that’s beyond current scope.
 * <p>
 * Impact and Sustainability:
 * - The solution is flexible enough to add new filters later without changing the core logic.
 * - Easy to maintain: adding new filter classes or modifying logic is localized.
 * <p>
 * Essential Requirements:
 * - Ability to filter by text and language.
 * - Support nested comments.
 * Nice-to-have:
 * - Combining filters (already considered with CompositeFilter).
 * - Additional filters for future extension.
 * <p>
 * Minimal Reproducible Example Below.
 * Tests:
 * - Basic tests: text search on a small set, language filter on a small set, combined filters.
 * - Edge cases: empty lists, no matches, multiple nested levels.
 * - Large data scenario: simulate with repeated data and ensure it runs efficiently.
 * <p>
 * Testing Approach:
 * - Use a main method for testing.
 * - Print PASS/FAIL for each test scenario.
 * - Manually verify outputs for correctness.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Filter Interface
interface SearchFilter {
    boolean matchesReview(Review review);

    boolean matchesComment(Comment comment);
}

// Data Models
record Comment(String text, String language, List<Comment> replies) {
    Comment(String text, String language, List<Comment> replies) {
        this.text = text;
        this.language = language;
        this.replies = replies != null ? replies : new ArrayList<>();
    }
}

record Review(String text, String language, List<Comment> comments) {
    Review(String text, String language, List<Comment> comments) {
        this.text = text;
        this.language = language;
        this.comments = comments != null ? comments : new ArrayList<>();
    }
}

// Concrete Filters
class TextSearchFilter implements SearchFilter {
    private final String searchText;

    public TextSearchFilter(String searchText) {
        this.searchText = searchText == null ? "" : searchText.trim();
    }

    @Override
    public boolean matchesReview(Review review) {
        return review.text().contains(searchText);
    }

    @Override
    public boolean matchesComment(Comment comment) {
        return comment.text().contains(searchText);
    }
}

class LanguageFilter implements SearchFilter {
    private final String targetLanguage;

    public LanguageFilter(String targetLanguage) {
        this.targetLanguage = targetLanguage == null ? "" : targetLanguage.trim();
    }

    @Override
    public boolean matchesReview(Review review) {
        return review.language().equalsIgnoreCase(targetLanguage);
    }

    @Override
    public boolean matchesComment(Comment comment) {
        return comment.language().equalsIgnoreCase(targetLanguage);
    }
}

// Composite Filter (for future extension)
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
            // Check comments recursively
            boolean commentMatches = checkComments(r.comments(), filter);
            if (reviewMatches || commentMatches) {
                result.add(r);
            }
        }
        return result;
    }

    private boolean checkComments(List<Comment> comments, SearchFilter filter) {
        for (Comment c : comments) {
            if (filter.matchesComment(c) || checkComments(c.replies(), filter)) {
                return true;
            }
        }
        return false;
    }
}

// Main class for testing
public class Solution {
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
        // review2 or its comment is in 'es', so match expected
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

        // Large data test (simple simulation by repeating reviews)
        List<Review> largeData = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            largeData.add(new Review("Large review dataset " + i, "en", null));
        }
        List<Review> largeDataResult = new ReviewSearchService().search(largeData, new TextSearchFilter("9999"));
        // Should find exactly one match
        System.out.println("Test 5 (Large data search) " + (largeDataResult.size() == 1 ? "PASS" : "FAIL"));

        // All tests done
    }
}
