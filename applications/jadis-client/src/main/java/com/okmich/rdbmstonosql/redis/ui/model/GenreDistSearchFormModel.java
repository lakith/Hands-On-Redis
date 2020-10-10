/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.okmich.rdbmstonosql.redis.ui.model;

import com.okmich.rdbmstonosql.redis.dao.SearchResult;
import com.okmich.rdbmstonosql.redis.dao.MovieDao;
import java.util.Map;
import java.util.Map.Entry;

public class GenreDistSearchFormModel implements SearchFormModel<Map<String, Long>> {

    private final MovieDao dao;
    private Map<String, Long> results;
    private long recordCount;

    private static final String[] COL_NAME = new String[]{"Genre", "Number of movies"};

    public GenreDistSearchFormModel(MovieDao dao) {
        this.dao = dao;
    }

    @Override
    public void update(Object... args) {
        results = dao.findGenreMovieDist();

        this.recordCount = results.size();
    }

    @Override
    public SearchResult<Map<String, Long>> getSearchResult(String... ts) {
        return null;
    }

    @Override
    public Object[][] tableData() {
        Object[][] data = new Object[results.size()][columnNames().length];
        int i = 0;
        for (Entry<String, Long> entry : results.entrySet()) {
            data[i][0] = entry.getKey();
            data[i][1] = entry.getValue();
            i++;
        }

        return data;
    }

    @Override
    public String currentCursor() {
        return "";
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }

    @Override
    public void next() {

    }

    @Override
    public void previous() {
    }

    @Override
    public boolean isLastIteration() {
        return true;
    }

    @Override
    public long recordCount() {
        return this.recordCount;
    }

    @Override
    public String[] columnNames() {
        return COL_NAME;
    }

}
