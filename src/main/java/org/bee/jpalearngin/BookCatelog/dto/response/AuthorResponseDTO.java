package org.bee.jpalearngin.BookCatelog.dto.response;

import java.util.List;
import lombok.Data;

@Data
public class AuthorResponseDTO {
    private Long id;
    private String name;
    private List<String> bookNames;
    private String zipCodeName;
}
