package com.hebta.common.persistence;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpStatus;

import com.github.pagehelper.PageInfo;
import com.hebta.common.exception.BusinessException;
/**
 * 用于分页返回查询结果
 * @author Han.Sun
 * @since 2017年07月22日
 */
public class PageResult<T> extends Page {
	
	/**
	 * -配合PageHelper使用的构造
	 * @param list
	 */
	public PageResult(List<T> list) {
		if(!(list instanceof com.github.pagehelper.Page)){
    		throw new BusinessException("请在使用分页对象前调用PageHelper.startPage(pageNum, pageSize);", HttpStatus.SC_INTERNAL_SERVER_ERROR);
    	}
		PageInfo<T> pageinfo = new PageInfo<T>(list);
    	setCurrent(pageinfo.getPageNum());
    	setSize(pageinfo.getPageSize());
    	setTotal(new BigDecimal(pageinfo.getTotal()).intValue());
    	this.records = list;
	}
	
	public PageResult(Page page) {
        super(page.getTotal(),page.getSize(),page.getPages(),page.getCurrent());
    }
    
    public PageResult() {
    	
	}
	
    /**
     ** 查询数据列表
     */
    private List<T> records = new ArrayList<>();

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }
    
}
