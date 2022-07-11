package org.bee.jpalearngin.BookCatelog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.bee.jpalearngin.BookCatelog.dto.Mapper;
import org.bee.jpalearngin.BookCatelog.dto.requestDto.AuthorRequestDTO;
import org.bee.jpalearngin.BookCatelog.dto.response.AuthorResponseDTO;
import org.bee.jpalearngin.BookCatelog.entitt.Author;
import org.bee.jpalearngin.BookCatelog.entitt.Zipcode;
import org.bee.jpalearngin.BookCatelog.repository.AuthorReposity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorReposity authorReposity;
    private final ZipcodeService zipcodeService;

    @Transactional
    @Override public AuthorResponseDTO addAuthor(AuthorRequestDTO authorRequestDTO) {
        Author author = new Author();
        author.setName(authorRequestDTO.getName());
        if(authorRequestDTO.getZipCodeId() == null){
            throw new IllegalArgumentException("author need a zipcode");
        }
        Zipcode zipcode = zipcodeService.getZipcode(authorRequestDTO.getZipCodeId());
        author.setZipcode(zipcode);
        authorReposity.save(author);
        return Mapper.authorToAuthorResponseDTO(author);
    }

    @Override public List<AuthorResponseDTO> getAuthors() {
        List<Author> authors = new ArrayList<>(authorReposity.findAll());
        return Mapper.authorsToAuthorResponseDTOs(authors);
    }

    @Override public AuthorResponseDTO getAuthorById(Long authorId) {
        return Mapper.authorToAuthorResponseDTO(getAuthor(authorId));
    }

    @Override public Author getAuthor(Long authorId) {
        return authorReposity.findById(authorId)
            .orElseThrow(() -> new IllegalArgumentException("author with id: " + authorId +" could not be found"));

    }

    @Override public AuthorResponseDTO deleteAuthor(Long authorId) {
        Author author = getAuthor(authorId);
        authorReposity.delete(author);
        return Mapper.authorToAuthorResponseDTO(author);
    }

    @Transactional
    @Override public AuthorResponseDTO editAuthor(Long authorId, AuthorRequestDTO authorRequestDTO) {
        Author author = getAuthor(authorId);
        author.setName(authorRequestDTO.getName());
        if(authorRequestDTO.getZipCodeId() != null){
            Zipcode zipcode = zipcodeService.getZipcode(authorRequestDTO.getZipCodeId());
            author.setZipcode(zipcode);
        }
        return Mapper.authorToAuthorResponseDTO(author);
    }

    @Transactional
    @Override public AuthorResponseDTO addZipcodeToAuthor(Long authorId, Long zipcodeId) {
        Author author = getAuthor(authorId);
        Zipcode zipcode = zipcodeService.getZipcode(zipcodeId);
        if(Objects.nonNull(author.getZipcode())){
            throw new RuntimeException("author already has zipcode");
        }
        author.setZipcode(zipcode);
        return Mapper.authorToAuthorResponseDTO(author);
    }

    @Transactional
    @Override public AuthorResponseDTO deleteZipcodeFromAuthor(Long authorId) {
        Author author = getAuthor(authorId);
        author.setZipcode(null);
        return Mapper.authorToAuthorResponseDTO(author);
    }
}
