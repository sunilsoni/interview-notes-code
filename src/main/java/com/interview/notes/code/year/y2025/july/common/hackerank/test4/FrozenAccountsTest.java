package com.interview.notes.code.year.y2025.july.common.hackerank.test4;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Minimal interface to simulate Spring's NamedParameterJdbcTemplate.
 */
interface NamedParameterJdbcTemplate {
    <T> List<T> queryForList(String sql, MapSqlParameterSource params, Class<T> elementType);
}

/**
 * Simulated MapSqlParameterSource for named parameters.
 */
class MapSqlParameterSource {
    private final Map<String, Object> values = new HashMap<>();

    /**
     * Add a named parameter.
     */
    public MapSqlParameterSource addValue(String key, Object value) {
        values.put(key, value);
        return this;
    }

    /**
     * Retrieve a parameter by name.
     */
    @SuppressWarnings("unchecked")
    public <T> T getValue(String key) {
        return (T) values.get(key);
    }
}

/**
 * Simple DTO holding account ID and frozen status.
 */
class FrozenAccountStatus {
    final String accountId;
    final boolean isFrozen;

    public FrozenAccountStatus(String accountId, boolean isFrozen) {
        this.accountId = accountId;
        this.isFrozen = isFrozen;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof FrozenAccountStatus)) return false;
        FrozenAccountStatus other = (FrozenAccountStatus) o;
        return this.accountId.equals(other.accountId)
                && this.isFrozen == other.isFrozen;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, isFrozen);
    }

    @Override
    public String toString() {
        return accountId + " → " + (isFrozen ? "FROZEN" : "ACTIVE");
    }
}

/**
 * Service that checks frozen accounts in bulk.
 */
class AccountService {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public AccountService(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Returns a list of FrozenAccountStatus for each input accountId.
     */
    public List<FrozenAccountStatus> checkFrozenAccounts(List<String> accountIds) {
        // 1. Prepare named parameters for the SQL IN clause
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("accountIds", accountIds);

        // 2. Single query: get all account_ids that are frozen
        List<String> frozenIds = jdbcTemplate.queryForList(
                "SELECT account_id FROM frozen_account WHERE account_id IN (:accountIds)",
                params,
                String.class
        );

        // 3. Build a Set for O(1) contains() checks
        Set<String> frozenSet = new HashSet<>(frozenIds);

        // 4. Map each accountId to its FrozenAccountStatus via a Stream
        return accountIds.stream()
                .map(id -> new FrozenAccountStatus(id, frozenSet.contains(id)))
                .collect(Collectors.toList());
    }
}

/**
 * Stub implementation of NamedParameterJdbcTemplate for testing.
 */
class StubJdbcTemplate implements NamedParameterJdbcTemplate {
    private final Set<String> trulyFrozen;

    public StubJdbcTemplate(Set<String> trulyFrozen) {
        this.trulyFrozen = trulyFrozen;
    }

    @Override
    public <T> List<T> queryForList(String sql, MapSqlParameterSource params, Class<T> elementType) {
        // 1. Retrieve the list of IDs passed in
        @SuppressWarnings("unchecked")
        List<String> requested = params.getValue("accountIds");

        // 2. Simulate DB: return only those IDs present in trulyFrozen
        List<T> result = new ArrayList<>();
        for (String id : requested) {
            if (trulyFrozen.contains(id)) {
                result.add(elementType.cast(id));
            }
        }
        return result;
    }
}

/**
 * Simple main() to run two test scenarios and print PASS/FAIL.
 */
public class FrozenAccountsTest {
    public static void main(String[] args) {
        // --- Test 1: Small dataset ---
        List<String> testIds1 = Arrays.asList("A1", "A2", "A3", "A4");
        Set<String> frozenInDb1 = new HashSet<>(Arrays.asList("A2", "A4"));
        AccountService svc1 = new AccountService(new StubJdbcTemplate(frozenInDb1));
        List<FrozenAccountStatus> actual1 = svc1.checkFrozenAccounts(testIds1);

        List<FrozenAccountStatus> expected1 = Arrays.asList(
                new FrozenAccountStatus("A1", false),
                new FrozenAccountStatus("A2", true),
                new FrozenAccountStatus("A3", false),
                new FrozenAccountStatus("A4", true)
        );
        System.out.println("Test 1: " + (actual1.equals(expected1) ? "PASS" : "FAIL"));

        // --- Test 2: Large dataset (~10_000 IDs) ---
        int N = 10_000;
        List<String> testIds2 = new ArrayList<>(N);
        Set<String> frozenInDb2 = new HashSet<>();
        for (int i = 0; i < N; i++) {
            String id = "ID" + i;
            testIds2.add(id);
            // Freeze ~5% of them
            if (i % 20 == 0) {
                frozenInDb2.add(id);
            }
        }
        AccountService svc2 = new AccountService(new StubJdbcTemplate(frozenInDb2));
        List<FrozenAccountStatus> actual2 = svc2.checkFrozenAccounts(testIds2);

        // Validate count of frozen statuses
        long countFrozen = actual2.stream().filter(s -> s.isFrozen).count();
        System.out.println("Test 2: " + (countFrozen == frozenInDb2.size() ? "PASS" : "FAIL"));
    }
}