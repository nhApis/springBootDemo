package com.nhapis.argus.er.message.model;

import com.github.pagehelper.Page;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Setter
public class PageInfo<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    //当前页
    private int pageNum;
    //每页的数量
    private int pageSize;
    //总记录数
    @Getter
    private long total;
    //总页数
    private int pages;
    //结果集
    @Getter
    private List<T> data;
    //是否为第一页
    private boolean isFirstPage = false;
    //是否为最后一页
    private boolean isLastPage = false;


    public PageInfo() {
    }

    /**
     * 包装Page对象
     *
     * @param data
     */
    public PageInfo(List<T> data) {
        if (data instanceof Page) {
            Page page = (Page) data;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();

            this.pages = page.getPages();
            this.data = page;
            this.total = page.getTotal();
        } else if (data instanceof Collection) {
            this.pageNum = 1;
            this.pageSize = data.size();

            this.pages = 1;
            this.data = data;
            this.total = data.size();
        }
        if (data instanceof Collection) {
            //判断页面边界
            judgePageBoudary();
        }
    }

    /**
     * 判定页面边界
     */
    private void judgePageBoudary() {
        isFirstPage = pageNum == 1;
        isLastPage = pageNum == pages;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PageInfo{");
        sb.append("pageNum=").append(pageNum);
        sb.append(", pageSize=").append(pageSize);
        sb.append(", total=").append(total);
        sb.append(", pages=").append(pages);
        sb.append(", data=").append(data);
        sb.append(", isFirstPage=").append(isFirstPage);
        sb.append(", isLastPage=").append(isLastPage);
        sb.append(", navigatepageNums=");
        sb.append('}');
        return sb.toString();
    }
}

