package com.nlu.Venko.repository_test;

import com.nlu.Venko.model.Item;
import com.nlu.Venko.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void testSave(){
        Item item = new Item();
        item.setCode("Code");
        item.setName("Name");
        item.setTasks(new ArrayList<>());

        item = itemRepository.save(item);

        assert(item.getId() > 0);
    }

    @Test
    public void testUpdate(){
        Item item = new Item();
        item.setCode("New Code");
        item.setName("New Name");

        Item existsItem = itemRepository.findById(1l).get();
        existsItem.setCode(item.getCode());
        existsItem.setName(item.getName());

        existsItem = itemRepository.save(existsItem);

        assert (existsItem.getCode().equals(item.getCode()) && existsItem.getName().equals(item.getName()));
    }

    @Test
    public void testDelete(){
        Item item = new Item();
        item.setCode("New Code");
        item.setName("New Name");

        item = itemRepository.save(item);

        itemRepository.deleteById(item.getId());

        try{
            Item deleted = itemRepository.findById(item.getId()).get();
            assert(deleted == null);
        }catch (Exception e){
            System.out.println(e.getMessage());
            assert(true);
        }
    }

    @Test
    public void testFindAll(){
        Item item = new Item();
        item.setCode("New Code");
        item.setName("New Name");
        Item item2 = new Item();
        item2.setCode("New Code");
        item2.setName("New Name");

        itemRepository.save(item);
        itemRepository.save(item2);

        assert (itemRepository.findAll().size() > 0);
    }


}
