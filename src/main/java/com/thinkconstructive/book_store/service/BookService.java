package com.thinkconstructive.book_store.service;

import com.thinkconstructive.book_store.dto.BookDto;
import java.util.List;

public interface BookService {
    BookDto getBook(String bookId);
    List<BookDto> getAllBooks();
    BookDto createBook(BookDto bookDto);
    BookDto updateBookName(BookDto bookDto);
    void deleteBookByBookId(String bookId);
}