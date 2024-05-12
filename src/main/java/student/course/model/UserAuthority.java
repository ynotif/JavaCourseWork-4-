package student.course.model;

import org.springframework.security.core.GrantedAuthority;

public enum UserAuthority implements GrantedAuthority {
    USER,
    CREATOR,
    ADMIN;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
