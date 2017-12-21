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

package org.openmetromaps.gtfs4j.csv;

public enum Trips
		implements
		Field {

	ROUTE_ID("route_id", true),
	SERVICE_ID("service_id", true),
	ID("trip_id", true),
	HEADSIGN("trip_headsign", false),
	SHORT_NAME("trip_short_name", false),
	DIRECTION_ID("direction_id", false),
	BLOCK_ID("block_id", false),
	SHAPE_ID("shape_id", false),
	WHEELCHAIR_ACCESSIBLE("wheelchair_accessible", false),
	BIKES_ALLOWED("bikes_allowed", false);

	private String csvName;
	private boolean required;

	Trips(String csvName, boolean required)
	{
		this.csvName = csvName;
		this.required = required;
	}

	@Override
	public String getCsvName()
	{
		return csvName;
	}

	@Override
	public boolean isRequired()
	{
		return required;
	}

}
