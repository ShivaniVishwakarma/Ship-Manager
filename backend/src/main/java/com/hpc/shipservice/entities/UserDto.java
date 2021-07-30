package com.hpc.shipservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A model class for saving user information
 * @author Shivani Vishwakarma
 * @since 29.07.21
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    
    private String username;
    private String password;
    private String email;
    private String phone;
    private String name;

    /**
     * Fetches the user information from databse
     * @return User
     */
    public User getUserFromDto(){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        user.setName(name);
        
        return user;
    }
    
}
