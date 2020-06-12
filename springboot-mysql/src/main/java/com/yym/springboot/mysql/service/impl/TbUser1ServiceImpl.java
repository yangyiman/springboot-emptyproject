package com.yym.springboot.mysql.service.impl;

import com.yym.springboot.mysql.entity.TbUser1;
import com.yym.springboot.mysql.entity.TbUser2;
import com.yym.springboot.mysql.mapper.TbUser1Mapper;
import com.yym.springboot.mysql.mapper.TbUser2Mapper;
import com.yym.springboot.mysql.service.ITbUser1Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yym
 * @since 2020-06-04
 */
@Service
public class TbUser1ServiceImpl extends ServiceImpl<TbUser1Mapper, TbUser1> implements ITbUser1Service {

    @Autowired
    private TbUser2Mapper tbUser2Mapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void insertRequired(TbUser1 user1) {
        this.save(user1);
    }

    /**
     * 每5秒发送一次 hello world
     */
    @Override
    @Scheduled(cron = "0/5 * * * * * *")
    public void scheduleTask() {
        System.out.println("hello world");
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void saveNotSupport(TbUser1 user1) {
        this.save(user1);
    }

    /**
     * 注册项目
     * @param tbUser1
     * @return
     */
    @Override
    @Transactional
    public boolean registry(TbUser1 tbUser1) {
        boolean save = this.save(tbUser1);
        if(save){
            try {
                this.changeName(tbUser1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 将用户姓名加后缀 _good
     */
    @Transactional(propagation = Propagation.NESTED)
    public void changeName(TbUser1 user1) {
        String oldName = user1.getName();
        String newName = oldName+"_GOOD";
        this.updateById(user1.setName(newName));
        try {
            this.addLog(oldName,newName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 不受事务影响
     * @param oldName
     * @param newName
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void addLog(String oldName, String newName) {
        TbUser2 user2 = new TbUser2().setName("日志人员");
        tbUser2Mapper.insert(user2);
    }



}
