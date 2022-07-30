package com.nlu.Venko.repository_test;

import com.nlu.Venko.model.Item;
import com.nlu.Venko.model.Task;
import com.nlu.Venko.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class TaskRepositoryTest {

    @MockBean
    private TaskRepository taskRepository;

    @Test
    public void testAdd(){
        Item item = new Item();
        item.setName("Name");
        item.setCode("Code");
        Task task = new Task();
        task.setTitle("TITLE");
        task.setIsDone(true);
        task.setItem(item);

        task = taskRepository.save(task);

        assert(task.getId() > 0);
    }

    @Test
    public void testUpdate(){
        Item item = new Item();
        item.setName("Name");
        item.setCode("Code");
        Task task = new Task();
        task.setTitle("TITLE");
        task.setIsDone(true);
        task.setItem(item);

        task = taskRepository.save(task);

        task.setTitle("NEW TITLE");
        task = taskRepository.save(task);

        assert(task.getTitle().equals("NEW TITLE"));
    }

    @Test
    public void testDelete(){
        Item item = new Item();
        item.setName("Name");
        item.setCode("Code");
        Task task = new Task();
        task.setTitle("TITLE");
        task.setIsDone(true);
        task.setItem(item);

        task = taskRepository.save(task);

        taskRepository.delete(task);

        try{
            Task task2 = taskRepository.findById(task.getId()).get();
            assert task2 == null;
        }catch (Exception e){
            assert true;
        }
    }
}
