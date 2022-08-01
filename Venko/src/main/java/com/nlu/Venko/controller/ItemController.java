package com.nlu.Venko.controller;

import com.nlu.Venko.model.dto.ItemDTO;
import com.nlu.Venko.payload.request.FilterRequest;
import com.nlu.Venko.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ItemService itemService;

    @GetMapping
    private ResponseEntity<?> findAll(){
        List<ItemDTO> result = itemService.findAll();
        logger.info("Find all with result: " + Arrays.toString(result.toArray()));
        return ResponseEntity.ok(result);
    }

    @PostMapping("/filter")
    private ResponseEntity<?> filter(@RequestBody FilterRequest filterRequest){
        return ResponseEntity.ok(itemService.findAll(filterRequest));
    }

    @PostMapping
    private ResponseEntity<?> add(@RequestBody ItemDTO itemDTO){
        logger.info("Add item with : " + itemDTO.toString());
        return ResponseEntity.ok(itemService.add(itemDTO));
    }

    @PutMapping
    private ResponseEntity<?> update(@RequestBody ItemDTO itemDTO){
        logger.info("Update item with : " + itemDTO.toString());
        return ResponseEntity.ok(itemService.update(itemDTO));
    }

    @DeleteMapping("{itemId}")
    private ResponseEntity<?> delete(@PathVariable("itemId") Long itemId){
        itemService.delete(itemId);
        logger.info("Delete item with : " + itemId);
        return ResponseEntity.ok().build();
    }
}
