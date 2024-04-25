package systematicTraders.tdt.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import systematicTraders.tdt.domain.auth.LoginDto;
import systematicTraders.tdt.domain.user.User;
import systematicTraders.tdt.domain.user.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AuthControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;
    @Autowired UserRepository userRepository;
    private String loginId = "test-id";
    private String password = "test-password";

    @BeforeEach
    void 회원저장() {
        User user = User.builder()
                .loginId(loginId)
                .encryptedPassword(BCrypt.hashpw(password, BCrypt.gensalt()))
                .build();
        userRepository.save(user);
    }

    @Test
    void 로그인O() throws Exception {
        //given
        LoginDto dto = LoginDto.builder()
                .loginId(loginId)
                .password(password)
                .build();

        //when
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
        //then
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void 로그인X_ID오류() throws Exception {
        //given
        LoginDto dto = LoginDto.builder()
                .loginId(loginId)
                .password(password + "x")
                .build();

        //when
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                //then
        ).andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    void 로그인X_비밀번호_오류() throws Exception {
        //given
        LoginDto dto = LoginDto.builder()
                .loginId(loginId + "x")
                .password(password)
                .build();

        //when
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                //then
        ).andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    //TODO 포스트맨으로 대체
//    @Test
    void 로그아웃O() throws Exception {

    }

}