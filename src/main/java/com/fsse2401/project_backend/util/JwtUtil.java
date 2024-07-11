package com.fsse2401.project_backend.util;

import com.fsse2401.project_backend.data.user.domainObject.FirebaseUserData;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class JwtUtil {
    public static FirebaseUserData getFirebaseUserData(JwtAuthenticationToken jwiToken){
        // This is a very simple example for login structure
        return new FirebaseUserData(jwiToken);
    }
}
