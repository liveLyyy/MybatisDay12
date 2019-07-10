package com.liyan.service;

import com.liyan.file.PageInfo;
import com.liyan.pojo.People;

import java.util.List;

public interface PeopleService {

    List<People> findAll() throws Exception;

    PageInfo findPage(int pageSize,int pageNumber) throws Exception;

}
