package com.nlu.Venko.service;

import com.nlu.Venko.model.Item;
import com.nlu.Venko.model.dto.ItemDTO;
import com.nlu.Venko.payload.request.FilterRequest;

import java.util.List;

public interface ItemService {
    List<ItemDTO> findAll();
    List<ItemDTO> findAll(FilterRequest filterRequest);
    ItemDTO update(ItemDTO itemDTO);
    ItemDTO add(ItemDTO itemDTO);
    void delete(Long itemId);
}
