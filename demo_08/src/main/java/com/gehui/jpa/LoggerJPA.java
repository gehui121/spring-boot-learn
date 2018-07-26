package com.gehui.jpa;

import com.gehui.entity.LoggerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/7/26 11:10.
 **/
public interface LoggerJPA extends
        JpaRepository<LoggerEntity,Long>,
        JpaSpecificationExecutor<LoggerEntity>,
        Serializable {
}
