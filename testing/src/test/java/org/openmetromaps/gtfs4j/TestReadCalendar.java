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

package org.openmetromaps.gtfs4j;

import java.io.IOException;
import java.util.List;

import org.openmetromaps.gtfs4j.csvreader.Test;
import org.openmetromaps.gtfs4j.model.Calendar;

public class TestReadCalendar
{

	public static void main(String[] args) throws IOException
	{
		List<Calendar> calendars = Test.readCalendar();

		for (Calendar calendar : calendars) {
			System.out.println(
					String.format("%s: %s, %s", calendar.getServiceId(),
							calendar.getStartDate(), calendar.getEndDate()));
		}
	}

}
