package com.yym.springboot.common.util;

import java.io.Serializable;
import java.util.List;


/**
 * @param <T> 实体
 * @author lizj
 */
public class CustomPage<T> implements Serializable {

    /**
     * 当前页数
     */
    private Integer page;

    /**
     * //总记录数
     */
    private Integer total;
    /**
     * 每页显示条数
     */
    private Integer pageSize;

    /**
     * //总页数
     */
    private Integer totalPages;

    /**
     * //分页列表数据
     */
    private List<T> list;

    public CustomPage(Integer currentPage, Integer totalCount, Integer pageSize) {
        this.total = totalCount;
        this.pageSize = pageSize;
        this.page = currentPage;
        if (this.page == null) {
            //如页面没有指定显示那一页.显示第一页.
            this.page = 1;
        }
        if (this.pageSize == null) {
            //如果每页显示条数没有指定,默认每页显示10条
            this.pageSize = 10;
        }
        //计算总页数
        this.totalPages = (this.total + this.pageSize - 1) / this.pageSize;
        //判断当前页数是否超出范围
        //不能小于1
        if (this.page < 1) {
            this.page = 1;
        }
        //不能大于总页数
//        if (this.page > this.totalPages && this.totalPages > 0) {
//            this.page = this.totalPages;
//        }
    }


    /**
     * //计算起始索引
     *
     * @return int
     */
    public int getStart() {
        return (this.page - 1) * this.pageSize;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public CustomPage() {
    }


    /**
     * 自定义mp分页
     *
     * @param queryWrapper 条件
     * @param service      service
     * @param pageSize     分页大小
     * @param page  当前页数
     * @return PageUtils
     */
/*    public static CustomPage getLimitPage(LambdaQueryWrapper queryWrapper, ServiceImpl service,
                                     Integer pageSize, Integer page) {
        int count = service.count(queryWrapper);
        CustomPage<Object> customPage = new CustomPage<>(page, count, pageSize);
        queryWrapper.last(" limit " + customPage.getStart() + "," + customPage.getPageSize());
        List<Object> beans = service.list(queryWrapper);
        customPage.setList(beans);
        return customPage;
    }*/

}