package com.yym.springboot.vue.api.service.impl;

import com.yym.springboot.vue.api.entity.Score;
import com.yym.springboot.vue.api.mapper.ScoreMapper;
import com.yym.springboot.vue.api.service.IScoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yym
 * @since 2020-04-21
 */
@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements IScoreService {

}
