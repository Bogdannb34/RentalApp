package com.finalproject.SmartBear.service;

import com.finalproject.SmartBear.exception.user.*;
import com.finalproject.SmartBear.model.user.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface UserServiceImpl {

    User register(String firstName, String lastName, String username, String email) throws UserNotFoundException, UsernameExistException, EmailExistException;

    List<User> getUsers();

    User findByUsername(String username);

    User findByEmail(String email);

    User updateUser(String currentUsername, String newFirstName, String newLastName, String newUsername, String newEmail,
                     boolean isNotLocked, boolean isActive, String newPhoneNumber, MultipartFile profileImage) throws UserNotFoundException, UsernameExistException, EmailExistException, NotAnImageFileException, IOException;

    void deleteById(Long id);

    void deleteUserByUsername(String username) throws IOException;

    void resetPassword(String email) throws EmailNotFoundException;

    User updateProfileImage(String username, MultipartFile profileImage) throws IOException, NotAnImageFileException, UserNotFoundException, UsernameExistException, EmailExistException;

}
