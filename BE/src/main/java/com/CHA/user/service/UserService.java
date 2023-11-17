package com.CHA.user.service;


import com.CHA.game.Entity.Stock;
import com.CHA.game.repository.StockRepository;
import com.CHA.jwt.PasswordUtil;
import com.CHA.user.Role;
import com.CHA.user.User;
import com.CHA.user.dto.UserSignUpDto;
import com.CHA.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final StockRepository stockRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUp(UserSignUpDto userSignUpDto) throws Exception {

        if (userRepository.findByEmail(userSignUpDto.getEmail()).isPresent()) {
            throw new Exception("이미 존재하는 이메일입니다.");
        }

        if (userRepository.findByNickname(userSignUpDto.getNickname()).isPresent()) {
            throw new Exception("이미 존재하는 닉네임입니다.");
        }


        Stock stock = Stock.builder()
                .account(balanceNumber())
                .balance(50000000L)
                .cash(50000000L)
                .purchaseamount_all(0L)
                .build();

            //계좌번호 만들고 넣기 만약 같은게 있다면 다른거 넣기

        User user = User.builder()
                .email(userSignUpDto.getEmail())
                .password(userSignUpDto.getPassword())
                .nickname(userSignUpDto.getNickname())
                .role(Role.USER)
                .user_to_stock(stock)
                .build();



        user.passwordEncode(passwordEncoder);
        userRepository.save(user);

        stockRepository.save(stock);
    }

    //계좌번호 만들기
    //PasswordUtil 사용해서 만듬 1~9까지 int 말고 차라리 String 으로 형변환해서 넣을까 ?
    //같은 계좌번호가 아니라면 그대로 반환하기

    public String balanceNumber() {
        for (; ; ) {
            String account = PasswordUtil.generateRandomStockNumber();
            Optional<Stock> before_account = stockRepository.findByAccount(account);
            if (!before_account.isPresent()) {
                return account;
            }
        }
    }



}