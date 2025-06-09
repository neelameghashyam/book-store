package com.thinkconstructive.book_store.repository;

import com.thinkconstructive.book_store.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface BookRepository extends MongoRepository<Book, String> {

    @Query("{ 'bookId' : ?0 }")
    Book findBookByBookId(String bookId);

}