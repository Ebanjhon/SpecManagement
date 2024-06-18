/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.DTO;

import java.util.List;

/**
 *
 * @author nmau4
 */
import java.util.List;

/**
 *
 * @author nmau4
 */
public class SearchResultDTO<T> {
    private List<T> results;//T là kiểugeneric  
    private long totalCount;

    public SearchResultDTO(List<T> results, long totalCount) {
        this.results = results;
        this.totalCount = totalCount;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}
    
