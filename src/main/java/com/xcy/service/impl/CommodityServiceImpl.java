package com.xcy.service.impl;

import com.xcy.dao.CommodityDao;
import com.xcy.entity.Commodity;
import com.xcy.service.CommodityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xcy
 * @date 2018/09/28 14:28
 * @description 商品实现类
 * @since V1.0.0
 */
@Service
public class CommodityServiceImpl implements CommodityService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CommodityDao commodityDao;

    @Override
    public List<Commodity> list() {
        List<Commodity> commodityList = commodityDao.list();
        logger.info("查询到{}条数据", commodityList.size());
        return commodityList;
    }
}
