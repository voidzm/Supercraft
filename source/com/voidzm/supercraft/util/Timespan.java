package com.voidzm.supercraft.util;

public class Timespan {

	private int startTime;
	private int endTime;
	
	public Timespan(int start, int end) {
		this.startTime = start;
		this.endTime = end;
	}
	
	public int getStart() {
		return this.startTime;
	}
	
	public int getEnd() {
		return this.endTime;
	}
	
}
