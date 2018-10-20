package br.com.acoaapi.config.security;

import br.com.acoaapi.model.entity.User;

import java.util.Optional;

public interface UserService {
    public Optional<User> getByUsername(String username);
}
