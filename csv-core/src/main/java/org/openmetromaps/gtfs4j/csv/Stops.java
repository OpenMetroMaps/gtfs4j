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

public enum Stops
		implements
		Field {

	ID("stop_id", true),
	CODE("stop_code", false),
	NAME("stop_name", true),
	DESC("stop_desc", false),
	LAT("stop_lat", true),
	LON("stop_lon", true),
	ZONE_ID("zone_id", false),
	URL("stop_url", false),
	LOCATION_TYPE("location_type", false),
	PARENT_STATION("parent_station", false),
	TIMEZONE("stop_timezone", false),
	WHEELCHAIR_BOARDING("wheelchair_boarding", false);

	private String csvName;
	private boolean required;

	Stops(String csvName, boolean required)
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
