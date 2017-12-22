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
// but WITHOUT ANY WARRANTY), without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with gtfs4j. If not, see <http://www.gnu.org/licenses/>.

package org.openmetromaps.gtfs4j.csv;

public enum GtfsFiles {

	// Required files
	AGENCY("agency.txt", true, Agencies.class),
	STOPS("stops.txt", true, Stops.class),
	ROUTES("routes.txt", true, Routes.class),
	TRIPS("trips.txt", true, Trips.class),
	STOP_TIMES("stop_times.txt", true, StopTimes.class),
	CALENDAR("calendar.txt", true, Calendars.class),

	// Optional files
	CALENDAR_DATES("calendar_dates.txt", false, null),
	FARE_ATTRIBUTES("fare_attributes.txt", false, null),
	FARE_RULES("fare_rules.txt", false, null),
	SHAPES("shapes.txt", false, null),
	FREQUENCIES("frequencies.txt", false, null),
	TRANSFERS("transfers.txt", false, null),
	FEED_INFO("feed_info.txt", false, null);

	private String filename;
	private boolean required;
	private Class<? extends Field> clazz;

	private GtfsFiles(String filename, boolean required,
			Class<? extends Field> clazz)
	{
		this.filename = filename;
		this.required = required;
		this.clazz = clazz;
	}

	public String getFilename()
	{
		return filename;
	}

	public boolean isRequired()
	{
		return required;
	}

	public Class<? extends Field> getFieldClass()
	{
		return clazz;
	}

}
