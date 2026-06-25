package org.yearup.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yearup.models.Profile;
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
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Profile> getProfile(Principal principal) {
        if (principal == null ) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String userName = principal.getName();

        User user = userService.getByUserName(userName);
        int userId = user.getId();

        return ResponseEntity.ok(profileService.getByUserId(userId));

    }

    @PutMapping
    public ResponseEntity<Profile> updateProfile(Principal principal,  @RequestBody Profile profile)
    {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String userName = principal.getName();
        User user = userService.getByUserName(userName);
        int userId = user.getId();

        profileService.update(profile,userId );
        return ResponseEntity.ok().body(profile);
    }




}
