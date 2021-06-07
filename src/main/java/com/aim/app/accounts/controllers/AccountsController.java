package com.aim.app.accounts.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aim.app.accounts.models.User;
import com.aim.app.accounts.services.AccountsService;
import com.aim.app.utils.ApiResponse;

@Validated
@RestController
@RequestMapping("/api/v1/accounts")
public class AccountsController {

	private final AccountsService accountsService;

	@Autowired
	public AccountsController(AccountsService accountsService) {
		this.accountsService = accountsService;
	}

	@GetMapping
	public ResponseEntity<ApiResponse<User>> getAccounts() {

		ApiResponse<User> response = new ApiResponse<>();

		response.setStatus("1");
		response.setMessage("Success");
		response.setDataList(accountsService.getAccounts());
		return new ResponseEntity<ApiResponse<User>>(response, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ApiResponse<User>> addAccount(@RequestBody @Valid User user) {
		ApiResponse<User> response = new ApiResponse<>();
		User account = accountsService.save(user);

		if (account == null) {
			response.setStatus("-1");
			response.setMessage("Failed");
			return new ResponseEntity<ApiResponse<User>>(response, HttpStatus.BAD_REQUEST);
		}

		response.setStatus("1");
		response.setMessage("Success");
		return new ResponseEntity<ApiResponse<User>>(response, HttpStatus.OK);
	}
}
