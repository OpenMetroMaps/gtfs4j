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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.openmetromaps.gtfs4j.csv.Field;
import org.openmetromaps.gtfs4j.csv.GtfsFiles;
import org.openmetromaps.gtfs4j.csvreader.Util;

import au.com.bytecode.opencsv.CSVReader;

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

		for (GtfsFiles file : GtfsFiles.values()) {
			// TODO: remove this after implementing all field classes
			if (file.getFieldClass() == null) {
				continue;
			}
			System.out.println(file.getFilename());
			printFields(zip, file, file.getFieldClass());
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

	private void printFields(ZipFile zip, GtfsFiles file,
			Class<? extends Field> clazz) throws IOException
	{
		ZipEntry entry = zip.getEntry(file.getFilename());
		if (entry == null) {
			return;
		}
		InputStreamReader isr = CliUtil.reader(zip, file);
		BufferedReader reader = new BufferedReader(isr);

		CSVReader csvReader = Util.defaultCsvReader(reader);
		String[] head = csvReader.readNext();

		Field[] fields = clazz.getEnumConstants();
		for (Field field : fields) {
			int index = Util.getIndex(head, field.getCsvName());
			System.out.println(
					String.format("  %s: %d", field.getCsvName(), index));
		}
	}

}
