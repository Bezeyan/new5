package org.javamentor.social.login.demo.dao;

import org.javamentor.social.login.demo.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Optional;


@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    @Override
    @NotNull
    Optional<RefreshToken> findById(Long id);
    @NotNull
    Optional<RefreshToken> findByRefreshTokenContent(String token);

}
