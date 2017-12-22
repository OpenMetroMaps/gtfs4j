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
		int maxLen = 0;
		for (GtfsFiles file : GtfsFiles.values()) {
			maxLen = Math.max(maxLen, file.getFilename().length());
		}

		print("File path", maxLen, "%s", pathInput);

		long size = Files.size(pathInput);
		print("File size", maxLen, "%d bytes", size);

		ZipFile zip = new ZipFile(pathInput.toFile());

		for (GtfsFiles file : GtfsFiles.values()) {
			print(zip, file, maxLen);
		}
	}

	private void print(ZipFile zip, GtfsFiles file, int pad)
	{
		ZipEntry entry = zip.getEntry(file.getFilename());
		if (entry == null) {
			print(file.getFilename(), pad, "not found (%s)",
					file.isRequired() ? "required" : "optional");
			return;
		}
		long size = entry.getSize();
		print(file.getFilename(), pad, "%d bytes", size);
	}

	private void print(String title, int pad, String format, Object... args)
	{
		StringBuilder buffer = new StringBuilder();
		buffer.append(title);
		buffer.append(":");
		buffer.append(spaces(title, pad));
		buffer.append(" ");
		buffer.append(String.format(format, args));
		System.out.println(buffer.toString());
	}

	private String spaces(String string, int pad)
	{
		int numSpaces = pad - string.length();
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < numSpaces; i++) {
			buffer.append(' ');
		}
		return buffer.toString();
	}

}
