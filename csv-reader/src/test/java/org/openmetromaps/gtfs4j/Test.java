package org.openmetromaps.gtfs4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.openmetromaps.gtfs4j.csv.GtfsFiles;
import org.openmetromaps.gtfs4j.csvreader.RoutesReader;
import org.openmetromaps.gtfs4j.csvreader.StopTimesReader;
import org.openmetromaps.gtfs4j.csvreader.StopsReader;
import org.openmetromaps.gtfs4j.csvreader.TripsReader;
import org.openmetromaps.gtfs4j.model.Route;
import org.openmetromaps.gtfs4j.model.Stop;
import org.openmetromaps.gtfs4j.model.StopTime;
import org.openmetromaps.gtfs4j.model.Trip;

public class Test
{

	public static Path path = Paths.get("/tmp/gtfs");

	private static BufferedReader reader(Path path) throws IOException
	{
		return Files.newBufferedReader(path, StandardCharsets.UTF_8);
	}

	public static List<Route> readRoutes() throws IOException
	{
		Path path = Test.path.resolve(GtfsFiles.NAME_ROUTES);
		BufferedReader br = reader(path);

		RoutesReader reader = new RoutesReader(br);
		List<Route> list = reader.readAll();
		br.close();
		return list;
	}

	public static List<Trip> readTrips() throws IOException
	{
		Path path = Test.path.resolve(GtfsFiles.NAME_TRIPS);
		BufferedReader br = reader(path);

		TripsReader reader = new TripsReader(br);
		List<Trip> list = reader.readAll();
		br.close();
		return list;
	}

	public static List<Stop> readStops() throws IOException
	{
		Path path = Test.path.resolve(GtfsFiles.NAME_STOPS);
		BufferedReader br = reader(path);

		StopsReader reader = new StopsReader(br);
		List<Stop> list = reader.readAll();
		br.close();
		return list;
	}

	public static List<StopTime> readStopTimes() throws IOException
	{
		Path path = Test.path.resolve(GtfsFiles.NAME_STOP_TIMES);
		BufferedReader br = reader(path);

		StopTimesReader reader = new StopTimesReader(br);
		List<StopTime> list = reader.readAll();
		br.close();
		return list;
	}

}
