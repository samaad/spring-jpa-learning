package org.bee.jpalearngin.BookCatelog.repository;

import org.bee.jpalearngin.BookCatelog.entitt.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
