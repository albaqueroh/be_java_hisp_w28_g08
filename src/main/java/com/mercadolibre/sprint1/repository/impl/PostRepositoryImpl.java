package com.mercadolibre.sprint1.repository.impl;

import com.mercadolibre.sprint1.entity.Post;
import com.mercadolibre.sprint1.repository.IRepository;
import com.mercadolibre.sprint1.utils.CResourceUtils;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepositoryImpl implements IRepository<Post> {

	private List<Post> posts = new ArrayList<>();

	public PostRepositoryImpl() {
		loadData();
	}

	private void loadData() {
		try {
			posts = CResourceUtils.loadDataFromJson("post.json");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Post> findAll() {
		return posts;
	}

	@Override
	public Post save(Post entity) {
		entity.setId(posts.size()+1);
		posts.add(entity);
		return entity;
	}

	@Override
	public boolean delete(int id) {
		Optional<Post> entity = posts.stream()
				.filter(p -> p.getId() == id)
				.findFirst();

		if (entity.isEmpty()) {
			return false;
		}

		return delete(entity.get());
	}

	@Override
	public boolean delete(Post entity) {
		return posts.remove(entity);
	}
}
