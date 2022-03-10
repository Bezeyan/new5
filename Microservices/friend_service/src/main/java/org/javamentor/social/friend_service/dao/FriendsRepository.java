package org.javamentor.social.friend_service.dao;

import org.javamentor.social.friend_service.model.Friends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface FriendsRepository extends JpaRepository<Friends, Long> {

    Friends findByInvitingUserIdAndInvitedUserId(long invitingUserId, long invitedUserId);

    @Modifying
    @Transactional
    @Query(value = "insert into friends (inviting_user_id, invited_user_id, relationship_status) "
            + "VALUES (:#{#s.getInvitingUserId()},:#{#s.getInvitedUserId()},  :#{#s.getStatus()})", nativeQuery = true)
    <S extends Friends> void saveNative(@Param("s") S s);

}