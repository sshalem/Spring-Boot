package com.biDirec.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biDirec.entity.Post;
import com.biDirec.entity.User;
import com.biDirec.repository.PostRepository;

@Service
public class PostDaoImpl implements PostDao {

	@Autowired
	private PostRepository postRepository;

	@Override
	public List<Post> getAllPost() {
		return postRepository.findAll();
	}

	@Override
	public Optional<Post> getPostById(int id) {
		return postRepository.findById(id);
	}

	@Override
	public List<Post> getAllPostsByUserObject(User user) {
		return postRepository.getAllPostsByUserObject(user);
	}

	@Override
	public List<Post> findPostsByUserId(Integer id) {
		return postRepository.findByUserId(id);
	}

	@Override
	public List<Post> findPostsByUserFirstname(String firstname) {
		return postRepository.findByUserFirstname(firstname);
	}

	@Override
	public List<Post> findPostsByUserLastname(String lastname) {
		return postRepository.findByUserLastname(lastname);
	}

	@Override
	public List<Post> findPostsByUserEmail(String email) {
		System.out.println(email);
		return postRepository.findByUserEmail(email);
	}

}
