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

public enum Calendar
		implements
		Field {

	SERVICE_ID("service_id", true),
	MONDAY("monday", true),
	TUESDAY("tuesday", true),
	WEDNESDAY("wednesday", true),
	THURSDAY("thursday", true),
	FRIDAY("friday", true),
	SATURDAY("saturday", true),
	SUNDAY("sunday", true),
	START_DATE("start_date", true),
	END_DATE("end_date", true);

	private String csvName;
	private boolean required;

	Calendar(String csvName, boolean required)
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
