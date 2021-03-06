package com.adkun.seckill.service.impl;

import com.adkun.seckill.dao.ItemMapper;
import com.adkun.seckill.dao.ItemStockMapper;
import com.adkun.seckill.dao.PromotionMapper;
import com.adkun.seckill.entity.Item;
import com.adkun.seckill.entity.ItemStock;
import com.adkun.seckill.entity.Promotion;
import com.adkun.seckill.service.ItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品业务
 *
 * @author adkun
 */
@Service
public class ItemServiceImpl implements ItemService {

    private final ItemMapper itemMapper;
    private final ItemStockMapper itemStockMapper;
    private final PromotionMapper promotionMapper;

    public ItemServiceImpl(ItemMapper itemMapper, ItemStockMapper itemStockMapper, PromotionMapper promotionMapper) {
        this.itemMapper = itemMapper;
        this.itemStockMapper = itemStockMapper;
        this.promotionMapper = promotionMapper;
    }

    /**
     * 找到正在促销的商品
     * 促销商品其实就是秒杀的商品
     * @return
     */
    @Override
    public List<Item> findItemsOnPromotion() {
        List<Item> items = itemMapper.selectOnPromotion();
        return items.stream().map(item -> {
            // 查库存
            ItemStock stock = itemStockMapper.selectByItemId(item.getId());
            item.setItemStock(stock);
            // 查活动
            Promotion promotion = promotionMapper.selectByItemId(item.getId());
            if (promotion != null && promotion.getStatus() == 0) {
                item.setPromotion(promotion);
            }
            return item;
        }).collect(Collectors.toList());
    }

    /**
     * 根据id找到商品（商品详情页）
     * @param id
     * @return
     */
    @Override
    public Item findItemById(int id) {
        Item item = itemMapper.selectByPrimaryKey(id);

        // 查库存
        ItemStock stock = itemStockMapper.selectByItemId(id);
        item.setItemStock(stock);

        // 查促销
        Promotion promotion = promotionMapper.selectByItemId(id);
        if (promotion != null && promotion.getStatus() == 0) {
            item.setPromotion(promotion);
        }

        return item;
    }

    /**
     * 减库存（事务）
     * @param itemId 商品id
     * @param amount 商品数量
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean decreaseStock(int itemId, int amount) {
        int rows = itemStockMapper.decreaseStock(itemId, amount);
        return rows > 0;
    }

    /**
     * 增加库存（事务）
     * @param itemId
     * @param amount
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void increaseSales(int itemId, int amount) {
        itemMapper.increaseSales(itemId, amount);
    }
}
