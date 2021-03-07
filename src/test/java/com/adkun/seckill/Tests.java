package com.adkun.seckill;

import com.adkun.seckill.dao.ItemMapper;
import com.adkun.seckill.entity.Item;
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
