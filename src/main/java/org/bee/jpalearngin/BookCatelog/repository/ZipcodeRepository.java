package org.bee.jpalearngin.BookCatelog.repository;

import org.bee.jpalearngin.BookCatelog.entitt.Zipcode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZipcodeRepository extends JpaRepository<Zipcode, Long> {
}
