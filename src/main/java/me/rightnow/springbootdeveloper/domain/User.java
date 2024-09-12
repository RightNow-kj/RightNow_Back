package me.rightnow.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User implements UserDetails { // userdetails 상속받아 인증 객체로 사용
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    // 사용자 이름
    @Column(name = "nickname", unique = true)
    private String nickname;

    @Column(name = "password")
    private String password;

    @Builder
    public User(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    @Override // 권한 반환
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override // 사용자 id 반환(고유값)
    public String getUsername() {
        return email;
    }

    @Override // 사용자 패스워드 반환
    public String getPassword() {
        // 만료됐는지 확인하는 로직
        return password; // true 는 만료되지 않음 을 뜻함
    }
    @Override // 계정 만료 여부 반환
    public boolean isAccountNonExpired() {
        // 계정 잠금됐는지 확인하는 로직
        return true; // true 는 잠금되지 않음을 뜻함
    }

    @Override // 계정 잠금 여부 반환
    public boolean isAccountNonLocked() {
        // 패스워드가 만료됐는지 확인하는 로직
        return true; // true 는 만료되지 않았음을 뜻함
    }

    @Override //패스워드 만료 여부 반환
    public boolean isCredentialsNonExpired() {
        // 패스워드가 만료됐는지 확인하는 로직
        return true; // true 는 만료되지 않았음을 뜻함
    }

    @Override // 계정 사용가능 여부 반환
    public boolean isEnabled() {
        // 계정 사용 가능한지 확인하는 로직
        return true; // true는 사용 가능함을 뜻함
    }

    // 사용자 이름 변경
    public User update(String nickname) {
        this.nickname = nickname;
        return this;
    }
}
