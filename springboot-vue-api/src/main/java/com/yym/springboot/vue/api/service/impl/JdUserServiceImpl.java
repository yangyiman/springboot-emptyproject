package com.yym.springboot.vue.api.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yym.springboot.vue.api.entity.JdUser;
import com.yym.springboot.vue.api.mapper.JdUserMapper;
import com.yym.springboot.vue.api.service.IJdUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.util.Arrays;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yym
 * @since 2020-06-22
 */
@Service
public class JdUserServiceImpl extends ServiceImpl<JdUserMapper, JdUser> implements IJdUserService {

    @Override
    public IPage<JdUser> pageList(Integer page, Integer pageSize) {
        return this.page(new Page<JdUser>(page, pageSize));
    }

    @Override
    public JdUser login(JdUser jdUser) {
        String username = jdUser.getUsername();
        return null;
    }

/*    public static void main(String[] args) {
        String url = "/zodiac-wechat/7d/7d623c2b0dbf4b9f898d66210d7905eb.jpg";
        String[] split = url.split("/");
        System.out.println("split = " + Arrays.toString(split));
    }*/
}
