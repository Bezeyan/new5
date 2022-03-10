package org.javamentor.social.login.demo.controller;


import org.javamentor.social.login.demo.exceptions.NoSuchUserException;
import org.javamentor.social.login.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/rest/data")
public class UserDataController {


    private final AccountService accountService;

    @Autowired
    public UserDataController(AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping("/email")
    public String getEmail( @RequestHeader("user_id") Long userId) {
        String email = accountService.getUserEmailByUserId(userId);
        if (email != null) {
            return email;

        } else {
            throw new NoSuchUserException("No account with id " + userId);
        }
    }


    @GetMapping("/status/{userId}")
    public String getStatus(@PathVariable("userId") Long userId) {
        return accountService.getStatusById(userId);
    }
}