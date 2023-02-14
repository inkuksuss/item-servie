package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        Item item = new Item("itemA", 10000, 10);
        Item save = itemRepository.save(item);


        Item findItem = itemRepository.findById(save.getId());

        Assertions.assertThat(save).isEqualTo(findItem);
    }

    @Test
    void findAll() {
        Item itemA = new Item("itemA", 10000, 10);
        Item itemB = new Item("itemB", 20000, 10);

        Item saveA = itemRepository.save(itemA);
        Item saveB = itemRepository.save(itemB);

        List<Item> all = itemRepository.findAll();

        Assertions.assertThat(all.size()).isEqualTo(2);
        Assertions.assertThat(all).contains(saveA, saveB);
    }

    @Test
    void updateItem() {
        Item itemA = new Item("itemA", 10000, 10);

        Item save = itemRepository.save(itemA);

        Item updateParam = new Item("itemB", 20000, 10);

        itemRepository.update(save.getId(), updateParam);

        Item findItem = itemRepository.findById(save.getId());

        Assertions.assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
    }
}