package org.javamentor.social.friend_service.service;

import org.javamentor.social.friend_service.model.Friends;

import java.util.List;


public interface IFriendsService {


    void save(Friends relationship);

    Friends findFriendsByUsersIds(long firstUserId, long secondUserId);

    Long findFriendsIdByUsersIds(long firstUserId, long secondUserId);

    void deleteByFriendsId(long friendsId);

    void deleteByUsersIds(long firstUserId, long secondUserId);

    List<Friends> getFriends();

    void acceptFriendsInvite(long firstUserId, long secondUserId);
}
