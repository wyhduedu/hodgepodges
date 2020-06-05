package com.wy.hodgepodges.manager;

import com.wy.hodgepodges.dao.repertory.BlogDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020/5/29 11:24 下午
 */
@Component
@Slf4j
public class FileUtils {

    //用于对数据库操作的Dao接口，博主用的是
    //Mybatis，由Mybatis调用接口返回数据库数据。
    @Resource
    private BlogDao blogDao;

    //静态化工具类变量
    public static FileUtils fileUtils;

    //注解用于告诉次代码在Spring加载之前就运行
    @PostConstruct
    public void init(){
        //工具类的实例赋值给fileUtils
        fileUtils = this;
        //会激活Spring对Dao的管理并赋给此类
        fileUtils.blogDao=this.blogDao;
        log.info("工具类已经初始化了，被纳入spring管理");

        //我们在初始化之后调用一下静态方法
        FileUtils.initFileStructure();
    }
    public static void initFileStructure(){
        //查询所有文件并输出
        List<String> fileInfos = fileUtils.blogDao.queryAllByContent("1");
        for (String fileInfo : fileInfos) {
            System.out.println("===="+fileInfo);
        }

    }
}
