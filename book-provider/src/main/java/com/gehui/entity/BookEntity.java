package com.gehui.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2018/10/13 13:59.
 **/
@Entity
@Table(name = "tb_book")
public class BookEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;
    @Column(name = "book_author")
    private String bookAuthor;
    @Column(name = "book_name")
    private String bookName;
    @Column(name = "book_price")
    private double bookPrice;
    @Column(name = "book_date")
    private Date bookDate;

    public BookEntity() {
    }

    public BookEntity(String bookAuthor, String bookName, double bookPrice, Date bookDate) {
        this.bookAuthor = bookAuthor;
        this.bookName = bookName;
        this.bookPrice = bookPrice;
        this.bookDate = bookDate;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public Date getBookDate() {
        return bookDate;
    }

    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "bookId=" + bookId +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookPrice=" + bookPrice +
                ", bookDate=" + bookDate +
                '}';
    }
}
