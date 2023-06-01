package com.hormigo.david.parkingmanager.user.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hormigo.david.parkingmanager.core.exceptions.UserExistsException;
import com.hormigo.david.parkingmanager.user.domain.Role;
import com.hormigo.david.parkingmanager.user.domain.User;
import com.hormigo.david.parkingmanager.user.domain.UserDao;
import com.hormigo.david.parkingmanager.user.service.UserService;
import com.hormigo.david.parkingmanager.user.service.UserServiceImpl;

import io.cucumber.java.Before;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Optional;
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper mapper;
    @MockBean
    private UserService userService;
    @BeforeEach
    public void initMapper() {
        this.mapper = new ObjectMapper();
    }

    @Test
    public void userDuplicated() throws Exception {
        UserDao dao = new UserDao("da@correo.es", "David", "Hormigo", "Ramírez", Role.PROFESSOR);
        String json = mapper.writeValueAsString(dao);
        doThrow(UserExistsException.class).when(this.userService).register(any(UserDao.class));
        this.mockMvc.perform(post("/api/users")
        .contentType("application/json").content(json))
        .andDo(print())
        .andExpect(status().is4xxClientError());
    }

    @Test
    public void testPositive() throws Exception {
        UserDao dao = new UserDao("da@correo.es", "David", "Hormigo", "Ramírez", Role.PROFESSOR);
        String json = mapper.writeValueAsString(dao);
        when(this.userService.register(any(UserDao.class))).thenReturn(new User("da@correo.es","David","Hormigo","Ramírez",Role.PROFESSOR));
        this.mockMvc.perform(post("/api/users")
                    .contentType("application/json").content(json))
                    .andDo(print())
                    .andExpect(status().isCreated());
    }

    @Test
    public void testSingleUserRead() throws Exception{
        ObjectMapper mapper = new ObjectMapper();

        User user = new User("dhorram948@g.educaand.es","David","Hormigo","Ramírez",Role.PROFESSOR);
        ArrayList<User> usuariso = new ArrayList<>();
        usuariso.add(user);
        String json = mapper.writeValueAsString(usuariso);
        json = "{ \"_embedded\": {\"userList\":" + json + "}}";
        when(userService.getAll()).thenReturn(usuariso);
        this.mockMvc.perform(get("/api/users"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().json(json));
                    

    }

 
}
