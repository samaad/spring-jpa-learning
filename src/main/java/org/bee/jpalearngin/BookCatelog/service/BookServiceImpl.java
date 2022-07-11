package org.bee.jpalearngin.BookCatelog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.bee.jpalearngin.BookCatelog.dto.Mapper;
import org.bee.jpalearngin.BookCatelog.dto.requestDto.BookDTO;
import org.bee.jpalearngin.BookCatelog.dto.response.BookResponseDTO;
import org.bee.jpalearngin.BookCatelog.entitt.Author;
import org.bee.jpalearngin.BookCatelog.entitt.Book;
import org.bee.jpalearngin.BookCatelog.entitt.Category;
import org.bee.jpalearngin.BookCatelog.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService  authorService;
    private final CategoryService categoryService;

    @Transactional
    @Override public BookResponseDTO addBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setName(bookDTO.getName());
        if(bookDTO.getAuthorIds().isEmpty()){
            throw new IllegalArgumentException("you need atleast one author");
        }else{
            List<Author> authors = new ArrayList<>();
            for(Long authorId : bookDTO.getAuthorIds()){
                Author author = authorService.getAuthor(authorId);
                authors.add(author);
            }
            book.setAuthors(authors);
        }
        if(bookDTO.getCategoryId() == null){
            throw new IllegalArgumentException("book needs atleast one category");
        }

        Category category = categoryService.getCategory(bookDTO.getCategoryId());
        book.setCategory(category);

        Book book1 = bookRepository.save(book);
        return Mapper.bookToBookResponseDTO(book1);
    }

    @Override public BookResponseDTO getBookById(Long bokId) {
        Book book = getBook(bokId);
        return Mapper.bookToBookResponseDTO(book);
    }

    @Override public List<BookResponseDTO> getBooks() {
        List<Book> books = bookRepository.findAll();
        return Mapper.booksToBookResponseDTOs(books);
    }

    @Override public Book getBook(Long bokId) {
       return  bookRepository.findById(bokId).orElseThrow(() ->
            new IllegalArgumentException("cannot find book with id: "+ bokId)
        );
    }

    @Override public BookResponseDTO deleteBook(Long bokId) {
        Book book = getBook(bokId);
        bookRepository.delete(book);
        return Mapper.bookToBookResponseDTO(book);
    }

    @Transactional
    @Override public BookResponseDTO editBook(Long bokId, BookDTO bookDTO) {
        Book bookToEdit = getBook(bokId);
        bookToEdit.setName(bookDTO.getName());
        if(!bookDTO.getAuthorIds().isEmpty()){
            List<Author> authors = new ArrayList<>();
            for(Long authorId: bookDTO.getAuthorIds()){
                Author author = authorService.getAuthor(authorId);
                authors.add(author);
            }
            bookToEdit.setAuthors(authors);
        }

        if(bookDTO.getCategoryId() != null){
            Category category = categoryService.getCategory(bookDTO.getCategoryId());
            bookToEdit.setCategory(category);
        }

        return Mapper.bookToBookResponseDTO(bookToEdit);
    }

    @Override public BookResponseDTO addAuthorToBook(Long bookId, Long authorId) {
        Book book = getBook(bookId);
        Author author = authorService.getAuthor(authorId);
        if (author.getBooks().contains(author)) {
            throw new IllegalArgumentException("this author is already assigned to this book");
        }
        book.addAuthor(author);
        author.addBook(book);
        return Mapper.bookToBookResponseDTO(book);
    }

    @Override public BookResponseDTO deleteAuthorFromBook(Long bookId, Long authorId) {
        Book book = getBook(bookId);
        Author author = authorService.getAuthor(authorId);
        if (!(author.getBooks().contains(book))){
            throw new IllegalArgumentException("book does not have this author");
        }
        author.removeBooks(book);
        book.removeAuthor(author);
        return Mapper.bookToBookResponseDTO(book);
    }

    @Override public BookResponseDTO addCategoryToBook(Long bookId, Long categoryId) {
        Book book = getBook(bookId);
        Category category = categoryService.getCategory(categoryId);
        if (Objects.nonNull(book.getCategory())){
            throw new IllegalArgumentException("book already has a catogory");
        }
        book.setCategory(category);
        category.addBook(book);
        return Mapper.bookToBookResponseDTO(book);
    }

    @Override public BookResponseDTO deleteCategoryFromBook(Long bookId, Long categoryId) {
        Book book = getBook(bookId);
        Category category = categoryService.getCategory(categoryId);
        if (!(Objects.nonNull(book.getCategory()))){
            throw new IllegalArgumentException("book does not have a category to delete");
        }
        book.setCategory(null);
        category.removeBook(book);
        return Mapper.bookToBookResponseDTO(book);
    }
}
