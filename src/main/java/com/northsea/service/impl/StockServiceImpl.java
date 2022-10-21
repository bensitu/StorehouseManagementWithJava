package com.northsea.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.northsea.dao.StockDao;
import com.northsea.entity.Stock;
import com.northsea.entity.User;
import com.northsea.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author BenSitu
 * @CreateDate 2022/10/15
 * @Description
 */
@Service
public class StockServiceImpl extends ServiceImpl<StockDao, Stock> implements StockService {

    @Autowired
    private StockDao stockDao;

    @Override
    public IPage<Stock> getPage(int currentPage, int pageSize) {
        IPage<Stock> page = new Page<Stock>(currentPage, pageSize);
        stockDao.selectPage(page, null);
        return page;
    }
}
