package com.manish.WorkHub.model;

import com.manish.WorkHub.enums.Role;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "users") // Specify the MongoDB collection name
@Data // Lombok generates getters, setters, and toString methods
public class User {
    @Id
    private String email;
    private String name;
    private String gender;
    private String password;
    private String profileUrl;
    private String phoneNo;
    private Role role;
    private Date joinDate;
    private List<String> interests;

}
