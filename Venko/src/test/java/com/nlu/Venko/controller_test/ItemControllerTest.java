package com.nlu.Venko.controller_test;

import com.nlu.Venko.controller.ItemController;
import com.nlu.Venko.model.Item;
import com.nlu.Venko.model.dto.ItemDTO;
import com.nlu.Venko.security.service.UserDetailsServiceImpl;
import com.nlu.Venko.service.ItemService;
import com.nlu.Venko.service.impl.ItemServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemControllerTest{

    @MockBean
    private ItemServiceImpl itemService;
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;
    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(authorities = {"ROLE_USER"})
    public void testFindAll() throws Exception {
        List<ItemDTO> list = new ArrayList<>();

        ItemDTO item1 = new ItemDTO();
        item1.setId(1l);
        ItemDTO item2 = new ItemDTO();
        item2.setId(2l);

        list.add(item1);
        list.add(item2);

        String uri = "/api/items";
        mockMvc.perform(MockMvcRequestBuilders.get(uri))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(authorities = {"ROLE_USER"})
    public void testAdd() throws Exception {
        List<ItemDTO> list = new ArrayList<>();

        ItemDTO item1 = new ItemDTO();
        item1.setId(1l);
        ItemDTO item2 = new ItemDTO();
        item2.setId(2l);

        list.add(item1);
        list.add(item2);

        String uri = "/api/items";
        mockMvc.perform(MockMvcRequestBuilders.post(uri).content("{}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(authorities = {"ROLE_USER"})
    public void testUpdate() throws Exception {
        List<ItemDTO> list = new ArrayList<>();

        ItemDTO item1 = new ItemDTO();
        item1.setId(1l);
        ItemDTO item2 = new ItemDTO();
        item2.setId(2l);

        list.add(item1);
        list.add(item2);

        String uri = "/api/items";
        mockMvc.perform(MockMvcRequestBuilders.put(uri).content("{}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(authorities = {"ROLE_USER"})
    public void testDelete() throws Exception {
        List<ItemDTO> list = new ArrayList<>();

        ItemDTO item1 = new ItemDTO();
        item1.setId(1l);
        ItemDTO item2 = new ItemDTO();
        item2.setId(2l);

        list.add(item1);
        list.add(item2);

        String uri = "/api/items/1";
        mockMvc.perform(MockMvcRequestBuilders.delete(uri).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
