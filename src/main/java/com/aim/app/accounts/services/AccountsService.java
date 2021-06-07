package com.aim.app.accounts.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aim.app.accounts.models.User;

@Service
public interface AccountsService {

	public List<User> getAccounts();

	public User getUserByEmail(String email);

	public User save(User user);

	public User getUserById(Long id);

	public void deleteUser(User user);

}
