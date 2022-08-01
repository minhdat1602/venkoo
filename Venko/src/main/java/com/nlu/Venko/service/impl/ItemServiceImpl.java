package com.nlu.Venko.service.impl;

import com.nlu.Venko.model.Item;
import com.nlu.Venko.model.dto.ItemDTO;
import com.nlu.Venko.model.dto.TaskDTO;
import com.nlu.Venko.payload.request.FilterRequest;
import com.nlu.Venko.repository.ItemRepository;
import com.nlu.Venko.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    public List<ItemDTO> findAll(FilterRequest filterRequest) {
        mapper = new ModelMapper();
        List<ItemDTO> result = itemRepository.findAll()
                .stream()
                .map(item -> mapper.map(item, ItemDTO.class))
                .collect(Collectors.toList());
        switch (filterRequest.getStatus()) {
            case DONE:
                List<Long> idsDoing = new ArrayList<>();
                result.forEach(itemDTO -> {
                    itemDTO.getTasks().forEach(taskDTO -> {
                        if (!Objects.equals(taskDTO.getDate(), filterRequest.getDate())
                                || !Objects.equals(taskDTO.getIsDone(), true)) {
                            idsDoing.add(taskDTO.getId());
                        }
                    });
                });
                return removeTasks(result, idsDoing);
            case DOING:
                List<Long> idsDone = new ArrayList<>();
                result.forEach(itemDTO -> {
                    itemDTO.getTasks().forEach(taskDTO -> {
                        if (!Objects.equals(taskDTO.getDate(), filterRequest.getDate())
                                || !Objects.equals(taskDTO.getIsDone(), false)) {
                            idsDone.add(taskDTO.getId());
                        }
                    });
                });
                return removeTasks(result, idsDone);
            default:
                List<Long> ids = new ArrayList<>();
                result.forEach(itemDTO -> {
                    itemDTO.getTasks().forEach(taskDTO -> {
                        if (!Objects.equals(taskDTO.getDate(), filterRequest.getDate())) {
                            ids.add(taskDTO.getId());
                        }
                    });
                });
            return removeTasks(result, ids);
        }
    }

    private List<ItemDTO> removeTasks(List<ItemDTO> itemDTOs, List<Long> ids){
        for(var i = 0; i < itemDTOs.size(); i++){
            List<TaskDTO> taskDTOS = new ArrayList<>();
            itemDTOs.get(i).getTasks().forEach(taskDTO -> {
                if (!ids.contains(taskDTO.getId())){
                    taskDTOS.add(taskDTO);
                }
            });
            itemDTOs.get(i).setTasks(taskDTOS);
        }
        return itemDTOs;
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
