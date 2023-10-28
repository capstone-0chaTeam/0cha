package com.CHA.user.service;

import com.CHA.game.Entity.Stock;
import com.CHA.game.repository.StockRepository;
import com.CHA.user.User;
import com.CHA.user.dto.UserInfoDto;
import com.CHA.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class UserInfoService {

    private final UserRepository userRepository;
    private final StockRepository stockRepository;


    //userInfo 메소드를 통해서
    //각각 유저 정보를 반환
    public Optional<User> userInfo(Optional<String> useremail){

        //Optional안에 uesremail이 담겨있음
        String result = useremail.map(Object::toString).orElse(null);
        Optional<User> user = userRepository.findUserByEmail(result);
//        user.get().getStock() 형식으로 뽑으면 끝 위에 있는걸 service 로직을 만들어서 해보자
        if (user.isPresent()) {
            // 값을 가져왔을 때의 처리
            System.out.println(result); // 출력: "123"
            return user;
        } else {
            // 값이 없을 때의 처리
            System.out.println("값이 없습니다.");
        }
        return user;
    }

    public UserInfoDto userInfo_Id_NickName_Balance(Optional<String> useremail) {
        Optional<User> user = userInfo(useremail);

        UserInfoDto userInfoDto = UserInfoDto.builder()
                .user_id(user.get().getId())
                .nickname(user.get().getNickname())
                .balance(user.get().getUser_to_stock().getBalance())
                .build();

        return userInfoDto;
    }

    public List<Stock> usersInfo_rank(){

        return stockRepository.findTop3ByOrderByBalance();
    }
}
