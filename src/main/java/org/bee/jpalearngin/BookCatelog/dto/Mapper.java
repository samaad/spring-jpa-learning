package org.bee.jpalearngin.BookCatelog.dto;

import java.util.ArrayList;
import java.util.List;
import org.bee.jpalearngin.BookCatelog.dto.response.AuthorResponseDTO;
import org.bee.jpalearngin.BookCatelog.dto.response.BookResponseDTO;
import org.bee.jpalearngin.BookCatelog.dto.response.CategoryResponseDTO;
import org.bee.jpalearngin.BookCatelog.entitt.Author;
import org.bee.jpalearngin.BookCatelog.entitt.Book;
import org.bee.jpalearngin.BookCatelog.entitt.Category;

public class Mapper {

    public static BookResponseDTO bookToBookResponseDTO(Book book){
        BookResponseDTO response = new BookResponseDTO();
        response.setId(book.getId());
        response.setCategoryName(book.getCategory().getName());
        List<String> names = new ArrayList<>();
        for (Author author : book.getAuthors()){
            names.add(author.getName());
        }

        response.setAuthorName(names);
        return response;
    }

    public static List<BookResponseDTO> booksToBookResponseDTOs(List<Book> books){
        List<BookResponseDTO> bookResponseDTOS = new ArrayList<>();
        for(Book book : books){
            bookResponseDTOS.add(bookToBookResponseDTO(book));
        }

        return bookResponseDTOS;
    }

    public static AuthorResponseDTO authorToAuthorResponseDTO(Author author){
        AuthorResponseDTO authorResponseDTO = new AuthorResponseDTO();
        authorResponseDTO.setId(author.getId());
        authorResponseDTO.setName(author.getName());
        List<String> names = new ArrayList<>();
        for (Book book : author.getBooks()){
            names.add(book.getName());
        }
        authorResponseDTO.setBookNames(names);
        return authorResponseDTO;
    }

    public static List<AuthorResponseDTO> authorsToAuthorResponseDTOs(List<Author> authors){
        List<AuthorResponseDTO> authorResponseDTOS = new ArrayList<>();
        for(Author author : authors){
            authorResponseDTOS.add(authorToAuthorResponseDTO(author));
        }
        return authorResponseDTOS;
    }

    public static CategoryResponseDTO categoryToCategoryResponseDTO(Category category){
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setId(category.getId());
        categoryResponseDTO.setName(category.getName());
        List<String> name = new ArrayList<>();
        for (Book book : category.getBooks()){
            name.add(book.getName());
        }
        categoryResponseDTO.setBookNames(name);
        return categoryResponseDTO;
    }

    public static List<CategoryResponseDTO> categoriesToCategoryResponseDTOs(List<Category> authors){
        List<CategoryResponseDTO> categoryResponseDTOS = new ArrayList<>();
        for(Category category  : authors){
            categoryResponseDTOS.add(categoryToCategoryResponseDTO(category));
        }
        return categoryResponseDTOS;
    }
}
