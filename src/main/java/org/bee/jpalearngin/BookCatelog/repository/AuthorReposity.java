package org.bee.jpalearngin.BookCatelog.repository;

import org.bee.jpalearngin.BookCatelog.entitt.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorReposity extends JpaRepository<Author, Long> {
}
