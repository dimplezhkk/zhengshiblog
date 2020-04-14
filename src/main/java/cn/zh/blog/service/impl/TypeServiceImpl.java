package cn.zh.blog.service.impl;

import cn.zh.blog.dao.TypeRepository;
import cn.zh.blog.exception.NotFoundException;
import cn.zh.blog.pojo.Type;
import cn.zh.blog.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 郑豪
 * @Date 2020/4/7 1:11
 **/
@Service
public class TypeServiceImpl  implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Type saveType(Type type) {

        return typeRepository.save(type);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Type getType(Long id) {
        return typeRepository.findById(id).get();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Type updateType(Long id, Type type) {
        Type t = typeRepository.findById(id).get();
        if (t == null) {
            throw new NotFoundException("不存在该博文分类");
        }
        //使用BeanUtils.copyProperties进行对象之间的属性赋值
        BeanUtils.copyProperties(type,t);
        return typeRepository.save(t);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }

    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }

    @Override
    public List<Type> listTypeTop(Integer size) {
        Pageable pageable = PageRequest.of(0,size, Sort.by(Sort.Direction.DESC,"blogs.size"));
        return typeRepository.findTop(pageable);
    }

    @Override
    public Long countType() {
        return typeRepository.count();
    }

    @Override
    public Page<Type> listType(Pageable pageable, String name) {
        return typeRepository.findAll(new Specification<Type>(){
            @Override
            public Predicate toPredicate(Root<Type> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!"".equals(name) && name != null) {
                    predicates.add(criteriaBuilder.like(root.get("name"),"%" + name +"%"));
                }
                criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }
}
