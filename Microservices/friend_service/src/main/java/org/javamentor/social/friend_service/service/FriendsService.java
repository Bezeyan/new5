package org.javamentor.social.friend_service.service;

import org.javamentor.social.friend_service.dao.FriendsRepository;
import org.javamentor.social.friend_service.exceptions.RelationshipAlreadyExistException;
import org.javamentor.social.friend_service.exceptions.RelationshipDontExistException;
import org.javamentor.social.friend_service.model.Friends;
import org.javamentor.social.friend_service.model.Relationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendsService implements IFriendsService {

    @Autowired
    private FriendsRepository friendsRepository;


    @Override
    public void save(final Friends friends) {
        var invitingUserId = friends.getInvitingUserId();
        var invitedUserId = friends.getInvitedUserId();
        if (findFriendsByUsersIds(invitingUserId, invitedUserId) == null) {
            friendsRepository.saveNative(friends);
            friends.setId(findFriendsIdByUsersIds(invitingUserId, invitedUserId));
            return;
        }
        throw new RelationshipAlreadyExistException("Relationship between user with Id " + invitingUserId +
                " and user with Id " + invitedUserId + " already exists");
    }

    @Override
    public Friends findFriendsByUsersIds(final long firstUserId, final long secondUserId) {
        var relationship = friendsRepository.findByInvitingUserIdAndInvitedUserId(firstUserId, secondUserId);

        if (relationship == null) {
            relationship = friendsRepository.findByInvitingUserIdAndInvitedUserId(secondUserId, firstUserId);
        }
        return relationship;
    }

    @Override
    public Long findFriendsIdByUsersIds(final long firstUserId, final long secondUserId) {
        var relationship = findFriendsByUsersIds(firstUserId, secondUserId);

        if (relationship != null) {
            return relationship.getId();
        }

        return null;
    }

    @Override
    public void deleteByFriendsId(final long friendsId) {

        friendsRepository.deleteById(friendsId);
    }

    @Override
    public void deleteByUsersIds(final long firstUserId, final long secondUserId) {
        Long friendsId = findFriendsIdByUsersIds(firstUserId, secondUserId);

        if (friendsId != null) {
            deleteByFriendsId(friendsId);
            return;
        }

        throw new RelationshipDontExistException("Relationship between user with Id " + firstUserId +
                " and user with Id " + secondUserId + " dont exists");
    }

    @Override
    public List<Friends> getFriends() {
        return friendsRepository.findAll();
    }

    @Override
    public void acceptFriendsInvite(final long firstUserId, final long secondUserId) {

        var friends = findFriendsByUsersIds(firstUserId, secondUserId);
        friends.setStatus(Relationship.ACCEPTED.toString());
        friendsRepository.saveAndFlush(friends);
    }

}
