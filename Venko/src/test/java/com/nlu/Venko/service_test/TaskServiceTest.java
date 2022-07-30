package com.nlu.Venko.service_test;

import com.nlu.Venko.model.Item;
import com.nlu.Venko.model.Task;
import com.nlu.Venko.model.dto.TaskDTO;
import com.nlu.Venko.payload.request.TaskRequest;
import com.nlu.Venko.repository.ItemRepository;
import com.nlu.Venko.repository.TaskRepository;
import com.nlu.Venko.service.impl.TaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;
    @Mock
    private ItemRepository itemRepository;

    private ModelMapper mapper;

    @Spy
    @InjectMocks
    private TaskServiceImpl taskService;

    private ModelMapper modelMapper;

    @Test
    public void testAdd() {
        modelMapper = new ModelMapper();
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setItemId(1l);
        taskRequest.setDate(LocalDate.now());
        taskRequest.setIsDone(true);
        taskRequest.setTitle("Title");
        Task task = modelMapper.map(taskRequest, Task.class);
        Item item = new Item();
        Long itemId = 1l;
        item.setId(1l);
        task.setItem(item);

        task.setId(1l);
        Mockito.when(taskRepository.save(Mockito.any(Task.class))).thenReturn(task);
        Mockito.when(itemRepository.findById(itemId)).thenReturn(Optional.of(item));
        TaskDTO taskDTO = taskService.add(taskRequest, itemId);
        assert true;
    }

    @Test
    public void testUpdate() {
        mapper = new ModelMapper();
        Long itemId = 1l;
        Long taskId = 1l;
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setTitle("Title");
        taskRequest.setDate(LocalDate.now());
        taskRequest.setIsDone(true);
        taskRequest.setItemId(itemId);
        Task task = mapper.map(taskRequest, Task.class);
        task.setId(1l);

        Mockito.when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));
        Mockito.when(taskRepository.save(task)).thenReturn(task);

        TaskDTO taskDTO = taskService.update(taskRequest, taskId, itemId);

        assert taskDTO.getTitle().equals("Title");
    }

    @Test
    public void testDelete() {
        Long taskId = 1l;
        Long itemId = 1l;
        Task task = new Task();
        task.setId(taskId);

        Mockito.when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));
        try{
            taskService.delete(taskId, itemId);
            assert true;
        } catch (Exception e){
            assert false;
        }
    }
}
