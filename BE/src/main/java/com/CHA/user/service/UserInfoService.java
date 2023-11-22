package com.CHA.user.service;

import com.CHA.game.Entity.Stock;
import com.CHA.game.repository.StockRepository;
import com.CHA.user.User;
import com.CHA.user.dto.UserInfoDto;
import com.CHA.user.dto.UserRankerDto;
import com.CHA.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class UserInfoService {

    private final UserRepository userRepository;
    private final StockRepository stockRepository;


    // userInfo 메소드를 통해서
    // 각각 유저 정보를 반환
    // Optional안에 uesremail이 담겨있음
    // user.get().getStock() 형식으로 뽑으면 끝 위에 있는걸 service 로직을 만들어서 해보자
    public Optional<User> userInfo(Optional<String> useremail){


        String result = useremail.map(Object::toString).orElse(null);
        Optional<User> user = userRepository.findUserByEmail(result);

        //처리해야됨
        if (user.isPresent()) {
            return user;
        }else {
        }
        return user;
    }

    public UserInfoDto userInfo_Id_NickName_Balance(Optional<String> useremail) {
        Optional<User> user = userInfo(useremail);

        UserInfoDto userInfoDto = UserInfoDto.builder()
                .nickname(user.get().getNickname())
                .balance(user.get().getUser_to_stock().getBalance())
                .account(user.get().getUser_to_stock().getAccount())
                .build();

        return userInfoDto;
    }

    public List<UserRankerDto> usersInfo_rank() {


        List<Stock> ranking = stockRepository.findTop20ByOrderByBalanceDesc();

        List<UserRankerDto> ranker = ranking
                .stream()
                .map(rank -> new UserRankerDto(rank.getUser().getNickname(), rank.getAccount(), rank.getBalance()))
                .collect(Collectors.toList());

        return ranker;
    }
}
