package com.wcmei.demo.commons.jpa.service.impl;

import com.wcmei.demo.commons.domain.AbstractBaseDomain;
import com.wcmei.demo.commons.service.BaseCommService;
import com.wcmei.demo.commons.utils.CommonUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wcmei
 * @date 2019-12-11
 * @description
 */
public class JpaCommServiceImpl<T extends AbstractBaseDomain, M extends JpaRepository<T, Long>> implements BaseCommService<T> {

    @Autowired
    protected M repository;

    //private Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    @Override
    public void insertDomain(T domain) {
        repository.save(domain);
    }

    @Override
    public void updateDomain(T domain) {
        T databaseDomain = repository.getOne(domain.getId());
        BeanUtils.copyProperties(domain, databaseDomain, CommonUtils.getNullPropertyNames(domain));
        repository.save(databaseDomain);
    }

    @Override
    public void deleteDomainById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public T selectDomainById(Long id) {
        T domain = repository.getOne(id);
        return domain;
    }
}
