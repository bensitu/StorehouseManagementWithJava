package com.northsea.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.northsea.dao.IODao;
import com.northsea.entity.IO;
import com.northsea.service.IOService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author BenSitu
 * @CreateDate 2022/10/17
 * @Description
 */
@Service
public class IOServiceImpl extends ServiceImpl<IODao, IO> implements IOService {

    @Autowired
    private IODao ioDao;

    @Transactional
    @Override
    public IPage<IO> getPage(int currentPage, int pageSize) {
        IPage<IO> page = new Page<IO>(currentPage, pageSize);
        ioDao.selectPage(page, null);
        return page;
    }

    @Transactional
    @Override
    public IPage<IO> getPageWithStockId(String stock_id, int currentPage, int pageSize) {
        LambdaQueryWrapper<IO> lambdaQueryWrapper = new LambdaQueryWrapper<IO>();
        lambdaQueryWrapper.eq(Strings.isNotEmpty(stock_id), IO::getId, stock_id);
        IPage<IO> page = new Page<IO>(currentPage, pageSize);
        ioDao.selectPage(page, lambdaQueryWrapper);
        return page;
    }

    @Transactional
    @Override
    public IPage<IO> getPageWithStockIdByParam(int currentPage, int pageSize, IO iO) {
        LambdaQueryWrapper<IO> lambdaQueryWrapper = new LambdaQueryWrapper<IO>();
        lambdaQueryWrapper.eq(Strings.isNotEmpty(iO.getId()), IO::getId, iO.getId());
        IPage<IO> page = new Page<IO>(currentPage, pageSize);
        ioDao.selectPage(page, lambdaQueryWrapper);
        return page;
    }

    @Transactional
    @Override
    public IPage<IO> getAllIOBySearch(String stock_id, int currentPage, int pageSize, IO iO) {
        LambdaQueryWrapper<IO> lambdaQueryWrapper = new LambdaQueryWrapper<IO>();
        lambdaQueryWrapper.eq(Strings.isNotEmpty(stock_id), IO::getId, stock_id);
        lambdaQueryWrapper.eq(Strings.isNotEmpty((CharSequence) iO.getCreateDate()),IO::getCreateDate, iO.getCreateDate());
        lambdaQueryWrapper.eq(Strings.isNotEmpty(Integer.toString(iO.getIoType())),IO::getIoType, iO.getIoType());
        Page<IO> page = new Page<>(currentPage, pageSize);
        return ioDao.selectPage(page, lambdaQueryWrapper);
    }

    @Transactional
    @Override
    public IPage<IO> getIOInfoWithUpdateUser(String stock_id, int currentPage, int pageSize, IO iO) {
        LambdaQueryWrapper<IO> lambdaQueryWrapper = new LambdaQueryWrapper<IO>();
        lambdaQueryWrapper.eq(Strings.isNotEmpty(stock_id), IO::getId, stock_id);
        lambdaQueryWrapper.eq(Strings.isNotEmpty(iO.getId()), IO::getId, iO.getId());
        Page<IO> page = new Page<>(currentPage, pageSize);
        return ioDao.selectPage(page, lambdaQueryWrapper);
    }

    @Transactional
    @Override
    public Boolean saveIOInfo(IO iO) {
        return ioDao.saveIOInfo(iO) > 0;
    }

    @Override
    public IO getIOItemByInOutNo(String stock_id, Integer in_out_no) {
//        LambdaQueryWrapper<IO> lambdaQueryWrapper = new LambdaQueryWrapper<IO>();
//        lambdaQueryWrapper.eq(Strings.isNotEmpty(stock_id), IO::getId, stock_id);
//        lambdaQueryWrapper.eq(Strings.isNotEmpty(Integer.toString(in_out_no)), IO::getInOutNo, in_out_no);
        return ioDao.getLastIOInfoRecordByIOID(stock_id);
    }

    @Override
    public IO getLatestIOItemByStockId(String stock_id) {
        return ioDao.getLastIOInfoRecordByIOID(stock_id);
    }


}
