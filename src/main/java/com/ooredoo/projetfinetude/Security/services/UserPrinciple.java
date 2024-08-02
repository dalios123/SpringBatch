package com.ooredoo.projetfinetude.Security.services;

 
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import com.ooredoo.projetfinetude.Entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;



public class UserPrinciple implements UserDetails {
	private static final long serialVersionUID = 1L;

    private String username;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

 

	public UserPrinciple(String username,
			String password, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}



	public static UserPrinciple build(User user) {
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        return new UserPrinciple(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

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



	@Override
	public int hashCode() {
		return Objects.hash(authorities, password, username);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserPrinciple other = (UserPrinciple) obj;
		return Objects.equals(authorities, other.authorities) && Objects.equals(password, other.password)
				&& Objects.equals(username, other.username);
	}


}