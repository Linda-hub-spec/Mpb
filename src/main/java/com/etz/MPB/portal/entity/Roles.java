package com.etz.MPB.portal.entity;

import com.etz.MPB.portal.enums.ConstantStatus;
import com.etz.MPB.portal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "roles")

public class Roles {

    @ManyToMany(mappedBy = "roles")
    private Set<Users> users;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id"))
    Set<Permissions> permissions;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private boolean authorized;
    @Enumerated
    private ConstantStatus status;
    private LocalDateTime createdOn;
    private Long createdBy;
    private LocalDateTime updatedOn;
    private Long updatedBy;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    public ConstantStatus getStatus() {
        return status;
    }

    public void setStatus(ConstantStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Set<Permissions> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permissions> permissions) {
        this.permissions = permissions;
    }


//    @PrePersist
//    private void setCreatedDate() {
//        if (SecurityContextHolder.getContext().getAuthentication() != null) {
//            Optional<Users> user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
//            this.createdBy = user.get().getId();
//        }else {
//            this.createdBy = 0L;
//        }
//        this.createdOn = LocalDateTime.now();
//    }
//
//    @PreUpdate
//    private void updatedAt() {
//        if (SecurityContextHolder.getContext().getAuthentication() != null) {
//            Optional<Users> user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
//            this.updatedBy = user.get().getId();
//        }else {
//            this.updatedBy = 0L;
//        }
//        this.updatedOn = LocalDateTime.now();
//    }
    
}
