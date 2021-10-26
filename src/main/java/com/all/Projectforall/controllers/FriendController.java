package com.all.Projectforall.controllers;

import com.all.Projectforall.auth.model.Authusermodel;
import com.all.Projectforall.entitys.Cart;
import com.all.Projectforall.entitys.Friend;
import com.all.Projectforall.models.CartModel;
import com.all.Projectforall.models.FriendModel;
import com.all.Projectforall.services.CartService;
import com.all.Projectforall.services.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class FriendController {

    @Autowired
    private FriendService f_service;

   /* @RequestMapping("/")
    public CompletableFuture< String aa() { return "allah"; }*/

    @GetMapping("/friend/{user}/{name}")
    public CompletableFuture<ResponseEntity<FriendModel>> getFriendByUserAndFriendName(@PathVariable(value = "user") String user,
                                                                                       @PathVariable(value = "name") String friendName) {
        CompletableFuture<FriendModel> friend = f_service.getFriendByUserNameAndFriendName(user, friendName);
        return friend.thenApply(ResponseEntity::ok);
    }

    @GetMapping("/friend/{user}")
    public CompletableFuture<ResponseEntity<List<Authusermodel>>> getAllUserFriends(@PathVariable(value = "user") String user) {
        return f_service.allUserFriends(user).thenApply(ResponseEntity::ok);
    }
    @GetMapping("/friend/common/{user}/{visitor}")
    public CompletableFuture<ResponseEntity<List<Authusermodel >>> getCommonFriends(@PathVariable(value = "user") String user,
                                                                                   @PathVariable(value = "visitor") String visitor)
                                                                                  throws ExecutionException, InterruptedException {
        return f_service.commonFriends(user,visitor).thenApply(ResponseEntity::ok);
    }

    @PostMapping("/friend")
    public CompletableFuture<FriendModel> createFriend(@Valid @RequestBody FriendModel friendModel) {
        return f_service.save(friendModel);
    }


    @DeleteMapping("/friend/{user}/{name}")
    public CompletableFuture<Map<String, Boolean>> deleteFriend(@PathVariable(value = "user") String user,
                                                               @PathVariable(value = "name") String name) {

        return f_service.deleteFriend(user, name);
    }

    @DeleteMapping("/friend/{user}")
    public CompletableFuture<Map<String, Boolean>> deleteUserFriends(@PathVariable(value = "user") String user) {

        return f_service.deleteAllUserFriends(user);
    }

}
