package com.yym.springboot.mysql.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yym.springboot.mysql.entity.TbClass;
import com.yym.springboot.mysql.entity.TbStudent;
import com.yym.springboot.mysql.mapper.TbClassMapper;
import com.yym.springboot.mysql.mapper.TbStudentMapper;
import com.yym.springboot.mysql.service.TbClassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TbClassService是接口,并且继承了IService接口,所有TbClassService的所有实现类不仅要实现它本身的方法,还有实现IService的方法;
 * ServiceImpl是IService的实现类,所以,当TbClassService继承了ServiceImpl类之后,就表示已经实现了Iservice的所有方法,那么,其本类只需要实现TbClassService方法就可以了
 */
@Service
@Slf4j
public class TbClassServiceImpl extends ServiceImpl<TbClassMapper,TbClass> implements TbClassService {
    @Autowired
    private TbClassMapper tbClassMapper;
    @Autowired
    private TbStudentMapper tbStudentMapper;

    @Override
    public void updateOne(TbClass tbClass) {
        this.updateById(tbClass);
    }

    /**
     * 根据id查,没有就使用班级名称搜
     * @param id    1
     * @param className 究极一班
     * @return
     */
    @Override
    public List<TbClass> selectOneByIdOrClassName(Integer id, String className) {
        LambdaQueryWrapper<TbClass> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(TbClass::getId, id);
        List<TbClass> list = this.list(wrapper);
        if(CollectionUtils.isEmpty(list)){
            wrapper.or();
            wrapper.eq(TbClass::getClassName,className);
            // 只获取className
            //wrapper.select(TbClass::getClassName);
            this.list(wrapper);
        }
        return list;
    }

    @Override
    public TbClass selectOne(Integer id) {
        return this.getById(id);
    }

    @Override
    public List<TbClass> getTbclass() {
        return this.list();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertClass(TbClass tbClass) {
        tbClassMapper.insert(tbClass);
        Integer id = tbClass.getId();
        log.info("新增的班级id为"+id);
        TbStudent tbStudent = tbClass.getTbStudent();
        tbStudent.setClassId(id);
        tbStudentMapper.insert(tbStudent);
    }

}
