package com.biDirec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biDirec.entity.Post;
import com.biDirec.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	/*
	 * Finder methods by field name
	 */

	User findByEmail(String email);

	User findByFirstname(String firstname);

	User findByLastname(String lastname);

	/**
	 * using @Query annotation with JPQL
	 */

	@Query("SELECT u FROM User u")
	List<User> queryGetAllUsers();

//	@Query("SELECT u FROM User u WHERE u.firstname like %?1%")
//	List<User> queryOfFindByFirstname(String firstname);

	@Query("SELECT user FROM User user WHERE user.firstname=:name")
	User queryByFirstName(@Param("name") String name);

//	@Query("SELECT user FROM User user WHERE user.firstname LIKE %?1%")
//	List<User> queryOffindByFirstnameContains(String name);

	@Query("SELECT user FROM User user WHERE user.firstname LIKE %:name%")
	List<User> queryByFirstnameContains(@Param("name") String name);

	/*
	 * Here I'm returning list of posts (not users) Instead I could use the
	 * following code in PostRepository which will give the same results
	 * 
	 */
	@Query("SELECT post FROM User user JOIN user.posts AS post WHERE user.id=:id")
	List<Post> queryOfGetAllPostsByUserId(@Param("id") int id);
	
	@Query("SELECT post FROM User user JOIN user.posts AS post WHERE user.firstname=:firstname")
	List<Post> queryOfGetAllPostsByFirstname(@Param("firstname") String firstname);
	
	@Query("SELECT post FROM User user JOIN user.posts AS post WHERE user.email=:email")
	List<Post> queryOfGetAllPostsByEmail(@Param("email") String email);

	/**
	 * @Query("SELECT user FROM User user JOIN user.posts AS post WHERE post.details LIKE %?1%") 
	 * List<User> findUserThatContainsInPostDetails(String details);
	 */

	@Query("SELECT user FROM User user JOIN user.posts AS post WHERE post.details LIKE %:details%")
	List<User> queryByUserThatContainsInPostDetails(@Param("details") String details);

	/**
	 * @Query("SELECT up FROM User u JOIN u.posts AS up WHERE u.id=:id AND up.details LIKE %:details%")
	 * List<Post> queryByUserIdAndPostsDetails(@Param("id") int id, @Param("details") String details);
	 * 
	 * @Query("SELECT cp FROM COMPANY comp JOIN comp.coupons AS cp WHERE comp.id=:id AND cp.type=:couponType")
	 * List<Coupon> findCompanyCouponByType(@Param("id") long compId, @Param("couponType") CouponType type);
	 */

}
