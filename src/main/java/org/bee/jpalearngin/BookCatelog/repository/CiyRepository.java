package org.bee.jpalearngin.BookCatelog.repository;

import org.bee.jpalearngin.BookCatelog.entitt.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CiyRepository extends JpaRepository<City, Long> {
}
