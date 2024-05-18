//package ru.viktorgezz.project1.util;
//
//
//import org.springframework.security.core.Authentication;
//
//import java.time.Duration;
//import java.util.LinkedList;
//import java.util.UUID;
//import java.util.function.Function;
//
//public class DefaultRefreshTokenFactory implements Function<Authentication, Token> {
//
//    private Duration tokenTtl = Duration.ofDays(1);
//
//    @Override
//    public Token apply(Authentication authentication) {
//        var authorities = new LinkedList<String>();
//        return new Token(UUID.randomUUID(), authentication.getName(), );
//    }
//}
