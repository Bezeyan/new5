package org.javamentor.social.login.demo.dao;

import org.javamentor.social.login.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
    Role getByRoleName(String roleName);
}
