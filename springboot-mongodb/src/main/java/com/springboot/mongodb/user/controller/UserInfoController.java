package com.springboot.mongodb.user.controller;/**
 * Created by dell on 2017/11/13.
 */

import com.springboot.mongodb.base.controller.BaseController;
import com.springboot.mongodb.base.entity.PageQuery;
import com.springboot.mongodb.user.dao.UserInfoRepository;
import com.springboot.mongodb.user.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户服务控制类
 *
 * @author jiasx
 * @create 2017-11-13 16:13
 **/
@RestController
@RequestMapping("/user")
public class UserInfoController extends BaseController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @PostMapping(value = "/insert")
    public UserInfo insert(UserInfo userInfo) {
        return userInfoRepository.insert(userInfo);
    }

    @PostMapping(value = "/get/{userId}")
    public UserInfo get(@PathVariable Long userId) {
        return userInfoRepository.findOne(userId);
    }

    @PostMapping(value = "/delete/{userId}")
    public String delete(@PathVariable Long userId) {
        userInfoRepository.delete(userId);
        return SUCCESS;
    }

    @PostMapping(value = "/findByAccount")
    public List<UserInfo> findByAccount(String account) {
        return userInfoRepository.findByAccount(account);
    }

    @PostMapping(value = "/findAll")
    public List<UserInfo> findAll(PageQuery page) {
        return userInfoRepository.findAll();
    }

    @PostMapping(value = "/findByPage")
    public Page<UserInfo> findByPage(PageQuery page,UserInfo userInfo) {
        Sort sort = new Sort("userId");
        Pageable pageable = new PageRequest(page.getPageNum()-1, page.getPageSize(),sort);
        return userInfoRepository.findAll(Example.of(userInfo),pageable);
    }

    @PostMapping(value = "/findAccountGroups")
    public List<UserInfo> findGroupCount(PageQuery page) {

        // 返回的字段
        ProjectionOperation projectionOperation = Aggregation.project("account","accountNum");

        // 分组操作，对每个分组总条数进行统计
        GroupOperation groupOperation = Aggregation.group("account").count().as("accountNum");
//        // 分页操作，控制分页从哪开始
//        SkipOperation skipOperation = Aggregation.skip(page.getStartRow());
//        // 分页操作，控制分页取得记录数
//        LimitOperation limitOperation = Aggregation.limit(page.getPageSize());
//        // 组合条件
//        Aggregation aggregation = Aggregation.newAggregation(projectionOperation, groupOperation, skipOperation, limitOperation);

//        AggregationOperation aggregationOperation =Aggregation.match( Criteria.where("account").is("1222333"));
        // 组合条件
        Aggregation aggregation = Aggregation.newAggregation(projectionOperation, groupOperation );
        // 执行操作
        AggregationResults<UserInfo> aggregationResults = mongoTemplate.aggregate(aggregation, mongoTemplate.getCollectionName(UserInfo.class), UserInfo.class);

        return aggregationResults.getMappedResults();

    }

}
