package com.thinkconstructive.book_store.service.impl;

import com.thinkconstructive.book_store.dto.BookDto;
import com.thinkconstructive.book_store.entity.Book;
import com.thinkconstructive.book_store.mapper.BookMapper;
import com.thinkconstructive.book_store.repository.BookRepository;
import com.thinkconstructive.book_store.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDto getBook(String bookId) {
        Book book = bookRepository.findBookByBookId(bookId);
        if (book == null) {
            throw new RuntimeException("Book not found with ID: " + bookId);
        }
        return BookMapper.toDto(book);
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(BookMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        Book book = BookMapper.toEntity(bookDto);
        Book savedBook = bookRepository.save(book);
        return BookMapper.toDto(savedBook);
    }

    @Override
    public BookDto updateBookName(BookDto bookDto) {
        Book book = bookRepository.findBookByBookId(bookDto.bookId());
        if (book == null) {
            throw new RuntimeException("Book not found with ID: " + bookDto.bookId());
        }
        Book updatedBook = new Book(
            bookDto.bookId(),
            bookDto.name(),
            book.price(),  // Preserve original price
            book.author(), // Preserve original author
            book.description() // Preserve original description
        );
        bookRepository.save(updatedBook);
        return BookMapper.toDto(updatedBook);
    }

    @Override
    public void deleteBookByBookId(String bookId) {
        bookRepository.deleteById(bookId);
    }
}