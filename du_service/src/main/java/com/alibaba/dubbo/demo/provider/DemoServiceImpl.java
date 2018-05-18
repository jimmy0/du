package com.alibaba.dubbo.demo.provider;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.demo.DemoService;
import com.alibaba.dubbo.demo.bean.Valid;
import com.alibaba.dubbo.demo.model.City;
import com.alibaba.dubbo.demo.model.Person;

/**
* @author 爱不留   
* @date 2018年5月8日 下午1:51:26 
**/
public class DemoServiceImpl implements DemoService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
    public String sayHello(String name) {
        return "Hello " + name;
    }

	@Override
	public Valid submitPersons(String token, Date date, City city, List<Person> persons) {
		// TODO Auto-generated method stub
		logger.info(token + ";" + date + ";" + city);
		for (Person p : persons) {
			logger.info(p.getName());
		}
		return Valid.SUCCESS;
	}
}