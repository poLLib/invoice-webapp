package cz.pollib.service.model;

public record AuthResponse(
        String token,
        String email,
        boolean isAdmin
) {}
