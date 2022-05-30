package com.form.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.form.login.entity.BookEntity;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

	BookEntity findByBookname(String bookname);

//	"SELECT COUNT(u) FROM User u
	@Query("select count(b) from BookEntity b")
	public long numberOfRecords();
}
