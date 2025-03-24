package dw.gameshop.model;

import dw.gameshop.dto.UserDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter // SETTER가 없는 이유: 회원가입 이후로는 수정을 절대 하지않는다
@ToString
@Entity
@Table(name="user")
public class User implements UserDetails {
    // 1. UserDetails 상속
    @Id
    @Column(name="user_name")
    private String userName; // 로그인하는 유저 아이디
    @Column(name="password", nullable = false)
    private String password;
    @Column(name="email", nullable = false, unique = true)
    private String email;
    @Column(name = "real_name", nullable = false)
    private String realName;
    @ManyToOne
    @JoinColumn(name = "user_authority")
    private Authority authority;
    @Column(name="created_at", updatable = false)
    private LocalDateTime createdAt;

    public UserDTO toDto() {
        return new UserDTO(
                this.userName,
                null, // 패스워드는 보호하도록 null
                this.email,
                this.realName,
                authority.getAuthorityName()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 권한을 읽어오는 메서드
        return Collections.singletonList(new SimpleGrantedAuthority(authority.getAuthorityName())); // 유저 엔티티 수정
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    // 현재 계정이 됐는지 확인

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
