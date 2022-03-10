package org.javamentor.social.friend_service.init;

import org.javamentor.social.friend_service.model.Friends;
import org.javamentor.social.friend_service.model.Relationship;
import org.javamentor.social.friend_service.service.IFriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Transactional
public class DataInit {


    @Autowired
    private IFriendsService friendRelationshipService;


    private final Relationship wait = Relationship.WAIT;
    private final Relationship accepted = Relationship.ACCEPTED;

    @PostConstruct
    public void dataInit(){


        for (int i = 1; i <= 20; i++) {
            createFriends(i);
        }
    }

    private void createFriends(int i) {

        Friends friends = new Friends();
        friends.setInvitingUserId((long) i);
        friends.setInvitedUserId((long) i + 1);

        if (i % 2 == 0) friends.setStatus(wait.toString());
        else friends.setStatus(accepted.toString());
        friendRelationshipService.save(friends);
    }

}
