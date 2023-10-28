package com.CHA.user.dto;


import lombok.*;

//단지 get해서 사용하는거여서

@AllArgsConstructor
@Builder
@Setter
@Getter
@NoArgsConstructor
public class UserInfoDto {

    private Long user_id;

    private String nickname;
    private Long balance;


    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }


}
