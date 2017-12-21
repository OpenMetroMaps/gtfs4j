// Copyright 2017 Sebastian Kuerten
//
// This file is part of gtfs4j.
//
// gtfs4j is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// gtfs4j is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with gtfs4j. If not, see <http://www.gnu.org/licenses/>.

package org.openmetromaps.gtfs4j.model;

public class StopTime
{

	// required
	private String tripId;
	private String arrivalTime;
	private String departureTime;
	private String stopId;
	private String stopSequence;

	// optional
	private String stopHeadsign;
	private String pickupType;
	private String dropOffType;
	private String shapeDistTraveled;
	private String timepoint;

	public StopTime(String tripId, String arrivalTime, String departureTime,
			String stopId, String stopSequence)
	{
		this.tripId = tripId;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.stopId = stopId;
		this.stopSequence = stopSequence;
	}

	public String getTripId()
	{
		return tripId;
	}

	public void setTripId(String tripId)
	{
		this.tripId = tripId;
	}

	public String getArrivalTime()
	{
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime)
	{
		this.arrivalTime = arrivalTime;
	}

	public String getDepartureTime()
	{
		return departureTime;
	}

	public void setDepartureTime(String departureTime)
	{
		this.departureTime = departureTime;
	}

	public String getStopId()
	{
		return stopId;
	}

	public void setStopId(String stopId)
	{
		this.stopId = stopId;
	}

	public String getStopSequence()
	{
		return stopSequence;
	}

	public void setStopSequence(String stopSequence)
	{
		this.stopSequence = stopSequence;
	}

	public String getStopHeadsign()
	{
		return stopHeadsign;
	}

	public void setStopHeadsign(String stopHeadsign)
	{
		this.stopHeadsign = stopHeadsign;
	}

	public String getPickupType()
	{
		return pickupType;
	}

	public void setPickupType(String pickupType)
	{
		this.pickupType = pickupType;
	}

	public String getDropOffType()
	{
		return dropOffType;
	}

	public void setDropOffType(String dropOffType)
	{
		this.dropOffType = dropOffType;
	}

	public String getShapeDistTraveled()
	{
		return shapeDistTraveled;
	}

	public void setShapeDistTraveled(String shapeDistTraveled)
	{
		this.shapeDistTraveled = shapeDistTraveled;
	}

	public String getTimepoint()
	{
		return timepoint;
	}

	public void setTimepoint(String timepoint)
	{
		this.timepoint = timepoint;
	}

}
