package com.aim.app.accounts.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aim.app.accounts.models.User;
import com.aim.app.accounts.repositories.UserRepository;

@Service
public class AccountsServiceImpl implements AccountsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getAccounts() {
		return userRepository.findAll();
	}

	@Override
	public User getUserByEmail(String email) {
		Optional<User> user = userRepository.findByEmail(email);

		if (!user.isPresent()) {
			return null;
		}
		return user.get();
	}

	@Override
	public User save(User user) {
		User newAccount = userRepository.save(user);
		return newAccount;
	}

	@Override
	public User getUserById(Long id) {
		Optional<User> user = userRepository.findById(id);

		return user.get();
	}

	@Override
	public void deleteUser(User user) {
		userRepository.delete(user);
	}
}
