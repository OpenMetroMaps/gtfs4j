// Copyright 2017 Sebastian Kuerten
//
// This file is part of OpenMetroMaps.
//
// OpenMetroMaps is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// OpenMetroMaps is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with OpenMetroMaps. If not, see <http://www.gnu.org/licenses/>.

package org.openmetromaps.gtfs4j.cli;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.openmetromaps.gtfs4j.csv.GtfsFiles;

public class ShowInfo
{

	private Path pathInput;

	public ShowInfo(Path pathInput)
	{
		this.pathInput = pathInput;
	}

	public void execute() throws IOException
	{
		System.out.println("Input: " + pathInput);

		long size = Files.size(pathInput);
		System.out.println(String.format("File size: %d bytes", size));

		ZipFile zip = new ZipFile(pathInput.toFile());

		print(zip, GtfsFiles.AGENCY);
		print(zip, GtfsFiles.STOPS);
	}

	private static void print(ZipFile zip, GtfsFiles path)
	{
		ZipEntry entry = zip.getEntry(path.getFilename());
		if (entry == null) {
			System.out.println(String.format("%s: not found", path));
			return;
		}
		long size = entry.getSize();
		System.out.println(String.format("%s: %d bytes", path, size));
	}

}
