package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;

import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.BrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;
import com.pinyougou.service.impl.BaseServiceImpl;
import com.pinyougou.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 暴露服务
 * 也就是在注册中心注册服务名称
 */
@Service(interfaceClass = BrandService.class)
public class BrandServiceImpl extends BaseServiceImpl<TbBrand> implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public PageResult search(Integer page, Integer rows, TbBrand tbBrand) {
        PageHelper.startPage(page,rows);
        Example example = new Example(TbBrand.class);
        Example.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(tbBrand.getFirstChar())){
            criteria.andEqualTo("firstChar",tbBrand.getFirstChar());
        }
        if (!StringUtils.isEmpty(tbBrand.getName())){
            criteria.andLike("name","%"+tbBrand.getName()+"%");
        }
        List<TbBrand> tbBrands = brandMapper.selectByExample(example);
        PageInfo<TbBrand> pageInfo = new PageInfo<>(tbBrands);
        return new PageResult(pageInfo.getTotal(),pageInfo.getList());
    }
}
