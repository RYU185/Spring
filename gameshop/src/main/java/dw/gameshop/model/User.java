package dw.gameshop.model;

import dw.gameshop.dto.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter // SETTER가 없는 이유: 회원가입 이후로는 수정을 절대 하지않는다
@ToString
@Entity
@Table(name="user")
public class User {
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
}
