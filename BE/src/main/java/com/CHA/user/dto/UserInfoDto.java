package com.CHA.user.dto;


import lombok.*;

//단지 get해서 사용하는거여서

@AllArgsConstructor
@Builder
@Setter
@Getter
@NoArgsConstructor
public class UserInfoDto {


    private String nickname;
    private String account;
    private Long balance;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}
