package com.lpb.demo.entity;

import com.lpb.demo.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String username;

    @Column(name = "pass_word")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "fullname")
    private String fullname;

    @Column (name = "gender")
    private String gender;

    @Enumerated(EnumType.STRING)
    private Role roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roles.name()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }


    @Override
    public String getUsername() {
        return this.username;
    }


    @Override
    public boolean isAccountNonExpired() {
        // Logic kiểm tra tài khoản có hết hạn hay không
        // Trong trường hợp này, trả về giá trị true nếu tài khoản không hết hạn
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Logic kiểm tra tài khoản có bị khóa hay không
        // Trong trường hợp này, trả về giá trị true nếu tài khoản không bị khóa
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Logic kiểm tra mật khẩu có hết hạn hay không
        // Trong trường hợp này, trả về giá trị true nếu mật khẩu không hết hạn
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Logic kiểm tra tài khoản có được kích hoạt hay không
        // Trong trường hợp này, trả về giá trị true nếu tài khoản đã được kích hoạt
        return true;
    }
}
