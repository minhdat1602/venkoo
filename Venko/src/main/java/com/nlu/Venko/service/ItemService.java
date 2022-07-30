package com.nlu.Venko.service;

import com.nlu.Venko.model.Item;
import com.nlu.Venko.model.dto.ItemDTO;

import java.util.List;

public interface ItemService {
    List<ItemDTO> findAll();
    ItemDTO update(ItemDTO itemDTO);
    ItemDTO add(ItemDTO itemDTO);
    void delete(Long itemId);
}
