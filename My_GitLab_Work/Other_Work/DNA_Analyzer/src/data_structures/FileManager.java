/*
 * Copyright (c) 2017, 2018.
 * 
 */
package data_structures;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * <p><b> File</b> class reads a file and saves each line inside a List<String>. Note that this class eliminates an empty lines and only trims the beginning </br>
 * and end of each line. The rest remain as row as there were inside the file; meaning among all saved characters in each line might</br>
 * exist white spaces and unnecessary signs as well.</p>
 * <p> {@link #FileManager()} Default Constructor.</p>
 * <p> {@link #FileManager(Path)} Constructor. Initializing an object so that it represents all contents of the given file located in filePath; </p>
 * in other words, represents a copy of the file line by line.</p>
 * <p> {@link #FileManager(Path, int)} Constructor. Initializes a new FileManager Object saving the given int as a header of the file.</p> 
 * <p> {@link #FileManager(Path, int, String)} Constructor. Initializes a new FileManager Object and uses last String argument to split each line of the file.</p>
 * <p> {@link #getPath()} Returns the path of the given file. </p>
 * <p> {@link #getAbsolutePath() Returns a String of file path.</p>
 * <p> {@link #header()} Returns the header line of the file or null if the file does not have any. </p>
 * <p> {@link #splittedHeader(){ Returns the spliced header by splitter. </p>
 * <p> {@link #getFileContent()} Returns all lines as a List of Strings.</p>
 * <p> {@link #getSplittedContent()} Returns all lines as a List of String arrays. </p>
 * <p> {@link #splitLines(String)} Splits each line based on an input and returns a list of String[].</p>
 * <p> {@link #linesContain(String...)} Returns all lines containing the given sequence. </p>
 * <p> {@link #linesContain(int[], String...)} Returns all lines in which their specific column contains the given sequence. </p>
 * <p> {@link #linesNotContain(String...)} Returns all lines Not containing the given sequence.</p>
 * <p> {@link #writeInTXTFile(Path, Object)} Writes the given output into the given file. </p>
 * <p> {@link #writeInTXTFile(Path, String, List)} Writes the given output with the given headerLine at the beginning into the given file. </p>
 * <p> {@link #writeInCsvFile(Path, String[], List)} Writes the given output with the given headerLine into the given CSV file. </p>   
 * <p> {@link #size()} Returns the number of existed lines.</p>
 * <p> {@link #sizeOfChars()} Returns the number of existed characters.</p>
 * <p> {@link #isEmpty()} true of false if the file the getFileContent is empty.</p>
 * <p> {@link #toString()} Builds a string from the file content.</p>
 * <p> {@link #hashCode()} Returns a hashCode of the FileManager object.</p>
 * @author amjadm@miamioh.edu.
 */
public final class FileManager {
	
	public FileManager() 																		{ super(); } /* Constructor. */
	public FileManager(Path txt_OR_CVS_FilePath) 												{ super(); readFile(txt_OR_CVS_FilePath, 0, null); } /* Constructor. */
	public FileManager(Path txt_OR_CVS_FilePath, int headerLineNumber)							{ super(); readFile(txt_OR_CVS_FilePath, headerLineNumber, null); } /* Constructor. */
	public FileManager(Path txt_OR_CVS_FilePath, int headerLineNumber, String splitBy)			{ super(); readFile(txt_OR_CVS_FilePath, headerLineNumber, splitBy); } /* Constructor. */
	public Path getPath()		 																{ return getFilePath(); }
	public String getAbsolutePath()																{ return getFilePath().toString(); }
	public String header()																		{ return getHeader(); }
	public String[] splittedHeader()															{ return getSplittedHeader(); }
	public List<String> getFileContent()														{ return getLines(); }
	public List<String[]> getSplittedContent()													{ return getSplittedLines(); }
	public List<String[]> splitLines(String regex)												{ return splitAllLines(regex); }
	public List<String> linesContain(String... sequence)										{ return LinesContaining(sequence); } 
	public List<String[]> linesContain(int[] index, String...sequence)							{ return LinesContaining(index, sequence); }
	public List<String> linesNotContain(String... sequence)										{ return LinesNotContaining(sequence); }
	public static boolean writeInTxtFile(Path path, List<?> output) 							{ return write(path, output); }
	public static boolean writeInTxtFile(Path path, String headerLine, List<?> output)			{ return write(path, headerLine, output); }
	public static boolean writeInCsvFile(Path path, String[] headerLine, List<String[]> output)	{ return writeCsv(path, headerLine, output); }
	public static boolean writeInCsvFile2(Path path, List<String> headerLine, List<List<String>> output)	{ return writeCsv2(path, headerLine, output); }
	public static String getDigits(String token)												{ return numberSeperator(token); }
	public int size() 																			{ return getNumLines(); }
	public int sizeOfChars() 																	{ return getNumChars(); }
	public boolean isEmpty()																	{ return getNumLines() == 0; }
	@Override
	public String toString() 																	{ return buildString(); };
	@Override
	public int hashCode() 																		{ return hash(); }
	
	/*	*************************************************
	 *	Below there exist all fields and private methods.
	 *	*************************************************
	 */
	
	/* Holds the file path. */
	private Path filePath = null;
	
	/* Holds each line of the file as a String. */
	private List<String> lines = new ArrayList<String>();
	
	/* Holds each line of the file as an array of Strings. */
	private List<String[]> splittedLines = new ArrayList<String[]>();
	
	/* Holds a header line belonging to a given file. */
	private String header = null;
	
	/* Holds a header line belonging to a given file which gets spliced by the given splitter. */
	private String[] splittedHeader = null;
	
	/* For holding the number of the all lines inside the file (Blank lines excluded). */
	private int nLines = 0;
	
	/* For holding the number of chars inside the file (White spaces and blanks excluded). */
	private int nChars = 0;
	
	/* Returns the filePath. */
	private Path getFilePath() { return this.filePath; }
	
	/* Returns a List of all lines. */
	private List<String> getLines() { return this.lines; }
	
	/* Returns a List of all spliced lines by splitter. */
	private List<String[]> getSplittedLines() { return this.splittedLines; }
	
	/* Returns the header line. */
	private String getHeader() { return this.header; }
	
	/* Returns the splitted header. */
	private String[] getSplittedHeader() { return this.splittedHeader; }
	
	/* Returns the number of lines. */
	private int getNumLines() {	return this.nLines;	}
	
	/* Returns the number of characters. */
	private int getNumChars() { return this.nChars;	}
	
	/* Calculates the hash code of this class. */
	private int hash() { return this.filePath.hashCode() ^ this.nLines ^ this.nChars; }
	
	/* Private method for reading the given file and counting the existed chars inside the file. */
	private void readFile(Path filePath, int headerLineNumber, String splitBy) {
		try (BufferedReader bfr = new BufferedReader(new FileReader(filePath.toFile()))) {
			String _newLine;
			this.filePath = filePath;
			List<String> fileContent = new ArrayList<>();
			List<String[]> splittedFileContent = new ArrayList<String[]>();
			while ((_newLine = bfr.readLine()) != null && (!_newLine.isEmpty())) {
				_newLine = _newLine.trim();
				fileContent.add(_newLine);
				this.nChars += _newLine.length();
				String[] sS = _newLine.split((splitBy == null)? "\t": splitBy);
				if (sS.length > 0)
					splittedFileContent.add(sS);
			}
			this.lines = fileContent;
			this.splittedLines = splittedFileContent;
			this.header = (headerLineNumber == 0)? null: this.lines.remove(headerLineNumber - 1);
			this.splittedHeader = (headerLineNumber == 0)? null: this.splittedLines.remove(headerLineNumber - 1);
			this.nLines = fileContent.size();
			bfr.close();
		} catch(FileNotFoundException e) {
			System.err.println(e.getStackTrace());
		} catch(IOException e) {
			System.err.println(e.getStackTrace());
		}
	}
	
	/* Reads all lines and split each based on the given regex and returns a list of String[].*/
	private List<String[]> splitAllLines(String regex) {
		if (isEmpty() || regex == null || regex.isEmpty() || regex.equals(""))
			return null;
		List<String[]> _newLines = new ArrayList<>();
		String[] _newLine;
		for (String line: lines)
			if ((_newLine = line.split(regex)).length > 0)
				_newLines.add(_newLine);
		this.splittedHeader = (this.header != null)? this.header.split(regex): null;
		this.splittedLines = _newLines;
		return _newLines;
	}
	
	/* Returns a list of lines containing the given sequence. */
	private List<String> LinesContaining(String... sequences) {
		if (isEmpty() || sequences == null || sequences.length == 0)
			return null;
		List<String> _newLines = new ArrayList<>();
		for (String line: lines) {
			boolean check = true;
			for (String sequence: sequences)
				if (!line.contains(sequence)) {
					check = false;
					break;
				}
			if (check)
				_newLines.add(line);
		}
		return (_newLines.isEmpty())? null: _newLines;
	}
	
	/* Returns a list of lines containing the given sequence. But first, it splits each line by the given regex, then only does the comparison between
	 * the given sequence and the nth given column number. */
	private List<String[]> LinesContaining(int[] column, String... sequences) {
		if (isEmpty() || sequences == null || sequences.length == 0 || column.length != sequences.length)
			return null;
		List<String[]> _newLines = new ArrayList<>();
		for (String[] line: this.splittedLines) {
				boolean check = true;
				for (int i = 0; i < column.length ; i++) {
					if ( line[column[i]] == null || (line[column[i]] != null && !line[column[i]].contains(sequences[i]))) {
						check = false;
						break;
					}
				}
				if (check)
					_newLines.add(line);
		}
		return _newLines;
	}
	
	/* Returns a list of lines containing the given sequence. */
	private List<String> LinesNotContaining(String... sequences) {
		if (isEmpty() || sequences == null || sequences.length == 0)
			return null;
		List<String> _newLines = new ArrayList<>();
		for (String line: lines) {
			boolean check = true;
			for (String sequence: sequences)
				if (line.contains(sequence)) {
					check = false;
					break;
				}
			if (check)
				_newLines.add(line);
		}
		return (_newLines.isEmpty())? null: _newLines;
	}
	
	/* Writes either a list of <String> or <String[]> into the given file and returns true if it has been successful. Otherwise, returns false. */
	@SuppressWarnings("unchecked")
	private static boolean write(Path path, String header,Object output)  {
		if ( output == null || output == null || header == null)
			return false;
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile()))) {
			if (header.length() > 2)
				writer.write(header + "\n");
			try{
				writeSringArrays((List<String[]>) output, writer) ;
			}catch(ClassCastException e) {
				try{
					writeStrings((List<String>) output, writer) ;
				}catch(ClassCastException er) {
					return false;
				}
				writer.close();
			}
		} catch (IOException x) {
		    return false;
		}
		return true;
	}
	
	/* write the output into the given path with the given header line at the begining of the file. */
	private static boolean write(Path path, Object output)  {
		return write(path, "", output);
	}
	
	/* Writes a list of string into the file.*/
	private static void writeStrings(List<String> output , BufferedWriter bfr) throws IOException {
		for (String line: output)
			bfr.write(line + "\n");
	}
	
	/* Writes a list of string Arrays into the file.*/
	private static void  writeSringArrays(List<String[]> output , BufferedWriter bfr) throws IOException {
		StringBuilder wholeLine = null;
		for (String[] line: output) {
			wholeLine = new StringBuilder();
			for (String tokens: line)
				wholeLine.append(tokens + "\t");
			wholeLine.delete(wholeLine.length()-1, wholeLine.length());
			wholeLine.append("\n");
			bfr.write(wholeLine.toString());
		}
	}
	
	/* Writes a list of string Arrays into the CSV file.*/
	private static boolean writeCsv(Path path, String[] headerLine, List<String[]> output) {
		if ( output == null || output == null || headerLine == null)
			return false;
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile()))) {
			StringBuilder st = new StringBuilder();
			for (String header : headerLine)
				st.append(header + ",");
			st.deleteCharAt(st.lastIndexOf(",")); //removing the last extra "," sign
			st.append("\n");
			writer.write(st.toString());
			for (String[] line: output) {
				st = new StringBuilder();
				for (String tokens: line)
					st.append(tokens + ",");
				st.deleteCharAt(st.lastIndexOf(",")); //removing the last extra "," sign
				st.append("\n");
				writer.write(st.toString());
			}
		}catch(ClassCastException | IOException er) {
			return false;
		}
		return true;
	}
	
	/* Writes a list of string Arrays into the CSV file.*/
	private static boolean writeCsv2(Path path, List<String> headerLine, List<List<String>> output) {
		if ( output == null || output == null || headerLine == null)
			return false;
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile()))) {
			StringBuilder st = new StringBuilder();
			for (String header : headerLine)
				st.append(header + ",");
			st.deleteCharAt(st.lastIndexOf(",")); //removing the last extra "," sign
			st.append("\n");
			writer.write(st.toString());
			for (List<String> line: output) {
				st = new StringBuilder();
				for (String tokens: line)
					st.append(tokens + ",");
				st.deleteCharAt(st.lastIndexOf(",")); //removing the last extra "," sign
				st.append("\n");
				writer.write(st.toString());
			}
		}catch(ClassCastException | IOException er) {
			return false;
		}
		return true;
	}
	
	/* Seperates the digit part of the given String*/
	private static String numberSeperator(String token) {
		if (token == null)
			throw new NullPointerException("The Input Token in null");
		StringBuilder output = new StringBuilder();
		for (int i = 0; i < token.length() ; i++)
			if (isDigit(token.charAt(i)))
				output.append(token.charAt(i));
		return output.toString();
	}
	
	/* Checks if the given char is a digit or not.*/
	private static boolean isDigit(char ch) {
		try{
			Integer.valueOf(String.valueOf(ch));
			return true;
		}catch(NumberFormatException e) {
			return false;
		}
	}
	
	/* Builds a string from all characters in each line and returns it for the use of {@link #ToString()} method. */
	private String buildString() {
		if (isEmpty())
			return null;
		StringBuilder _content = new StringBuilder();
		for (String line : this.lines)
 				_content.append(line + "\n");
		return _content.toString();
	}
}

