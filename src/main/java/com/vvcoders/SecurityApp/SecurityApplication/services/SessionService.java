package com.vvcoders.SecurityApp.SecurityApplication.services;

import com.vvcoders.SecurityApp.SecurityApplication.entities.Session;
import com.vvcoders.SecurityApp.SecurityApplication.entities.Users;
import com.vvcoders.SecurityApp.SecurityApplication.repositories.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final int SESSION_LIMIT = 2;

    public void generateNewSession(Users user, String refreshToken){
        List<Session> userSessions = sessionRepository.findByUser(user);
        if(userSessions.size()== SESSION_LIMIT){
            userSessions.sort(Comparator.comparing(session -> session.getLastUsedAt()));
            Session leastRecentlyUsedSession = userSessions.getFirst();
            sessionRepository.delete(leastRecentlyUsedSession);
        }
        Session newSession = Session.builder()
                .refreshToken(refreshToken)
                .user(user)
                .build();
        sessionRepository.save(newSession);
    }

    public void validateSession(String refreshToken){
        Session session = sessionRepository.findByRefreshToken(refreshToken)
                .orElseThrow(()-> new SessionAuthenticationException("Session not found for RT: "+refreshToken));
        session.setLastUsedAt(LocalDateTime.now());
        sessionRepository.save(session);
    }
}
