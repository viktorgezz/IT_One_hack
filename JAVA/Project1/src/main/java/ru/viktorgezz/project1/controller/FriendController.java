package ru.viktorgezz.project1.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.viktorgezz.project1.dao.FriendDAO;
import ru.viktorgezz.project1.dto.FriendDTO;
import ru.viktorgezz.project1.services.FriendService;
import ru.viktorgezz.project1.util.ValidCashFlow;

import java.util.List;

@RestController
@RequestMapping("/friend")
public class FriendController {

    private final FriendDAO friendDAO;
    private final FriendService friendService;
    private final ValidCashFlow validCashFlow;

    @Autowired
    public FriendController(FriendDAO friendDAO, FriendService friendService, ValidCashFlow validCashFlow) {
        this.friendDAO = friendDAO;
        this.friendService = friendService;
        this.validCashFlow = validCashFlow;
    }

    @PostMapping("/request_friend")
    public ResponseEntity<HttpStatus> requestFriend(@RequestBody @Valid FriendDTO friendDTO,
                                                    BindingResult bindingResult) {
        validCashFlow.checkError(bindingResult);
        friendDAO. addFriend(friendDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/accept_request")
    public ResponseEntity<HttpStatus> acceptRequest(@RequestBody @Valid FriendDTO friendDTO,
                              BindingResult bindingResult) {
        validCashFlow.checkError(bindingResult);
        friendDAO.acceptFriend(friendDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/get_request/{idAccount}") // 2-oe id
    public List<String> getRequest(@PathVariable int idAccount) {
        return friendDAO.getFriendRequests(idAccount);
    }

    @GetMapping("/search/{login}")
    public List<String> findFriends(@PathVariable String login) {
        return friendService.findFriendsByLogin(login);
    }

}
