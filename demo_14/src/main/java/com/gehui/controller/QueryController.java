package com.gehui.controller;

import com.gehui.Inquirer;
import com.gehui.entity.GoodEntity;
import com.gehui.entity.QGoodEntity;
import com.gehui.jpa.GoodJPA;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Administrator on 2018/8/9 12:56.
 **/
@RestController
public class QueryController {

    @Autowired
    private GoodJPA goodJPA;
    //注入EntityManager
    @PersistenceContext
    private EntityManager entityManager;


    @RequestMapping(value = "/query")
    public List<GoodEntity> lsit(){
        //创建query查询实体
        QGoodEntity goodEntity = QGoodEntity.goodEntity;
        //构建jpa查询对象
        JPAQuery<GoodEntity> jpaQuery = new JPAQuery<>(entityManager);
        //返回查询接口,查询类型ID为1的所有商品
        return jpaQuery
                //查询字段
        .select(goodEntity)
                //查询表
        .from(goodEntity)
                //查询条件
        .where(goodEntity.type.id.eq(Long.valueOf("1")))
                //返回结果
                .fetch();
    }

    /**
     * spring data jpa整合querydsl完成查询
     * @return
     */
    @RequestMapping(value = "/join")
    public List<GoodEntity> join(){
        //querydsl查询实体
        QGoodEntity qGoodEntity = QGoodEntity.goodEntity;
        //查询条件
//        BooleanExpression expression = qGoodEntity.type.id.eq(Long.valueOf("1"));
//        //执行查询
//        Iterator<GoodEntity> iterator = goodJPA.findAll(expression).iterator();
//        List<GoodEntity> list = new ArrayList<>();
//        //转换成list
//        while (iterator.hasNext()){
//            list.add(iterator.next());
//        }
        //自定义查询对象
        Inquirer inquirer = new Inquirer();
        //添加查询条件
       inquirer.putExpression(qGoodEntity.type.id.eq(Long.valueOf("1")));
       //返回查询结果
        List<GoodEntity> list = inquirer.iteratorToList(goodJPA.findAll(inquirer.buidleQuery()));
        return list;
    }
}
