package com.hebta.common.persistence.service;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.hebta.common.persistence.PageResult;
import com.hebta.common.persistence.mapper.BaseMapper;

import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 业务处理基础类-全维版本
 * 
 * @author zl
 * @param <M>
 *            对应mapper接口
 * @param <T>
 *            对应entity类
 */
@Slf4j
public abstract class BaseServiceImpl<M extends BaseMapper<T>, T> implements BaseService<T> {

    @Autowired
    protected M baseMapper;

    @Override
    public int insert(T entity) {
        return baseMapper.insert(entity);
    }

    @Override
    public int insertSelective(T entity) {
        return baseMapper.insertSelective(entity);
    }

    @Override
    public int insertBatch(List<T> list) {
        return baseMapper.insertList(list);
    }

    @Override
    public int update(T entity) {
        return baseMapper.updateByPrimaryKey(entity);
    }

    @Override
    public int updateSelective(T entity) {
        return baseMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public int updateByExample(T entity, Example example) {
        return baseMapper.updateByExampleSelective(entity, example);
    }

    @Override
    public int deleteById(Serializable id) {
        return baseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public T selectByProperty(String column, Object value) {
        return baseMapper.selectOneByExample(buildEntity(column, value));
    }

    @Override
    public List<T> listByProperty(String column, Object value) {
        return baseMapper.selectByExample(buildEntity(column, value));
    }

    @Override
    public T selectById(Serializable id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    @Override
    public T selectByIdAndGroupId(Serializable id, String groupId, Class<?> entityClass) {
        Example query = new Example(entityClass);
        Criteria criteria = query.createCriteria();
        criteria.andCondition("s_group_id=", groupId);
        criteria.andEqualTo("sId", id);
        List<T> list = baseMapper.selectByExample(query);
        return null == list || list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<T> select(T entity) {
        return baseMapper.select(entity);
    }

    @Override
    public List<T> selectAllList() {
        return baseMapper.selectAll();
    }

    @Override
    public int count(T entity) {
        return baseMapper.selectCount(entity);
    }

    @Override
    public PageResult<T> selectPage(T entity, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<T> list = baseMapper.select(entity);
        return new PageResult<T>(list);
    }

    @Override
    public List<T> selectByExample(Example example) {
        return baseMapper.selectByExample(example);
    }

    @Override
    public PageResult<T> selectByExample(Example example, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<T> list = baseMapper.selectByExample(example);
        return new PageResult<T>(list);
    }

    @Override
    public int countByExample(Example example) {
        int n = baseMapper.selectCountByExample(example);
        return n;
    }

    // ****Private Function****
    /**
     * *构建泛型对象
     * 
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private Example buildEntity(String column, Object value) {
        try {
            column = underlineToCamel(column);
            Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                    .getActualTypeArguments()[1];
            Example ex = new Example(entityClass);
            ex.and().andEqualTo(column, value);
            return ex;
        } catch (Exception e) {
            log.error("buildEntity error.", e);
            return null;
        }
    }

    /**
     * 下划线格式字符串转换为驼峰格式字符串
     * 
     * @param param
     * @return
     */
    public static String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == '_') {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
    // public static void main(String[] args) throws Exception {
    // String column = "s_account";
    // column = underlineToCamel(column);
    // System.out.println(column);
    // Class<BranchAccount> entityClass = BranchAccount.class;
    // BranchAccount t = entityClass.newInstance();
    // PropertyDescriptor pd = new PropertyDescriptor(column, entityClass,
    // "get"+column, "set"+column);
    // Method wM = pd.getWriteMethod();
    // wM.invoke(t, "ssss");
    //
    // System.out.println(t.getsAccount());
    // }
}
