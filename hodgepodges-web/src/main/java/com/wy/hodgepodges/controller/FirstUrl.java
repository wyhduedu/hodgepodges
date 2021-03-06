package com.wy.hodgepodges.controller;

import com.wy.hodgepodges.dao.repertory.BlogDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020-03-19 11:02
 */
@RestController
@Slf4j
public class FirstUrl {


    @Autowired
    private BlogDao blogDao;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    @RequestMapping(value = "/json", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String aaa(HttpServletRequest request) {
        log.info("请求参数，request->{}", request);
        log.info("11" + request.getRequestURI() + "cam access");
        return "11" + request.getRequestURI() + "cam access";
    }

    @CacheEvict()
    @RequestMapping(value = "/requestParam", produces = "text/plain;charset=UTF-8")
    public @ResponseBody String passRequestParam(Long id, HttpServletRequest request) {
        log.info("url" + request.getRequestURL() + "can access" + "  " + id);
        return "url" + request.getRequestURL() + "can access" + "  " + id;
    }

}
