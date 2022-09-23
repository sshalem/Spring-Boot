package com.swagger2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.swagger2.entity.Author;
import com.swagger2.entity.Book;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

	Author findByFirstName(String firstname);

	Author findByLastName(String firstname);

	Author findByEmail(String eamil);

	@Query("SELECT books FROM Author au JOIN au.booksList AS books WHERE au.firstName=:fName")
	List<Book> getAuthorBookList(@Param("fName") String firstname);
}
