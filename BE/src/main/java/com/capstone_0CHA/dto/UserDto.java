package com.capstone_0CHA.dto;

import com.capstone_0CHA.domain.User;
import lombok.*;

import java.io.Serializable;

public class UserDto {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Getter
    public static class Request {

        private String username;
        private String password;

        //Dto -> Entity
        public User toEntity() {
            User user = User.builder()
                    .username(username)
                    .password(password)
                    .build();

            return user;
        }
    }

    //세션 저장용 dto
    @Getter
    public static class Response implements Serializable {
        private String userId;

        //Entity -> Dto
        public Response(User user) {
            this.userId = user.getUsername();
        }
    }
}