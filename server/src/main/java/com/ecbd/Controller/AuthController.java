package com.ecbd.Controller;

import com.ecbd.Entity.Article;
import com.ecbd.Entity.User;
import com.ecbd.Exceptions.UnauthorizedException;
import com.ecbd.Exceptions.UserDoesNotExistException;
import com.ecbd.Exceptions.UserExistsException;
import db.UserCollectionBridge;
import org.bson.Document;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tools.Encoder;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

    @RequestMapping(value="/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String login(@RequestBody User user) {
        Document userFromDB = UserCollectionBridge.getUser(user.getName());
        if (userFromDB == null) {
            throw new UserDoesNotExistException();
        }
        Document userWithHashedPassword = UserCollectionBridge.makeUserDocument(user);
        if (userWithHashedPassword.get("password").equals(userFromDB.get("password"))){
            user.setId(userFromDB.get("_id").toString());
            return Encoder.encodeUser(user);
        }
        else
            throw new UnauthorizedException();
    }


    @RequestMapping(value="/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void register(@RequestBody User user) {
        boolean inserted = UserCollectionBridge.addUser(user);
        if (!inserted) {
            throw new UserExistsException();
        }
    }
}
