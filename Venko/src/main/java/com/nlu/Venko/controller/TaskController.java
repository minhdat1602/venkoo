package com.nlu.Venko.controller;

import com.nlu.Venko.payload.request.TaskRequest;
import com.nlu.Venko.service.TaskService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/items/{itemId}/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody TaskRequest taskRequest, @PathVariable("itemId") Long itemId){
        return ResponseEntity.ok(taskService.add(taskRequest, itemId));
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<?> update(@RequestBody TaskRequest taskRequest, @PathVariable("taskId") Long taskId,
                                    @PathVariable("itemId") Long itemId){
        return ResponseEntity.ok(taskService.update(taskRequest, taskId, itemId));
    }

    @DeleteMapping("{taskId}")
    public ResponseEntity delete(@PathVariable("taskId") Long taskId, @PathVariable("itemId") Long itemId){
        taskService.delete(taskId, itemId);
        return ResponseEntity.ok().build();
    }
}
