package com.etz.MPB.portal.entity;

import com.etz.MPB.portal.enums.ConstantStatus;
import com.etz.MPB.portal.enums.UserStatus;
import com.etz.MPB.portal.repositories.UserRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String username;

    @JsonIgnore
    private String cypher;
    private boolean authorized;
    @Enumerated
    private UserStatus status;
    private LocalDateTime createdOn;
    private Long createdBy;
    private LocalDateTime updatedOn;
    private Long updatedBy;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    @Fetch(value= FetchMode.SELECT)
    private Set<Roles> roles = new HashSet<>();


//    @PrePersist
//    private void setCreatedDate() {
//        if (SecurityContextHolder.getContext().getAuthentication() != null) {
//            Optional<Users> user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
//            this.createdBy = user.get().getId();
//        }
//        this.createdOn = LocalDateTime.now();
//    }
//
//    @PreUpdate
//    private void updatedAt() {
//        if (SecurityContextHolder.getContext().getAuthentication() != null) {
//            Optional<Users> user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
//            this.updatedBy = user.get().getId();
//        }
//        this.updatedOn = LocalDateTime.now();
//    }


}
