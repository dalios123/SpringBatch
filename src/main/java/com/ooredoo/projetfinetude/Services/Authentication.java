package com.ooredoo.projetfinetude.Services;

import com.ooredoo.projetfinetude.Entities.LoginResponse;
import com.ooredoo.projetfinetude.Entities.User;

public interface Authentication {
    public LoginResponse login(User user);
}
