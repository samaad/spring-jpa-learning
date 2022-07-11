package org.bee.jpalearngin.BookCatelog.dto.response;

import java.util.List;
import lombok.Data;

@Data
public class BookResponseDTO {

    private Long id;
    private String       name;
    private List<String> authorName;
    private String categoryName;
}
