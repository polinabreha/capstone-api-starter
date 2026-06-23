package org.yearup.service;

import org.springframework.stereotype.Service;
import org.yearup.models.Profile;
import org.yearup.repository.ProfileRepository;

@Service
public class ProfileService
{
    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository)
    {
        this.profileRepository = profileRepository;
    }

    public Profile create(Profile profile)
    {
        return profileRepository.save(profile);
    }

    public Profile getByUserId(Integer userId)
    {
       return profileRepository.findByUserId(userId);
    }

    public void update(Profile profile, int userId)
    {
        Profile existing = profileRepository.findByUserId(userId);
        existing.setFirstName(profile.getFirstName());
        existing.setLastName(profile.getLastName());
        existing.setEmail(profile.getEmail());
        existing.setPhone(profile.getPhone());
        existing.setAddress(profile.getAddress());
        existing.setCity(profile.getCity());
        existing.setState(profile.getState());
        existing.setZip(profile.getZip());
        profileRepository.save(existing);
    }
}
