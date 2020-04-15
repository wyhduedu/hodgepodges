package com.wy.hodgepodges.dao.repertory;


import com.wy.hodgepodges.moudle.vo.BlogInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020-04-05 21:50
 */

@Repository
public interface BlogDao extends ElasticsearchRepository<BlogInfo,String> {

    // 可以用jpa 语法
    void queryAllByContent(String str);
}

