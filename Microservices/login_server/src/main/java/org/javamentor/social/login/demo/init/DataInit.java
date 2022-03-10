package org.javamentor.social.login.demo.init;

import org.javamentor.social.login.demo.dao.RoleDao;
import org.javamentor.social.login.demo.model.Account;
import org.javamentor.social.login.demo.model.Role;
import org.javamentor.social.login.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Transactional
public class DataInit {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder ;

    @Autowired
    private AccountService accountService;

    Role ROLE_ADMIN = new Role("ROLE_ADMIN");
    Role ROLE_USER = new Role("ROLE_USER");


    @PostConstruct
    public void dataInit(){
        roleDao.saveAndFlush(ROLE_ADMIN);
        roleDao.saveAndFlush(ROLE_USER);
        for (int i = 1; i <= 20; i++) {
            createAccount(i);
        }
    }

    private void createAccount(int i) {

        Account account = new Account();
        account.setEmail("test" + i + "@mail.ru");
        if (i % 2 == 0) account.setRole(ROLE_USER);
        else account.setRole(ROLE_ADMIN);
        account.setPassword(passwordEncoder.encode("pass" + i));
        account.setEnable(true);
        accountService.save(account);
    }

}
