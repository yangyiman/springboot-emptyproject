package com.yym.springboot.restsecurity.util;

import com.yym.springboot.common.util.SpringUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Data
public class RedisUtil {
    private RedisTemplate redisTemplate = SpringUtil.getBean("redisTemplate", RedisTemplate.class);

    /**
     * 设置过期时间
     * @param key key
     * @param expireSecond 过期时间,单位为s
     * @return b
     */
    public boolean expire(String key, long expireSecond) {
        try {
            if (expireSecond > 0) {
                redisTemplate.expire(key, expireSecond, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void converAndSend(String channel,Object message){
        redisTemplate.convertAndSend(channel,message);
    }

    /**
     * 获取缓存过期时间,单位为s
     * @param key key
     * @return 时间(s) 返回为0表示永久有效
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    public Set keys(String keyWord){
        return redisTemplate.keys(keyWord);
    }


    /**
     * 判断当前key是否存在
     * @param key key
     * @return b
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public void del(String... keys) {
        if (keys != null && keys.length > 0) {
            if (keys.length == 1) {
                redisTemplate.delete(keys[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(keys));
            }
        }
    }

    // ================== String ====================

    public Object getValue(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置缓存
     * @param key key
     * @param value value
     * @param seconds  当为null时,表示永久保存
     * @return  b
     */
    public boolean setValue(String key, Object value, Long seconds) {
        try {
            if (seconds != null && seconds > 0) {
                redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
            }else {
                redisTemplate.opsForValue().set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 操作数字
     * @param key key
     * @param num 负数为减,正数为增
     * @return l
     */
    public long operateNum(String key,long num){
        if(num > 0){
            return redisTemplate.opsForValue().increment(key,num);
        }else {
            return redisTemplate.opsForValue().decrement(key,num);
        }
    }

    // =================== Hash =======================

    /**
     * HashSet
     * @param key key
     * @param map map
     * @param seconds 如果为null,则表示不设置过期时间
     * @return b
     */
    public boolean hmset(String key, Map<String,Object> map,Long seconds){
        try {
            redisTemplate.opsForHash().putAll(key,map);
            if(seconds != null && seconds > 0){
                this.expire(key,seconds);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向hash表中放入数据
     * @param key key
     * @param item 项
     * @param value 值
     * @param seconds 为null则表示永久存储
     * @return
     */
    public boolean hset(String key,String item,Object value,Long seconds){
        try {
            redisTemplate.opsForHash().put(key,item,value);
            if(seconds != null && seconds > 0){
                this.expire(key,seconds);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public long hdel(String key,Object... item){
        return redisTemplate.opsForHash().delete(key, item);
    }

    public boolean hHasKey(String key,String item){
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * 操作hash表中的数字
     * @param key key
     * @param item 项
     * @param num 负数为减,正数为增
     * @return l
     */
    public long hOperateNum(String key,String item,long num){
        return redisTemplate.opsForHash().increment(key, item, num);
    }

    public Map<Object,Object> hmget(String key){
        return redisTemplate.opsForHash().entries(key);
    }

    public Object hget(String key,String item){
        return redisTemplate.opsForHash().get(key, item);
    }

    // =================== Set =======================

    // 获取所有set中的值
    public Set<Object> sget(String key){
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    // 判断指定的value是否在set中
    public boolean sHasKey(String key,Object value){
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // 将values存入set,并且设置过期时间
    public long sadd(String key,Long seconds,Object... values){
        try {
            long t = redisTemplate.opsForSet().add(key,values);
            if(seconds != null && seconds > 0){
                redisTemplate.expire(key,seconds,TimeUnit.SECONDS);
            }
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    // 获取set的长度
    public long sSize(String key){
        return redisTemplate.opsForSet().size(key);
    }
    // 移除指定values
    public long sdel(String key,Object... values){
        return redisTemplate.opsForSet().remove(key, values);
    }

    // ================ List ==================

    /**
     * 获取list的内容
     * @param key key
     * @param start 从0开始
     * @param end 结束值, 0 到 -1代表所有值
     * @return list
     */
    public List<Object> lget(String key,int start,int end){
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    // 获取list的长度
    public long lsize(String key){
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    // 通过索引获取list中的值
    public Object lgetIndex(String key,long index){
        try {
            return redisTemplate.opsForList().index(key,index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    // 将values存入list,并设置过期时间
    public long lsetLeft(String key,Long seconds,Object... values){
        try {
            long l = redisTemplate.opsForList().leftPushAll(key, values);
            if(seconds != null && seconds > 0){
                redisTemplate.expire(key,seconds,TimeUnit.SECONDS);
            }
            return l;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public long lsetRight(String key,Long seconds,Object... values){
        try {
            long l = redisTemplate.opsForList().rightPushAll(key, values);
            if(seconds != null && seconds > 0){
                redisTemplate.expire(key,seconds,TimeUnit.SECONDS);
            }
            return l;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    // 批量将list存入list,并设置过期时间
    public long lsetLeftList(String key,Long seconds,List<Object> list){
        try {
            Long l = redisTemplate.opsForList().leftPushAll(key, list);
            if(seconds != null && seconds > 0){
                redisTemplate.expire(key,seconds,TimeUnit.SECONDS);
            }
            return l;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public long lsetRightList(String key,Long seconds,List<Object> list){
        try {
            Long l = redisTemplate.opsForList().rightPushAll(key, list);
            if(seconds != null && seconds > 0){
                redisTemplate.expire(key,seconds,TimeUnit.SECONDS);
            }
            return l;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    // 弹出元素
    public Object lpopLeft(String key){
        return redisTemplate.opsForList().leftPop(key);
    }
    public Object lpopRight(String key){
        return redisTemplate.opsForList().rightPop(key);
    }
    // 通过索引修改list的值
    public boolean lupdateIndex(String key,long index,Object value){
        try {
            redisTemplate.opsForList().set(key,index,value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // 在list中移除n个值为value的数据
    public long ldel(String key,long count,Object value){
        try {
            return redisTemplate.opsForList().remove(key, count, value);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 删除指定范围之外的数据
     * @param key   key
     * @param start start
     * @param end end
     * @return b
     */
    public boolean ldelrange(String key,int start,int end){
        try {
            redisTemplate.opsForList().trim(key,start,end);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    //=========BoundListOperations 用法 start============

    /**
     *将数据添加到Redis的list中（从右边添加）
     * @param listKey
     * @param expireEnum 有效期的枚举类
     * @param values 待添加的数据
     */
    /*public void addToListRight(String listKey, Status.ExpireEnum expireEnum, Object... values) {
        //绑定操作
        BoundListOperations<String, Object> boundValueOperations = redisTemplate.boundListOps(listKey);
        //插入数据
        boundValueOperations.rightPushAll(values);
        //设置过期时间
        boundValueOperations.expire(expireEnum.getTime(),expireEnum.getTimeUnit());
    }*/
    /**
     * 根据起始结束序号遍历Redis中的list
     * @param listKey
     * @param start  起始序号
     * @param end  结束序号
     * @return
     */
    public List<Object> rangeList(String listKey, long start, long end) {
        //绑定操作
        BoundListOperations<String, Object> boundValueOperations = redisTemplate.boundListOps(listKey);
        //查询数据
        return boundValueOperations.range(start, end);
    }
    /**
     * 弹出右边的值 --- 并且移除这个值
     * @param listKey
     */
    public Object rifhtPop(String listKey){
        //绑定操作
        BoundListOperations<String, Object> boundValueOperations = redisTemplate.boundListOps(listKey);
        return boundValueOperations.rightPop();
    }
}
