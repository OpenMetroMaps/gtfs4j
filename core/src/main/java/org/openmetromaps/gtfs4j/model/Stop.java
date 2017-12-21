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

public class Stop
{

	// required
	private String id;

	// optional
	private String code;

	// required
	private String name;

	// optional
	private String desc;

	// required
	private String lat;
	private String lon;

	// optional
	private String zoneId;
	private String url;
	private String locationType;
	private String parentStation;
	private String timezone;
	private String wheelchairBoarding;

	public Stop(String id, String name, String lat, String lon)
	{
		this.id = id;
		this.name = name;
		this.lat = lat;
		this.lon = lon;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDesc()
	{
		return desc;
	}

	public void setDesc(String desc)
	{
		this.desc = desc;
	}

	public String getLat()
	{
		return lat;
	}

	public void setLat(String lat)
	{
		this.lat = lat;
	}

	public String getLon()
	{
		return lon;
	}

	public void setLon(String lon)
	{
		this.lon = lon;
	}

	public String getZoneId()
	{
		return zoneId;
	}

	public void setZoneId(String zoneId)
	{
		this.zoneId = zoneId;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getLocationType()
	{
		return locationType;
	}

	public void setLocationType(String locationType)
	{
		this.locationType = locationType;
	}

	public String getParentStation()
	{
		return parentStation;
	}

	public void setParentStation(String parentStation)
	{
		this.parentStation = parentStation;
	}

	public String getTimezone()
	{
		return timezone;
	}

	public void setTimezone(String timezone)
	{
		this.timezone = timezone;
	}

	public String getWheelchairBoarding()
	{
		return wheelchairBoarding;
	}

	public void setWheelchairBoarding(String wheelchairBoarding)
	{
		this.wheelchairBoarding = wheelchairBoarding;
	}

}
