package com.gehui.jpa;

import com.gehui.entity.GoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * Created by Administrator on 2018/8/9 12:57.
 **/
public interface GoodJPA extends
        JpaRepository<GoodEntity,Long>,
        QuerydslPredicateExecutor<GoodEntity> {
}
