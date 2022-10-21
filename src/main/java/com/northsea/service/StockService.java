package com.northsea.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.northsea.entity.Stock;

/**
 * @Author BenSitu
 * @CreateDate 2022/10/15
 * @Description
 */

public interface StockService extends IService<Stock> {
    IPage<Stock> getPage(int currentPage, int pageSize);
}
