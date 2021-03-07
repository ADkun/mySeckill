package com.adkun.mySeckill;

import com.adkun.mySeckill.dao.ItemMapper;
import com.adkun.mySeckill.entity.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Tests {

    @Autowired
    private ItemMapper itemMapper;

    /**
     * 测试1
     * 测试ItemMapper
     */
    @Test
    public void test() {
        List<Item> list = itemMapper.selectAll();
        for (Item item : list) {
            System.out.println(item);
        }
    }
}
