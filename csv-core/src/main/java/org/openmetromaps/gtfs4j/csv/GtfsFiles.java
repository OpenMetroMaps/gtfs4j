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
	NAME_AGENCY("agency.txt", true),
	NAME_STOPS("stops.txt", true),
	NAME_ROUTES("routes.txt", true),
	NAME_TRIPS("trips.txt", true),
	NAME_STOP_TIMES("stop_times.txt", true),
	NAME_CALENDAR("calendar.txt", true),

	// Optional files
	NAME_CALENDAR_DATES("calendar_dates.txt", false),
	NAME_FARE_ATTRIBUTES("fare_attributes.txt", false),
	NAME_FARE_RULES("fare_rules.txt", false),
	NAME_SHAPES("shapes.txt", false),
	NAME_FREQUENCIES("frequencies.txt", false),
	NAME_TRANSFERS("transfers.txt", false),
	NAME_FEED_INFO("feed_info.txt", false);

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
