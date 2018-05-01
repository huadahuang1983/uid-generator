package com.baidu.fsg.uid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.baidu.fsg.uid.impl.CachedUidGenerator;
import com.baidu.fsg.uid.impl.DefaultUidGenerator;
import com.baidu.fsg.uid.worker.DisposableWorkerIdAssigner;

@Configuration
@EnableConfigurationProperties(UidGeneratorProperties.class)
@EnableAutoConfiguration
@ComponentScan(basePackages="com.baidu.fsg.uid")
public class UidGeneratorConfiguration {
	
	@Autowired	
	private UidGeneratorProperties uidGeneratorProperties;
	
	@Bean
	public DisposableWorkerIdAssigner disposableWorkerIdAssigner() {
		return new DisposableWorkerIdAssigner();
	}
	
	@Bean	
	public UidGenerator uidGenerator() {
		if(uidGeneratorProperties.getEnableCached() == null 
				|| uidGeneratorProperties.getEnableCached() == false) {
			DefaultUidGenerator defaultUidGenerator = new DefaultUidGenerator();
					
			defaultUidGenerator.setWorkerIdAssigner(disposableWorkerIdAssigner());
			defaultUidGenerator.setTimeBits(uidGeneratorProperties.getTimeBits());
			defaultUidGenerator.setWorkerBits(uidGeneratorProperties.getWorkerBits());
			defaultUidGenerator.setSeqBits(uidGeneratorProperties.getSeqBits());
			defaultUidGenerator.setEpochStr(uidGeneratorProperties.getEpochStr());
			
			return defaultUidGenerator;
		}else {
			CachedUidGenerator cachedUidGenerator = new CachedUidGenerator();
			
			cachedUidGenerator.setWorkerIdAssigner(disposableWorkerIdAssigner());
			cachedUidGenerator.setTimeBits(uidGeneratorProperties.getTimeBits());
			cachedUidGenerator.setWorkerBits(uidGeneratorProperties.getWorkerBits());
			cachedUidGenerator.setSeqBits(uidGeneratorProperties.getSeqBits());
			cachedUidGenerator.setEpochStr(uidGeneratorProperties.getEpochStr());
			
			cachedUidGenerator.setBoostPower(uidGeneratorProperties.getBoostPower());
			cachedUidGenerator.setScheduleInterval(uidGeneratorProperties.getScheduleInterval());
			
			return cachedUidGenerator;
		}
	}
	 
}
