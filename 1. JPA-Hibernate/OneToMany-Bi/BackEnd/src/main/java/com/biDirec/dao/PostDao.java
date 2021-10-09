package com.biDirec.dao;

import java.util.List;
import java.util.Optional;

import com.biDirec.entity.Post;
import com.biDirec.entity.User;

public interface PostDao {

	public Optional<Post> getPostById(int id);

	List<Post> getAllPost();

	List<Post> getAllPostsByUserObject(User user);

	List<Post> findPostsByUserId(Integer id);

	List<Post> findPostsByUserFirstname(String firstname);

	List<Post> findPostsByUserLastname(String lastname);

	List<Post> findPostsByUserEmail(String email);
}
