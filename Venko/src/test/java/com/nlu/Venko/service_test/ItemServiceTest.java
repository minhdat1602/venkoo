package com.nlu.Venko.service_test;

import com.nlu.Venko.model.Item;
import com.nlu.Venko.model.dto.ItemDTO;
import com.nlu.Venko.repository.ItemRepository;
import com.nlu.Venko.service.ItemService;
import com.nlu.Venko.service.impl.ItemServiceImpl;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemServiceImpl itemService;

    private ModelMapper mapper;

    @Test
    public void testFindAll(){
        List<Item> list = new ArrayList<Item>();
        list.add(new Item());
        list.add(new Item());

        Mockito.when(itemRepository.findAll()).thenReturn(list);

        List<ItemDTO> listDto = itemService.findAll();
        Assert.isTrue(listDto.size() == 2);
    }

    @Test
    public void testAdd(){
        mapper = new ModelMapper();

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(1l);
        Item item = mapper.map(itemDTO, Item.class);

        Mockito.when(itemRepository.save(Mockito.any(Item.class))).thenReturn(item);

        ItemDTO result = itemService.add(itemDTO);
        Assert.notNull(result);
    }

    @Test
    public void testUpdate(){
        mapper = new ModelMapper();
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(1l);
        itemDTO.setCode("New Code");

        Item item = new Item();
        item.setId(1l);
        item.setCode("Old Code");

        Item newItem = new Item();
        newItem.setId(1l);
        newItem.setCode("New Code");

        Mockito.when(itemRepository.findById(item.getId())).thenReturn(Optional.of(item));
        Mockito.when(itemRepository.save(Mockito.any(Item.class))).thenReturn(newItem);

        ItemDTO result = itemService.update(itemDTO);

        Assert.isTrue(result.getCode().equals(itemDTO.getCode()));
    }

    @Test
    public void testDelete(){
        try{
            itemService.delete(1l);
            Assert.isTrue(true);
        }catch (Exception e){
            System.out.println(e.getMessage());
            Assert.isTrue(false);
        }
    }
}
