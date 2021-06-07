package com.aim.app.accounts;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.aim.app.accounts.models.User;
import com.aim.app.accounts.repositories.UserRepository;
import com.aim.app.accounts.services.AccountsService;
import com.aim.app.accounts.services.AccountsServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class AccountsServiceTests {

	@TestConfiguration
	static class AccountsServiceImplTestContextConfiguration {
		@Bean
		public AccountsService accountsService() {
			return new AccountsServiceImpl();
		}
	}

	@MockBean
	private UserRepository userRepository;

	@MockBean
	private PasswordEncoder encoder;

	@Autowired
	private AccountsService accountsService;

	@Before
	public void setUp() {
		User user = new User();
		user.setEmail("test1user@test.com");
		user.setId(Long.valueOf(1));
		user.setFirstName("Test1");
		user.setLastName("Test1LN");
		user.setPassword(encoder.encode("sasa"));
		user.setPhoneNumber("2546780967678");

		User user2 = new User();
		user2.setId(Long.valueOf(2));
		user2.setEmail("test2user@test.com");
		user2.setFirstName("Test1");
		user2.setLastName("Test1LN");
		user2.setPassword(encoder.encode("sasa"));
		user2.setPhoneNumber("2546780967678");

		List<User> users = new ArrayList<>();
		users.add(user2);
		users.add(user);

		Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
		Mockito.when(userRepository.findByEmail(user2.getEmail())).thenReturn(Optional.of(user2));

		Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
		Mockito.when(userRepository.findById(user2.getId())).thenReturn(Optional.of(user2));

		Mockito.when(userRepository.findAll()).thenReturn(users);

		Mockito.when(userRepository.save(user)).thenReturn(user);
		Mockito.when(userRepository.save(user2)).thenReturn(user2);

	}

	@Test
	public void whenValidEmail_thenUserShouldBeFound() {

		String email = "test1user@test.com";
		User found = accountsService.getUserByEmail(email);

		assertThat(found.getEmail()).isEqualTo(email);
	}

	@Test
	public void whenValidId_thenUserShoudBeFound() {
		long id = 1;
		User found = accountsService.getUserById(id);
		assertThat(found.getId()).isEqualTo(id);
	}

	@Test
	public void whenGetAccounts_thenUsersList() {
		assertThat(accountsService.getAccounts().size()).isEqualTo(2);
	}

	@Test
	public void whenAddAccount_thenAccountAdded() {

		User user = new User();
		user.setEmail("test1user@test.com");
		user.setId(Long.valueOf(1));
		user.setFirstName("Test1");
		user.setLastName("Test1LN");
		user.setPassword(encoder.encode("sasa"));
		user.setPhoneNumber("2546780967678");

//		assertThat(accountsService.save(user).getEmail()).isEqualTo(user.getEmail());
	}

}
