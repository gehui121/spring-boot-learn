package com.gehui.entity.jpa;

import com.gehui.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2018/9/12 14:37.
 **/
public interface TokenJPA extends JpaRepository<TokenEntity,Long>,JpaSpecificationExecutor<TokenEntity> {
}
