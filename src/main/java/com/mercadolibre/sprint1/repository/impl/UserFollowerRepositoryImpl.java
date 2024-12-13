package com.mercadolibre.sprint1.repository.impl;

import com.mercadolibre.sprint1.entity.UserFollower;
import com.mercadolibre.sprint1.repository.IRepository;
import com.mercadolibre.sprint1.utils.CResourceUtils;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserFollowerRepositoryImpl implements IRepository<UserFollower> {

	private List<UserFollower> usersFollowers = new ArrayList<>();

	public UserFollowerRepositoryImpl() {
		loadData();
	}

	private void loadData() {
		try {
			usersFollowers = CResourceUtils.loadDataFromJson("userFollower.json");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<UserFollower> findAll() {
		return usersFollowers;
	}

	@Override
	public UserFollower save(UserFollower entity) {
		usersFollowers.add(entity);
		return entity;
	}

	@Override
	public boolean delete(int id) {
		return false;
	}

	@Override
	public boolean delete(UserFollower entity) {
		return false;
	}
}
