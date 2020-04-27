package com.hebta.common.persistence;

/**
 * 用于查询参数
 * @author Han.Sun
 * @since 2017年07月22日
 */
public class Page extends ApiBody {

    /**总数*/
    private int total;
    /**每页显示条数，默认 10*/
    private int size = 10;
    /**总页数*/
    private int pages;
    /**当前页*/
    private int current = 1;

    public Page() {
        super();
    }

    public Page(int current, int size) {
        this.size = size;
        this.current = current;
    }

    public Page(int total, int size, int pages, int current) {
        this.total = total;
        this.size = size;
        this.pages = pages;
        this.current = current;
    }

    public int getOffset() {
        if (current > 0) {
            return (current - 1) * size;
        }
        return 0;
    }

    public boolean hasPrevious() {
        return this.current > 1;
    }

    public boolean hasNext() {
        return this.current < this.pages;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPages() {
        if (this.size == 0) {
            return 0;
        }
        this.pages = this.total / this.size;
        if (this.total % this.size != 0) {
            this.pages++;
        }
        return this.pages;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    @Override
    public String toString() {
        return "Page { total=" + total + " ,size=" + size + " ,pages=" + pages + " ,current=" + current + " }";
    }


    /**
     * 获取 Hibernate FirstResult
     */
    public int getFirstResult(){
        int firstResult = (getCurrent() - 1) * getSize();
        if (firstResult >= getTotal()) {
            firstResult = 0;
        }
        return firstResult;
    }
    /**
     * 获取 Hibernate MaxResults
     */
    public int getMaxResults(){
        return getSize();
    }

}
