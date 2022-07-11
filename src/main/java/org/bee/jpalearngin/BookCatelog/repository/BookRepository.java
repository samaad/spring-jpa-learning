package org.bee.jpalearngin.BookCatelog.repository;

import org.bee.jpalearngin.BookCatelog.entitt.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
