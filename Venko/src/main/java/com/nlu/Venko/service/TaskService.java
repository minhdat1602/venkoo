package com.nlu.Venko.service;

import com.nlu.Venko.model.dto.TaskDTO;
import com.nlu.Venko.payload.request.TaskRequest;

public interface TaskService {

    TaskDTO add(TaskRequest taskRequest, Long taskId);
    TaskDTO update(TaskRequest taskRequest, Long taskId, Long itemId);
    void delete(Long taskId, Long itemId);
}
