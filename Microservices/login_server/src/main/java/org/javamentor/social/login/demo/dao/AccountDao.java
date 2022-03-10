package org.javamentor.social.login.demo.dao;

import org.javamentor.social.login.demo.model.Account;
import org.javamentor.social.login.demo.model.dto.AccountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDao extends JpaRepository<Account, Long> {

    Account findByEmail(String email);

    @Query("select new org.javamentor.social.login.demo.model.dto.AccountDTO(" +
            "a.status)" +
            "from Account a where a.id = :userId")
    AccountDTO findAccountDtoById(@Param("userId") Long userId);
}
