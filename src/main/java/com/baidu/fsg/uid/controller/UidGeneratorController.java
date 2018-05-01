package com.baidu.fsg.uid.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baidu.fsg.uid.UidGenerator;

@RestController
public class UidGeneratorController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UidGeneratorController.class);
	
    @Autowired    
    private UidGenerator uidGenerator;
    
    @RequestMapping(value="/uid", method=RequestMethod.GET)
	public long generateUid() {
		long uid = uidGenerator.getUID();
		
        String parsedInfo = uidGenerator.parseUID(uid);
        LOGGER.debug(parsedInfo);
        
        return uid;
	}
    
    @RequestMapping(value="/uid/{count}", method=RequestMethod.GET)
	public List<Long> generateUids(@PathVariable("count") int count) {
    	List<Long> uids = new ArrayList<Long>(0);
    	for(int i=0; i<count; i++) {
			long uid = uidGenerator.getUID();
			uids.add(uid);
			
	        String parsedInfo = uidGenerator.parseUID(uid);
	        LOGGER.debug(parsedInfo);
    	}
        
        return uids;
	}
}
