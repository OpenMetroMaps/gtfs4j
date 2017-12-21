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

public class Trip
{

	// required
	private String routeId;
	private String serviceId;
	private String id;

	// optional
	private String headsign;
	private String shortName;
	private String directionId;
	private String blockId;
	private String shapeId;
	private String wheelchairAccessible;
	private String bikesAllowed;

	public Trip(String routeId, String serviceId, String id)
	{
		this.routeId = routeId;
		this.serviceId = serviceId;
		this.id = id;
	}

	public String getRouteId()
	{
		return routeId;
	}

	public void setRouteId(String routeId)
	{
		this.routeId = routeId;
	}

	public String getServiceId()
	{
		return serviceId;
	}

	public void setServiceId(String serviceId)
	{
		this.serviceId = serviceId;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getHeadsign()
	{
		return headsign;
	}

	public void setHeadsign(String headsign)
	{
		this.headsign = headsign;
	}

	public String getShortName()
	{
		return shortName;
	}

	public void setShortName(String shortName)
	{
		this.shortName = shortName;
	}

	public String getDirectionId()
	{
		return directionId;
	}

	public void setDirectionId(String directionId)
	{
		this.directionId = directionId;
	}

	public String getBlockId()
	{
		return blockId;
	}

	public void setBlockId(String blockId)
	{
		this.blockId = blockId;
	}

	public String getShapeId()
	{
		return shapeId;
	}

	public void setShapeId(String shapeId)
	{
		this.shapeId = shapeId;
	}

	public String getWheelchairAccessible()
	{
		return wheelchairAccessible;
	}

	public void setWheelchairAccessible(String wheelchairAccessible)
	{
		this.wheelchairAccessible = wheelchairAccessible;
	}

	public String getBikesAllowed()
	{
		return bikesAllowed;
	}

	public void setBikesAllowed(String bikesAllowed)
	{
		this.bikesAllowed = bikesAllowed;
	}

}
