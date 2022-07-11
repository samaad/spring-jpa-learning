package org.bee.jpalearngin.BookCatelog.service;

import java.util.List;
import org.bee.jpalearngin.BookCatelog.dto.requestDto.AuthorRequestDTO;
import org.bee.jpalearngin.BookCatelog.dto.response.AuthorResponseDTO;
import org.bee.jpalearngin.BookCatelog.entitt.Author;
import org.springframework.stereotype.Service;

@Service
public interface AuthorService {

    public AuthorResponseDTO addAuthor(AuthorRequestDTO authorRequestDTO);
    public List<AuthorResponseDTO> getAuthors();
    public AuthorResponseDTO getAuthorById(Long authorId);
    public Author getAuthor(Long authorId);
    public AuthorResponseDTO deleteAuthor(Long authorId);
    public AuthorResponseDTO editAuthor(Long authorId, AuthorRequestDTO authorRequestDTO);
    public AuthorResponseDTO addZipcodeToAuthor(Long authorId, Long zipcode);
    public AuthorResponseDTO deleteZipcodeFromAuthor(Long authorId);
}
