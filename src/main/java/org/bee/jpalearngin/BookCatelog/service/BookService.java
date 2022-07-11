package org.bee.jpalearngin.BookCatelog.service;

import java.util.List;
import org.bee.jpalearngin.BookCatelog.dto.requestDto.BookDTO;
import org.bee.jpalearngin.BookCatelog.dto.response.BookResponseDTO;
import org.bee.jpalearngin.BookCatelog.entitt.Book;
import org.springframework.stereotype.Service;

@Service
public interface BookService {
    public BookResponseDTO addBook(BookDTO bookDTO);
    public BookResponseDTO getBookById(Long bokId);
    public List<BookResponseDTO> getBooks();
    public Book getBook(Long bokId);
    public BookResponseDTO deleteBook(Long bokId);
    public BookResponseDTO editBook(Long bokId, BookDTO bookDTO);
    public BookResponseDTO addAuthorToBook(Long bookId, Long authorId);
    public BookResponseDTO deleteAuthorFromBook(Long bookId, Long authorId);
    public BookResponseDTO addCategoryToBook(Long bookId, Long categoryId);
    public BookResponseDTO deleteCategoryFromBook(Long bookId, Long categoryId);
}
