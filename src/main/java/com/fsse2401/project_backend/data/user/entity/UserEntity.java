package com.fsse2401.project_backend.data.user.entity;

import com.fsse2401.project_backend.data.user.domainObject.FirebaseUserData;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer uid;
    @Column(nullable = false)
    private String email;
    @Column(name = "firebase_uid", nullable = false)
    private String firebaseUid;

    public UserEntity(FirebaseUserData data) {
        this.firebaseUid = data.getFirebaseUid();
        this.email = data.getEmail();
    }
}
