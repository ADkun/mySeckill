package com.adkun.seckill.controller;

import com.adkun.seckill.common.ErrorCode;
import com.adkun.seckill.common.ResponseModel;
import com.adkun.seckill.entity.Item;
import com.adkun.seckill.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品信息接口
 * @author adkun
 */
@RestController
@RequestMapping("/item")
@CrossOrigin(origins = "${adkun.web.path}", allowedHeaders = "*", allowCredentials = "true")
public class ItemController implements ErrorCode {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * 商品列表
     *
     * @return
     */
    @GetMapping("/list")
    public ResponseModel getItemList() {
        List<Item> items = itemService.findItemsOnPromotion();
        return new ResponseModel(items);
    }

    /**
     * 商品详情
     *
     * @param id
     * @return
     */
    @GetMapping("/detail/{id}")
    public ResponseModel getItemDetail(@PathVariable("id") int id) {
        Item item = itemService.findItemById(id);
        return new ResponseModel(item);
    }
}
