package com.gehui.jpa;

import com.gehui.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2018/10/13 14:07.
 **/
@Repository
public interface BookJPA extends JpaRepository<BookEntity,Long> {
}
