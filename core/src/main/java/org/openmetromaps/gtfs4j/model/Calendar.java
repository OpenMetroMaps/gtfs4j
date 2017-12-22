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

public class Calendar
{

	// required
	private String serviceId;
	private String monday;
	private String tuesday;
	private String wednesday;
	private String thursday;
	private String friday;
	private String saturday;
	private String sunday;
	private String startDate;
	private String endDate;

	public Calendar(String serviceId, String monday, String tuesday,
			String wednesday, String thursday, String friday, String saturday,
			String sunday, String startDate, String endDate)
	{
		this.serviceId = serviceId;
		this.monday = monday;
		this.tuesday = tuesday;
		this.wednesday = wednesday;
		this.thursday = thursday;
		this.friday = friday;
		this.saturday = saturday;
		this.sunday = sunday;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getServiceId()
	{
		return serviceId;
	}

	public void setServiceId(String serviceId)
	{
		this.serviceId = serviceId;
	}

	public String getMonday()
	{
		return monday;
	}

	public void setMonday(String monday)
	{
		this.monday = monday;
	}

	public String getTuesday()
	{
		return tuesday;
	}

	public void setTuesday(String tuesday)
	{
		this.tuesday = tuesday;
	}

	public String getWednesday()
	{
		return wednesday;
	}

	public void setWednesday(String wednesday)
	{
		this.wednesday = wednesday;
	}

	public String getThursday()
	{
		return thursday;
	}

	public void setThursday(String thursday)
	{
		this.thursday = thursday;
	}

	public String getFriday()
	{
		return friday;
	}

	public void setFriday(String friday)
	{
		this.friday = friday;
	}

	public String getSaturday()
	{
		return saturday;
	}

	public void setSaturday(String saturday)
	{
		this.saturday = saturday;
	}

	public String getSunday()
	{
		return sunday;
	}

	public void setSunday(String sunday)
	{
		this.sunday = sunday;
	}

	public String getStartDate()
	{
		return startDate;
	}

	public void setStartDate(String startDate)
	{
		this.startDate = startDate;
	}

	public String getEndDate()
	{
		return endDate;
	}

	public void setEndDate(String endDate)
	{
		this.endDate = endDate;
	}

}
