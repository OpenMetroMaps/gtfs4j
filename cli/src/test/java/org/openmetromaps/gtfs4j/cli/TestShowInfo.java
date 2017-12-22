package org.openmetromaps.gtfs4j.cli;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestShowInfo
{

	public static void main(String[] args) throws IOException
	{
		Path path = Paths.get("/tmp/gtfs/test.zip");
		ShowInfo task = new ShowInfo(path);
		task.execute();
	}

}
