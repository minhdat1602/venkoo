package com.nlu.Venko.controller;

import com.nlu.Venko.model.dto.ItemDTO;
import com.nlu.Venko.payload.request.FilterRequest;
import com.nlu.Venko.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    private ResponseEntity<?> findAll(){
        return ResponseEntity.ok(itemService.findAll());
    }

    @PostMapping("/filter")
    private ResponseEntity<?> filter(@RequestBody FilterRequest filterRequest){
        return ResponseEntity.ok(itemService.findAll(filterRequest));
    }

    @PostMapping
    private ResponseEntity<?> add(@RequestBody ItemDTO itemDTO){
        return ResponseEntity.ok(itemService.add(itemDTO));
    }

    @PutMapping
    private ResponseEntity<?> update(@RequestBody ItemDTO itemDTO){
        return ResponseEntity.ok(itemService.update(itemDTO));
    }

    @DeleteMapping("{itemId}")
    private ResponseEntity<?> delete(@PathVariable("itemId") Long itemId){
        itemService.delete(itemId);
        return ResponseEntity.ok().build();
    }
}
