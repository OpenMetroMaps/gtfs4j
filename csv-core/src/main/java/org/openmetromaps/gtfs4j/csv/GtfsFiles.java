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
	AGENCY("agency.txt", true),
	STOPS("stops.txt", true),
	ROUTES("routes.txt", true),
	TRIPS("trips.txt", true),
	STOP_TIMES("stop_times.txt", true),
	CALENDAR("calendar.txt", true),

	// Optional files
	CALENDAR_DATES("calendar_dates.txt", false),
	FARE_ATTRIBUTES("fare_attributes.txt", false),
	FARE_RULES("fare_rules.txt", false),
	SHAPES("shapes.txt", false),
	FREQUENCIES("frequencies.txt", false),
	TRANSFERS("transfers.txt", false),
	FEED_INFO("feed_info.txt", false);

	private String filename;

	private GtfsFiles(String filename, boolean required)
	{
		this.filename = filename;
	}

	public String getFilename()
	{
		return filename;
	}

}
