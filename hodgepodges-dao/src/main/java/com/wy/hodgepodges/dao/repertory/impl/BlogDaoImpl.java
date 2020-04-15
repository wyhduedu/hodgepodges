package com.wy.hodgepodges.dao.repertory.impl;

import com.wy.hodgepodges.dao.repertory.BlogDao;
import com.wy.hodgepodges.moudle.vo.BlogInfo;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.Optional;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020-04-05 21:53
 */
public class BlogDaoImpl implements BlogDao {
    @Override
    public void queryAllByContent(String str) {

    }

    @Override
    public <S extends BlogInfo> S index(S s) {
        return null;
    }

    @Override
    public <S extends BlogInfo> S indexWithoutRefresh(S s) {
        return null;
    }

    @Override
    public Iterable<BlogInfo> search(QueryBuilder queryBuilder) {
        return null;
    }

    @Override
    public Page<BlogInfo> search(QueryBuilder queryBuilder, Pageable pageable) {
        return null;
    }

    @Override
    public Page<BlogInfo> search(SearchQuery searchQuery) {
        return null;
    }

    @Override
    public Page<BlogInfo> searchSimilar(BlogInfo blogInfo, String[] strings, Pageable pageable) {
        return null;
    }

    @Override
    public void refresh() {

    }

    @Override
    public Class<BlogInfo> getEntityClass() {
        return null;
    }

    @Override
    public Iterable<BlogInfo> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<BlogInfo> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends BlogInfo> S save(S s) {
        return null;
    }

    @Override
    public <S extends BlogInfo> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<BlogInfo> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public Iterable<BlogInfo> findAll() {
        return null;
    }

    @Override
    public Iterable<BlogInfo> findAllById(Iterable<String> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(BlogInfo blogInfo) {

    }

    @Override
    public void deleteAll(Iterable<? extends BlogInfo> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
