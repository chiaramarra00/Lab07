package it.polito.tdp.poweroutages.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class PowerOutage implements Comparable<PowerOutage>{
	
	private int id;
	private int eventTypeId;
	private int tagId;
	private int areaId;
	private Nerc nercId;
	private int responsibleId;
	private int customersAffected;
	private LocalDateTime dateEventBegan;
	private LocalDateTime dateEventFinished;
	private int demandLoss;
	
	public PowerOutage(int id, int eventTypeId, int tagId, int areaId, Nerc nercId, int responsibleId,
			int customersAffected, LocalDateTime dateEventBegan, LocalDateTime dateEventFinished, int demandLoss) {
		super();
		this.setId(id);
		this.setEventTypeId(eventTypeId);
		this.setTagId(tagId);
		this.setAreaId(areaId);
		this.setNercId(nercId);
		this.setResponsibleId(responsibleId);
		this.customersAffected = customersAffected;
		this.dateEventBegan = dateEventBegan;
		this.dateEventFinished = dateEventFinished;
		this.setDemandLoss(demandLoss);
	}

	public LocalDateTime getDateEventBegan() {
		return dateEventBegan;
	}

	public LocalDateTime getDateEventFinished() {
		return dateEventFinished;
	}
	
	public int getCustomersAffected() {
		return customersAffected;
	}

	public long getDuration() {
		LocalDateTime temp = LocalDateTime.from(dateEventBegan);
		long duration = temp.until(dateEventFinished, ChronoUnit.HOURS);
		return duration; 
	}

	@Override
	public String toString() {
		return dateEventBegan.getYear() + " " + dateEventBegan + " " + dateEventFinished + " " + getDuration() + " " + customersAffected;
	}

	@Override
	public int compareTo(PowerOutage o) {
		return dateEventBegan.compareTo(o.getDateEventBegan());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEventTypeId() {
		return eventTypeId;
	}

	public void setEventTypeId(int eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public Nerc getNercId() {
		return nercId;
	}

	public void setNercId(Nerc nercId) {
		this.nercId = nercId;
	}

	public int getResponsibleId() {
		return responsibleId;
	}

	public void setResponsibleId(int responsibleId) {
		this.responsibleId = responsibleId;
	}

	public int getDemandLoss() {
		return demandLoss;
	}

	public void setDemandLoss(int demandLoss) {
		this.demandLoss = demandLoss;
	}
	
}
