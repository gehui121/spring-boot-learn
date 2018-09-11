package com.gehui.jpa;

import com.gehui.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2018/8/22 9:46.
 **/
public interface AuthorityJPA extends JpaRepository<Authority,String> {
}
