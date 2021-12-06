package com.sample.microservices.common.util;

import java.util.SortedSet;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;

@Data
public class ComparableTime implements Comparable<ComparableTime>{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ComparableTime.class);
	
	private String time;
	private byte hour;
	private byte min;
	private byte sec;
	
	public ComparableTime(String time) {
		
		try {
			String[] times = time.split(":");
			
			this.hour = Byte.parseByte(times[0]);
			this.min = Byte.parseByte(times[1]);
			this.sec = Byte.parseByte(times[2]);
			
			this.time = time;
		} catch (Exception e) {
			this.time = "00:00:00";
			LOGGER.error("Invalid time format: ", e);
			
		}
	}

	@Override
	public int compareTo(ComparableTime o) {

		if(this.hour > o.hour || (this.hour == o.hour && this.min > o.min )
				|| (this.hour == o.hour && this.min == o.min && this.sec > o.sec) )
			return 1;
		
		if(this.hour == o.hour && this.min == o.min && this.sec == o.sec)
			return 0;
		
		return -1;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hour;
		result = prime * result + min;
		result = prime * result + sec;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComparableTime other = (ComparableTime) obj;
		if (hour != other.hour || min != other.min || sec != other.sec)
			return false;
		
		return true;
	}

	public static void main(String[] args) {

		SortedSet<ComparableTime> ss = new TreeSet<>();
		
		ss.add(new ComparableTime("01:10:20"));
		ss.add(new ComparableTime("01:10:20"));
		ss.add(new ComparableTime("02:10:20"));
		ss.add(new ComparableTime("03:10:20"));
		ss.add(new ComparableTime("03:20:50"));
		ss.add(new ComparableTime("03:30:10"));
		
		System.out.println(ss.last().getTime());

	}

	
}
