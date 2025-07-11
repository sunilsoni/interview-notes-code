package com.interview.notes.code.year.y2025.july.hackerank.test5;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.*;

public class Application {

    public static void main(String[] args) {
        // 1) Configure HikariCP DataSource
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/mydb");
        hikariConfig.setUsername("dbuser");
        hikariConfig.setPassword("secret");
        // …any other pool settings you need…
        DataSource ds = new HikariDataSource(hikariConfig);

        // 2) Build a NamedParameterJdbcTemplate on top of that DataSource
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(ds);

        // 3) Instantiate your service/DAO
        AccountService accountService = new AccountService(jdbcTemplate);

        // 4) Demo: check three accounts
        List<String> accountIds = Arrays.asList("A123", "B456", "C789");
        Map<String, Boolean> statusMap = accountService.fetchFrozenStatuses(accountIds);

        // 5) Print results
        statusMap.forEach((id, frozen) ->
                System.out.println("Account " + id + " frozen? " + frozen)
        );
    }
}

class AccountService {
    // Single SQL to fetch _all_ frozen accounts at once
    private static final String SQL_FROZEN_IN =
            "SELECT account_id FROM frozen_accounts WHERE account_id IN (:ids)";

    private final NamedParameterJdbcTemplate jdbc;

    public AccountService(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * Returns a map of accountId → isFrozen (true if freeze_flag=1).
     * Does one DB round-trip regardless of how many IDs you pass in.
     */
    public Map<String, Boolean> fetchFrozenStatuses(Collection<String> accountIds) {
        // 1) handle null/empty input
        if (accountIds == null || accountIds.isEmpty()) {
            return Collections.emptyMap();
        }

        // 2) bind the entire list under one named parameter
        MapSqlParameterSource params = new MapSqlParameterSource("ids", accountIds);

        // 3) execute the query once
        Set<String> frozenSet = new HashSet<>();
        try {
            List<String> found = jdbc.queryForList(SQL_FROZEN_IN, params, String.class);
            frozenSet.addAll(found);
        } catch (DataAccessException dae) {
            // on error, log & treat everyone as “not frozen”
            System.err.println("Error fetching frozen accounts: " + dae.getMessage());
        }

        // 4) build the final map maintaining input order
        Map<String, Boolean> result = new LinkedHashMap<>();
        for (String id : accountIds) {
            result.put(id, frozenSet.contains(id));
        }
        return result;
    }
}