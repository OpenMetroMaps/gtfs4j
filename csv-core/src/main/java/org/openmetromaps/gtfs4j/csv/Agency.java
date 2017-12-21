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

public enum Agency
		implements
		Field {

	ID("agency_id", false),
	NAME("agency_name", true),
	URL("agency_url", true),
	TIMEZONE("agency_timezone", true),
	LANG("agency_lang", false),
	PHONE("agency_phone", false),
	FARE_URL("agency_fare_url", false),
	EMAIL("agency_email", false);

	private String csvName;
	private boolean required;

	Agency(String csvName, boolean required)
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
