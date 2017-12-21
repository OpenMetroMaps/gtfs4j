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

package org.openmetromaps.gtfs4j.csvreader;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.openmetromaps.gtfs4j.model.StopTime;

import au.com.bytecode.opencsv.CSVReader;

public class StopTimesReader
{

	private CSVReader csvReader;

	private int idxTripId;
	private int idxArrivalTime;
	private int idxDepartureTime;
	private int idxStopId;
	private int idxStopSequence;

	public StopTimesReader(Reader reader) throws IOException
	{
		csvReader = Util.defaultCsvReader(reader);

		String[] head = csvReader.readNext();

		idxTripId = Util.getIndex(head, "trip_id");
		idxArrivalTime = Util.getIndex(head, "arrival_time");
		idxDepartureTime = Util.getIndex(head, "departure_time");
		idxStopId = Util.getIndex(head, "stop_id");
		idxStopSequence = Util.getIndex(head, "stop_sequence");
	}

	public List<StopTime> readAll() throws IOException
	{
		List<StopTime> results = new ArrayList<>();

		while (true) {
			String[] parts = csvReader.readNext();
			if (parts == null) {
				break;
			}
			String tripId = parts[idxTripId];
			String arrivaleTime = parts[idxArrivalTime];
			String departureTime = parts[idxDepartureTime];
			String stopId = parts[idxStopId];
			String stopSequence = parts[idxStopSequence];
			results.add(new StopTime(tripId, arrivaleTime, departureTime,
					stopId, stopSequence));
		}

		csvReader.close();

		return results;
	}

}
