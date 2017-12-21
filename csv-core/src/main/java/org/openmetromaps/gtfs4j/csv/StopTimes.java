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

public enum StopTimes
		implements
		Field {

	TRIP_ID("trip_id", true),
	ARRVIAL_TIME("arrival_time", true),
	DEPARTURE_TIME("departure_time", true),
	STOP_ID("stop_id", true),
	STOP_SEQUENCE("stop_sequence", true),
	STOP_HEADSIGN("stop_headsign", false),
	PICKUP_TYPE("pickup_type", false),
	DROP_OFF_TYPE("drop_off_type", false),
	SHAPE_DIST_TRAVELED("shape_dist_traveled", false),
	TIMEPOINT("timepoint", false);

	private String csvName;
	private boolean required;

	StopTimes(String csvName, boolean required)
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
