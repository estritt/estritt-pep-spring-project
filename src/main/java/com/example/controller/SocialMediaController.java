package com.example.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.DTO.PatchMessageRequest;
import com.example.entity.*;
import com.example.exception.*;
import com.example.service.*;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */


 // i should probably make separate AccountController and MessageController classes with @RestController @RequestMapping
 @RestController
public class SocialMediaController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private MessageService messageService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public Account registerAccount(@RequestBody Account account) {
        return accountService.saveAccount(account);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public Account login(@RequestBody Account account) {
        return accountService.findByUsernameAndPassword(account);
    }

    @PostMapping("/messages")
    @ResponseStatus(HttpStatus.OK)
    public Message postMessage(@RequestBody Message message) {
        return messageService.saveMessage(message);
    }

    @GetMapping("/messages")
    @ResponseStatus(HttpStatus.OK)
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/messages/{message_id}")
    @ResponseStatus(HttpStatus.OK)
    public Message getMessageById(@PathVariable int message_id) {
        return messageService.getMessageById(message_id).orElse(null);
    }

    @DeleteMapping("/messages/{message_id}")
    @ResponseStatus(HttpStatus.OK)
    public Integer deleteMessage(@PathVariable int message_id) {
        return messageService.deleteMessage(message_id);
    }

    @PatchMapping("/messages/{message_id}")
    @ResponseStatus(HttpStatus.OK)
    public Integer patchMessage(@PathVariable int message_id, @RequestBody PatchMessageRequest request) {
        String messageText = request.getMessageText();
        return messageService.patchMessage(message_id, messageText);
    }

    @GetMapping("/accounts/{accountId}/messages")
    @ResponseStatus(HttpStatus.OK)
    public List<Message> getMessagesByAccount(@PathVariable int accountId) {
        return messageService.getMessagesByAccount(accountId);
    }

    @ExceptionHandler(AccountAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleAccountAlreadyExists(AccountAlreadyExistsException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(GenericCustomException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleGenericCustom(GenericCustomException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleUnauthorized(UnauthorizedException ex) {
        return ex.getMessage();
    }

}