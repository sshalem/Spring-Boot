package com.biDirec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biDirec.entity.Post;
import com.biDirec.entity.User;

public interface PostRepository extends JpaRepository<Post, Integer> {

	/**
	 * SOMETHING TO KNOW:
	 * ------------------ 
	 * We can use in PostRepository, finder methods of fields from 
	 * User Class even though we are in PostRepository, we Just need to add the prefix
	 * of the field to the finder method: findByUserXXXX where XXXX- is the field from User Class.
	 * User Class has the following fields : id, firstname, lastname, email
	 * 
	 * These finder methods return List<Post> while in UserRepository they return
	 * User object.
	 * 
	 * The Finder methods are Just like the finder methods of UserRepository just
	 * placed here in PostRepository Thus finder methods can be: List<Post>
	 * findByUserId(Integer id); List<Post> findByUserFirstname(String firstname);
	 * List<Post> findByUserLastname(String lastname); List<Post>
	 * findByUserEmail(String email);
	 */

	List<Post> findByUserId(Integer id);

	List<Post> findByUserFirstname(String firstname);

	List<Post> findByUserLastname(String lastname);

	List<Post> findByUserEmail(String email);

	/**
	 * using @Query annotation with JPQL
	 */

	/*
	 * this SQL queries only in Post Entity We need to have a User object in order
	 * to get all the Posts of the specific User. 
	 * We will get compile error if we query by "posts.user.id"
	 */
	@Query("SELECT post FROM Post post WHERE post.user=:user")
	List<Post> getAllPostsByUserObject(@Param("user") User user);

}
