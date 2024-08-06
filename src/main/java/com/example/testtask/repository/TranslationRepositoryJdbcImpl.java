package com.example.testtask.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TranslationRepositoryJdbcImpl implements TranslationRepository{
    private final JdbcClient jdbcClient;
    private static final String SAVE_QUERY = "INSERT INTO translation (req, resp, ip) VALUES (?, ?, ?)";
    @Override
    public void save(String req, String resp, String ipAddr) {
        jdbcClient.sql(SAVE_QUERY)
                .params(req, resp, ipAddr)
                .query();
    }
}
