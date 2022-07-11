package org.bee.jpalearngin.BookCatelog.dto.response;

import java.util.List;
import lombok.Data;

@Data
public class CategoryResponseDTO {
    private Long id;
    private String name;
    private List<String> bookNames;
}
