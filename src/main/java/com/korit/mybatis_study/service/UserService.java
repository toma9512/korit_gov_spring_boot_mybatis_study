package com.korit.mybatis_study.service;

import com.korit.mybatis_study.dto.*;
import com.korit.mybatis_study.entity.User;
import com.korit.mybatis_study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public ApiRespDto<?> signup(SignupReqDto signupReqDto) {
        Optional<User> foundUser = userRepository.findUserByUsername(signupReqDto.getUsername());

        if (foundUser.isPresent()) {
            return new ApiRespDto<>("failed", "중복된 username", null);
        }

        int result = userRepository.signup(signupReqDto.toEntity());

        if (result != 1) {
            return new ApiRespDto<>("failed", "회원 가입 실패", null);
        }

        return new ApiRespDto<>("success", "회원 가입 성공", null);
    }

    public ApiRespDto<?> getUserAll() {
        return new ApiRespDto<>("success", "전체 조회", userRepository.getUserAll());
    }

    public ApiRespDto<?> getUserByUsername(String username) {
        Optional<User> foundUser = userRepository.findUserByUsername(username);

        if (foundUser.isEmpty()) {
            return new ApiRespDto<>("failed", "찾으려는 회원이 없음", null);
        }

        foundUser.get().setPassword(null);
        return new ApiRespDto<>("success", "단건 조회", foundUser.get());
    }

    public ApiRespDto<?> editUser(EditUserReqDto editUserReqDto) {
        Optional<User> foundUser = userRepository.findUserByUsername(editUserReqDto.getUsername());

        if (foundUser.isEmpty()) {
            return new ApiRespDto<>("failed", "회원 조회 실패", null);
        }

        if (!foundUser.get().getPassword().equals(editUserReqDto.getPassword())) {
            return new ApiRespDto<>("failed", "기존 비밀번호와 일치하지 않습니다.", null);
        }

        int result = userRepository.editUser(editUserReqDto.toEntity());

        if (result != 1) {
            return new ApiRespDto<>("failed", "수정 실패", null);
        }

        return new ApiRespDto<>("success", "수정 완료", null);
    }

    public ApiRespDto<?> removeUser(RemoveUserReqDto removeUserReqDto) {
        Optional<User> foundUser = userRepository.findUserByUsername(removeUserReqDto.getUsername());

        if (foundUser.isEmpty() || !foundUser.get().getPassword().equals(removeUserReqDto.getPassword())) {
            return new ApiRespDto<>("failed", "회원 정보가 일치하지 않음", null);
        }

        int result = userRepository.removeUser(removeUserReqDto.getUsername());

        if (result != 1) {
            return new ApiRespDto<>("failed", "회원 삭제 실패", null);
        }

        return new ApiRespDto<>("success", "회원 삭제 완료", null);
    }

    public ApiRespDto<?> signin(SigninReqDto signinReqDto) {
        Optional<User> foundUser = userRepository.findUserByUsername(signinReqDto.getUsername());

        if (foundUser.isEmpty() || !foundUser.get().getPassword().equals(signinReqDto.getPassword())) {
            return new ApiRespDto<>("failed", "회원 정보가 일치하지 않습니다.", null);
        }

        return new ApiRespDto<>("success", "로그인 성공", foundUser.get());
    }
}
