package com.baidu.fsg.uid;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("uidgenerator")
public class UidGeneratorProperties implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5396838616246819974L;
	
	private Boolean enableCached = false;
	
	/** Bits allocate */
    protected int timeBits = 28;
    protected int workerBits = 22;
    protected int seqBits = 13;

    /** Customer epoch, unit as second. For example 2016-05-20 (ms: 1463673600000)*/
    protected String epochStr = "2018-05-01";
    
    /** Spring properties */
    private int boostPower = 3;    
    private Long scheduleInterval = 60L;

	public int getTimeBits() {
		return timeBits;
	}

	public void setTimeBits(int timeBits) {
		this.timeBits = timeBits;
	}

	public int getWorkerBits() {
		return workerBits;
	}

	public void setWorkerBits(int workerBits) {
		this.workerBits = workerBits;
	}

	public int getSeqBits() {
		return seqBits;
	}

	public void setSeqBits(int seqBits) {
		this.seqBits = seqBits;
	}

	public String getEpochStr() {
		return epochStr;
	}

	public void setEpochStr(String epochStr) {
		this.epochStr = epochStr;
	}

	public int getBoostPower() {
		return boostPower;
	}

	public void setBoostPower(int boostPower) {
		this.boostPower = boostPower;
	}

	public Long getScheduleInterval() {
		return scheduleInterval;
	}

	public void setScheduleInterval(Long scheduleInterval) {
		this.scheduleInterval = scheduleInterval;
	}

	public Boolean getEnableCached() {
		return enableCached;
	}

	public void setEnableCached(Boolean enableCached) {
		this.enableCached = enableCached;
	}	
	
}
