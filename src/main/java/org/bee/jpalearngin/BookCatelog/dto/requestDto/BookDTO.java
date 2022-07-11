package org.bee.jpalearngin.BookCatelog.dto.requestDto;

import java.util.List;
import lombok.Data;

@Data
public class BookDTO {
    private String name;
    private List<Long> authorIds;
    private Long categoryId;
}
