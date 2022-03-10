package org.javamentor.social.friend_service.controller;

import org.javamentor.social.friend_service.model.Friends;
import org.javamentor.social.friend_service.service.IFriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/rest/friend")
public class FriendsController {

    @Autowired
    private IFriendsService friendRelationshipService;

    @GetMapping("/friends")
    public List<Friends> getFriends() {
        return friendRelationshipService.getFriends();
    }

    @PostMapping("/save")
    public void saveFriends(@RequestBody Long invitingUserId,
                            @RequestBody Long invitedUserId,
                            @RequestHeader("user_id") String userId) {

        var friendRelationship = new Friends(invitingUserId, invitedUserId);
        friendRelationshipService.save(friendRelationship);
    }

    @PostMapping("/delete")
    public String deleteFriends(@RequestBody Long firstUserId,
                                @RequestBody Long secondUserId,
                                @RequestHeader("user_id") String userId) {
        friendRelationshipService.deleteByUsersIds(firstUserId, secondUserId);
        return "Relationship between user with id " + firstUserId + " and user with id " + secondUserId + " deleted";
    }

    @PostMapping("/accept")
    public String acceptFriendsInvite(@RequestBody Long firstUserId,
                                      @RequestBody Long secondUserId,
                                      @RequestHeader("user_id") String userId) {
        friendRelationshipService.acceptFriendsInvite(firstUserId, secondUserId);
        return "Relationship between user with id " + firstUserId + " and user with id " + secondUserId + " accepted";
    }
}