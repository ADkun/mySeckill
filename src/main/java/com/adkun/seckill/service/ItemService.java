package com.adkun.seckill.service;

import com.adkun.seckill.entity.Item;

import java.util.List;

public interface ItemService {

    /**
     * 找到正在促销的商品
     *
     * @return
     */
    List<Item> findItemsOnPromotion();

    /**
     * 根据id找商品
     *
     * @param id
     * @return
     */
    Item findItemById(int id);

    /**
     * 减少库存
     *
     * @param itemId 商品id
     * @param amount 商品数量
     * @return
     */
    boolean decreaseStock(int itemId, int amount);

    /**
     * 增加销量
     *
     * @param itemId
     * @param amount
     */
    void increaseSales(int itemId, int amount);
}
