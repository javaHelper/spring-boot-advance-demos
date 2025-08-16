package com.diee.jpademo.repository;

public interface BaseRepository<D, T> {

    D findWithGraph(T id, String graphName);
}
