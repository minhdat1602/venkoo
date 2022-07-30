package com.nlu.Venko.service.impl;

import com.nlu.Venko.model.Item;
import com.nlu.Venko.model.Task;
import com.nlu.Venko.model.dto.TaskDTO;
import com.nlu.Venko.payload.request.TaskRequest;
import com.nlu.Venko.repository.ItemRepository;
import com.nlu.Venko.repository.TaskRepository;
import com.nlu.Venko.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    private ModelMapper mapper;

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public TaskDTO add(TaskRequest taskRequest, Long itemId) {
        mapper = new ModelMapper();

        Task task = mapper.map(taskRequest, Task.class);
        Item item = itemRepository.findById(itemId).get();
        task.setItem(item);
        task = taskRepository.save(task);

        TaskDTO result = mapper.map(task, TaskDTO.class);
        result.setItemId(itemId);
        return result;
    }

    @Override
    public TaskDTO update(TaskRequest taskRequest, Long taskId, Long itemId) {
        mapper = new ModelMapper();
        Task task = taskRepository.findById(taskId).get();
        task.setTitle(taskRequest.getTitle());
        task.setDate(task.getDate());
        task.setIsDone(taskRequest.getIsDone());

        task = taskRepository.save(task);

        TaskDTO result = mapper.map(task, TaskDTO.class);
        result.setItemId(itemId);

        return result;
    }

    @Override
    public void delete(Long taskId, Long itemId) {
        Task task = taskRepository.findById(taskId).get();
        taskRepository.delete(task);
    }
}
