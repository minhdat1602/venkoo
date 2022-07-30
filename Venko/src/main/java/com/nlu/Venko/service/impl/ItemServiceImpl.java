package com.nlu.Venko.service.impl;

import com.nlu.Venko.model.Item;
import com.nlu.Venko.model.dto.ItemDTO;
import com.nlu.Venko.repository.ItemRepository;
import com.nlu.Venko.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    private ModelMapper mapper;

    @Override
    public List<ItemDTO> findAll() {
        mapper = new ModelMapper();
        return itemRepository.findAll()
                .stream()
                .map(item -> mapper.map(item, ItemDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ItemDTO update(ItemDTO itemDTO) {
        mapper = new ModelMapper();
        Item entity = itemRepository.findById(itemDTO.getId().longValue()).get();
        entity = mapper.map(itemDTO, Item.class);

        entity = itemRepository.save(entity);

        return mapper.map(entity, ItemDTO.class);
    }

    @Override
    public ItemDTO add(ItemDTO itemDTO) {
        mapper = new ModelMapper();
        // dto -> entity
        Item entity = mapper.map(itemDTO, Item.class);
        //save
        entity = itemRepository.save(entity);
        // entity -> dto -> return
        return mapper.map(entity, ItemDTO.class);
    }

    @Override
    public void delete(Long itemId) {
        itemRepository.deleteById(itemId);
    }
}
