package com.scope.challenge.utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class {@code FileUtility} includes utility methods to read and write file
 * 
 * @author Mohit.Saluja
 *
 */
/**
 * @author Mohit.Saluja
 *
 */
public class FileUtility {

	private static final String BACKWARD_SLASH_N = "\n";
	private static Logger logger = LoggerFactory.getLogger(FileUtility.class);

	public static List<String> readFile(String inputFile) {
		List<String> input = new ArrayList<>();
		try (FileInputStream fileInputStream = new FileInputStream(inputFile); BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream))) {

			String inputTalk = null;
			// Read input file line by line and ignores empty lines
			while ((inputTalk = bufferedReader.readLine()) != null) {
				if (inputTalk.isEmpty())
					continue;
				logger.info(inputTalk);
				input.add(inputTalk);
			}
		} catch (FileNotFoundException e) {
			logger.error("FileNotFoundException in readFile", e);
		} catch (IOException e) {
			logger.error("IOException in readFile", e);
		}
		return input;
	}

	/**
	 * This method iterates provided list and writes its content to outputFile
	 * 
	 * @param output
	 * @param outputFile
	 */
	public static void writeFile(List<String> output, String outputFile) {
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))) {
			// Write output list line by line
			for (Iterator<String> iterator = output.iterator(); iterator.hasNext();) {
				String string = iterator.next();
				logger.info(string);
				bufferedWriter.write(string + BACKWARD_SLASH_N);
			}
		} catch (IOException e) {
			logger.error("IOException in writeFile", e);
		}
	}

	private FileUtility() {
		super();
	}

}
