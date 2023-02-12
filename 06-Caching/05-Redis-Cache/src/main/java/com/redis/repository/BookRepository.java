package com.redis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.redis.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	// This method will only Update the name
    @Transactional
    @Modifying
    @Query("update Book b set b.name=?2 where b.id=?1")
    int updateAddress(long id, String name);
    
    Book findBookByAuthor(String author);
}
