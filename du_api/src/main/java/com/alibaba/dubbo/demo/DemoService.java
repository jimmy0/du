package com.alibaba.dubbo.demo;

import java.util.Date;
import java.util.List;

import com.alibaba.dubbo.demo.bean.Valid;
import com.alibaba.dubbo.demo.model.City;
import com.alibaba.dubbo.demo.model.Person;

/**
* @author 爱不留   
* @date 2018年5月8日 下午1:50:23 
**/
public interface DemoService {

	public String sayHello(String name);
	
	public Valid submitPersons(String token, Date date, City city, List<Person> persons);
	
}
