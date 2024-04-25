package systematicTraders.tdt.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import systematicTraders.tdt.domain.user.User;
import systematicTraders.tdt.domain.user.UserRepository;
import systematicTraders.tdt.domain.user.dtos.UserRegisterDto;
import systematicTraders.tdt.domain.user.dtos.UserUpdateDto;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;
    @Autowired UserRepository userRepository;
    @Autowired AuthController authController;

    @Test
    void 회원가입O() throws Exception {
        //given
        UserRegisterDto dto = UserRegisterDto.builder()
                .loginId("test-id")
                .password("test-password")
                .nickname("test-nn")
                .build();
        //when
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users/register")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
        //then
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void 회원가입X_비밀번호오류() throws Exception {
        //given
        UserRegisterDto dto = UserRegisterDto.builder()
                .loginId("test-id")
                .password("")
                .nickname("test-nn")
                .build();
        //when
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users/register")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
        //then
        ).andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    void 회원가입X_ID중복() throws Exception {
        //given
        UserRegisterDto dto = UserRegisterDto.builder()
                .loginId("test-id")
                .password("test-password")
                .nickname("test-nn")
                .build();
        //when
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users/register")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
        //then
        ).andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    //TODO 포스트맨으로 대체
//    @Test
    void 회원정보수정O() throws Exception {
        //given

        //when

        //then
    }

    //TODO 포스트맨으로 대체
//    @Test
    void 회원정보수정X_입력값_오류() throws Exception {
        //given

        //when

        //then
    }

    //TODO 포스트맨으로 대체
//    @Test
    void 회원탈퇴() throws Exception {
        //given

        //when

        //then

    }
}