package org.javamentor.social.friend_service;

import org.javamentor.social.friend_service.dao.FriendsRepository;
import org.javamentor.social.friend_service.exceptions.RelationshipDontExistException;
import org.javamentor.social.friend_service.model.Friends;
import org.javamentor.social.friend_service.model.Relationship;
import org.javamentor.social.friend_service.service.IFriendsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.Objects;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan({"org.javamentor.social.friend_service"})
public class JpaFriendsServiceTests {

    private final Long firstUserId = 125L;

    private final Long secondUserId = 126L;

    @Autowired
    private IFriendsService service;

    @Autowired
    private FriendsRepository friendsRepository;

    @Test
    void addFriends() {


        var friends = new Friends(firstUserId, secondUserId);
        service.save(friends);

        var savedFriends = service.findFriendsByUsersIds(firstUserId, secondUserId);

        assert savedFriends.getInvitingUserId().equals(firstUserId) && savedFriends.getInvitedUserId().equals(secondUserId);

    }

    @Test
    void deleteFriends() {


        var friends = new Friends(firstUserId, secondUserId);

        service.save(friends);

        service.deleteByUsersIds(firstUserId, secondUserId);




        assert service.findFriendsByUsersIds(firstUserId, secondUserId) == null;
    }

    @Test
    void acceptFriendsInvite() {

        var friends = new Friends(firstUserId, secondUserId);

        service.save(friends);

        service.acceptFriendsInvite(firstUserId, secondUserId);

        var savedFriends = service.findFriendsByUsersIds(firstUserId, secondUserId);

        assert Objects.equals(savedFriends.getStatus(), Relationship.ACCEPTED.toString());

    }
}
