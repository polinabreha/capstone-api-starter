package org.yearup.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yearup.models.Profile;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import org.yearup.models.User;
import org.yearup.service.ProfileService;
import org.yearup.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("profile")
@CrossOrigin(origins = "http://localhost:63342")
public class ProfileController {

    private final ProfileService profileService ;
    private final UserService userService;

    public ProfileController(ProfileService profileService, UserService userService) {
        this.profileService = profileService;
        this.userService = userService;
    }

    @GetMapping
    public Profile getProfile(Principal principal) {
        String userName = principal.getName();
        User user = userService.getByUserName(userName);
        int userId = user.getId();

        return profileService.getByUserId(userId);

    }

    @PutMapping
    public ResponseEntity<Profile> updateProfile(Principal principal,  @RequestBody Profile profile)
    {
        String userName = principal.getName();
        User user = userService.getByUserName(userName);
        int userId = user.getId();

        profileService.update(profile,userId );
        return ResponseEntity.ok().body(profile);
    }




}
