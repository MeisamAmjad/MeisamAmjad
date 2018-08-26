/*
 * Copyright (c) 2017, 2018.
 * 
 */
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import data_structures.*;

public class Main {
	
	/* This method returns number of all (individual)identical elements in i th column of the list. */
	private static Set<String> getElements(List<String[]> lst, int ix) {
		Set<String> individualElements = new HashSet<>();
		for (int i = 0; i < lst.size(); individualElements.add(lst.get(i ++)[ix]));
		return individualElements;
	}
	
	/* This methods eliminates any "*" existing in the i th token of each line*/ 
	private static void eliminateStar(List<String[]> lst, int i) {
		for (String[] line : lst)
			if (line[i].contains("*")) {
				int index = line[i].indexOf("*");
				String token = line[i].substring(0, index);
				if (index + 1 < line[i].length())
					token += line[i].substring(index + 1, line[i].length());
				line[i] = token;
			}
	}
	
	/* Sorts the list based on the comparison between their ix th columns.*/
	private static boolean sortList(List<String[]> lst, int ix) {
		Collections.sort(lst, new Comparator<String[]>() {
			@Override
			public int compare(String[] o1, String[] o2) {
				return o1[ix].compareTo(o2[ix]);
			}
		});
		return true;
	}
	
	/* This method compares only the number part of the ix th column of the first list with jx th column of the second list and returns those which are not exist in the second file. */
	private static List<String[]> getNotInCommon(List<String[]> firstList, int ix, List<String[]> secondList, int jx) {
		Set<String> noDuplicationSecondList = new HashSet<>();
		List<String[]> notInCommon = new ArrayList<>();
		for (int i = 0; i < secondList.size(); i ++) {
			String[] tokens = secondList.get(i)[jx].split("-");
			noDuplicationSecondList.add(FileManager.getDigits((tokens.length <= 2)? tokens[1]:tokens[2]));
		}
		for ( String[] line : firstList) {
			String[] tokens = line[ix].split("-");
			String number = (tokens.length > 2)? tokens[2]: (tokens.length<=1)? tokens[0]: tokens[1];				
			if (!noDuplicationSecondList.contains(number))
				notInCommon.add(line);
		}
		return notInCommon;
	}
	
	/* This method compares only the number part of the ix th column of the first list with jx th column of the second list and returns those which are exist in the second file. */
	private static List<String[]> getInCommon(List<String[]> firstList, int ix, List<String[]> secondList, int jx) {
		Set<String> noDuplicationSecondList = new HashSet<>();
		List<String[]> InCommon = new ArrayList<>();
		for (int i = 0; i < secondList.size(); i ++) {
			String[] tokens = secondList.get(i)[jx].split("-");
			noDuplicationSecondList.add(FileManager.getDigits((tokens.length <= 2)? tokens[1]:tokens[2]));
		}
		for ( String[] line : firstList) {
			String[] tokens = line[ix].split("-");
			String number = (tokens.length > 2)? tokens[2]: (tokens.length<=1)? tokens[0]: tokens[1];				
			if (noDuplicationSecondList.contains(number))
				InCommon.add(line);
		}
		return InCommon;
	}
	
	/* This methods counts how many copy of each miRNAs exist and how many ups and downs each copy has. */
	private static List<String[]> countElements(List<String[]> lst) {
		Map<String, Integer> numberOfCopy = new HashMap<>();
		Map<String, Integer> numberOfUps = new HashMap<>();
		Map<String, Integer> numberOfDowns = new HashMap<>();
		for (String[] tokens : lst) {
			Integer value = numberOfCopy.get(tokens[0]);
			if (value == null)
				numberOfCopy.put(tokens[0], 1);
			else
				numberOfCopy.put(tokens[0], value + 1);
			String token = tokens[tokens.length - 1].toLowerCase();
			if (token.contains("up")) {
				value = numberOfUps.get(tokens[0]);
				if (value == null)
					numberOfUps.put(tokens[0], 1);
				else
					numberOfUps.put(tokens[0], value + 1);
			}else if(token.contains("down")) {
				value = numberOfDowns.get(tokens[0]);
				if (value == null)
					numberOfDowns.put(tokens[0], 1);
				else
					numberOfDowns.put(tokens[0], value + 1);
			}else {
				System.out.println("Otherss");
			}
		}
		List<String[]> Output = new ArrayList<>();
		for (String key : numberOfCopy.keySet()) {
			String nCopy = Integer.toString(numberOfCopy.get(key));
			String nUps = (numberOfUps.get(key) == null)? "0": Integer.toString(numberOfUps.get(key));
			String nDowns = (numberOfDowns.get(key) == null)? "0": Integer.toString(numberOfDowns.get(key));
			Output.add(new String[] {key, nCopy, nUps, nDowns}); 
		}
		return Output;
	}
	
	/*This method remove duplicates inside the list*/
	private static void removeDuplicates(List<String[]> lst) {
		List<String[]> obj = new ArrayList<>();
		Set<String> temp = new HashSet<String>();
		for(int i = 0; i < lst.size(); i ++)
			if (!temp.add(lst.get(i)[0]+lst.get(i)[1]))
				obj.add(lst.get(i));
		if (!obj.isEmpty())
			for (String[] o : obj)
				lst.remove(o);
	}
	
	private static void showDuplicationRow(List<List<String>> lst) {
		Set<String> temp = new HashSet<String>();
		for(int i = 0; i < lst.size(); i ++)
			if (!temp.add(lst.get(i).get(0)))
				System.out.println(i);
	}
	
	private static String checkHSA(String s) {
		return (!s.startsWith("hsa-"))? "hsa-" + s: s;
	}
	/* Returns the detail about the given miRNA. Otherwise returns null if there is not any similar.*/
	private static String upDownDetails(String miRNA, List<String[]> upDownReport) {
		miRNA = checkHSA(miRNA);
		for(String[] tokens : upDownReport) {
			if (miRNA.equals(tokens[0]))
				return ("(cpy:" + tokens[1] + "- up:" + tokens[2] + "- down:" + tokens[3] + ")");
		}
		return null;
	}
	
	/* Retrieves the relevant data with the given geneCode from MySQl and Returns a list of all informations.*/  
	private static List<String> getGeneDetail(int geneCode, List<String[]> upDownReport) {
		List<String> Result = new ArrayList<>();
		DataBaseManager db = new DataBaseManager("genes");
		List<String[]> gene = db.exeQueryReturnList("SELECT * FROM main WHERE id=" + geneCode, 4);
		//List<String[]> secondaryNames = db.exeQueryReturnList("SELECT * FROM also_known_as where id=" + geneCode, 2);
		if (gene.size()>1)
			System.out.println("ERROOOOr");
		for (String[] g : gene)
			for (String st : g )
				Result.add(st);
		/*for (String[] names : secondaryNames)
				Result.add(names[1]);
		*/
		return Result;
	}
	
	/* Returns an index of the given list in which the first element is equal to the geneCode.*/
	private static int checkGeneExistent(List<List<String>> output, String geneCode) {
		for (int i = 0; i < output.size(); i ++){
			if ( output.get(i).get(0).equals(geneCode))
				return i;
		}
		return -1;
	}
	
	/* This method compares only the number part of the ix th column of the first list with jx th column of the second list and returns those which are exist in the second file. */
	private static List<String[]> getInCommon2(List<String[]> RNAs, int ix, List<String[]> nCList, int jx) {
		Set<String> noDuplicationSecondList = new HashSet<>();
		List<String[]> InCommon = new ArrayList<>();
		for (int i = 0; i < nCList.size(); i ++) {
			String token = nCList.get(i)[jx].substring(8);
			noDuplicationSecondList.add(token);
		}
		for ( String[] line : RNAs) {
			String tokens = (line[ix].startsWith("hsa-miR-") || line[ix].startsWith("hsa-let-") )? line[ix].substring(8):
							(line[ix].startsWith("miR-") || line[ix].startsWith("let-") )? line[ix].substring(4): line[ix].substring(7);				
			if (noDuplicationSecondList.contains(tokens))
				InCommon.add(line);
		}
		return InCommon;
	}
	
	/* This methods returns matched elements in both list.*/
	private static List<String[]> getMatches(List<String[]> RNAs, int ix, List<String[]> nClst, int jx, List<String[]> upDownReport) {
		List<List<String>> output = new ArrayList<>();
		List<String[]> commonRNAs = getInCommon2(RNAs, ix, nClst, jx);
		removeDuplicates(commonRNAs);
		for(String[] RNA : commonRNAs) {
			int index = -1;
			String details = upDownDetails(RNA[1], upDownReport);
			if ((index = checkGeneExistent(output, RNA[0])) >= 0)
				output.get(index).add(RNA[1] + details);
			else {
				int geneCode = Integer.valueOf(RNA[0]);
				List<String> oneResult = getGeneDetail(geneCode, upDownReport);
				oneResult.add(RNA[1] + details);
				output.add(oneResult);
			}
		}
		List<String[]> finalResult = new ArrayList<>();
		for ( List<String> list : output) {
			String[] lS = new String[list.size()]; 
			finalResult.add(list.toArray(lS));
		}
		return finalResult;
	}
	
	private static void runFirstPartOFproject() {
		FileManager first = new FileManager(Paths.get("/Users/Meijad/Desktop/fatemeh_reports/miRExpAll.txt"), 1);
		FileManager second = new FileManager(Paths.get("/Users/Meijad/Desktop/fatemeh_reports/miRCancerDecemberarticles.txt"), 1);
		
		
		List<String[]> cBoth = first.linesContain(new int[]{4,7}, "hepatocellular carcinoma".toLowerCase(), "cancer vs normal".toLowerCase());
		eliminateStar(cBoth, 0);
		sortList(cBoth, 0);
		
		
		List<String[]> cHepatoCellular = second.linesContain(new int[]{1}, "hepatocellular carcinoma".toLowerCase());
		eliminateStar(cHepatoCellular, 0);
		sortList(cHepatoCellular, 0);
		
		
		List<String[]> notInCommon1 = getNotInCommon(cBoth, 0, cHepatoCellular, 0),
						notInCommon2 =getNotInCommon(cBoth, 1, cHepatoCellular, 0);
		sortList(notInCommon1, 0);
		sortList(notInCommon2, 0);
		
		List<String[]> upDownReport = countElements(notInCommon1);
		sortList(upDownReport, 0);
		
		DataBaseManager db = new DataBaseManager("genes");
		List<String[]> rNAs = db.exeQueryReturnList("SELECT * FROM miRNAs", 2);
		List<String[]> genes = getMatches(rNAs, 1, notInCommon1, 0, upDownReport);
		sortList(genes, 0);
		/*********** END of final Report 1 **********/
		/******** Starting off the final report 2 *********/
		List<String[]> inCmn = getInCommon(cBoth, 0, cHepatoCellular, 0);
		sortList(inCmn, 0);
		
		List<String[]> upDownReport2 = countElements(inCmn);
		sortList(upDownReport2, 0);
		
		List<String[]> rNAs2 = db.exeQueryReturnList("SELECT * FROM miRNAs", 2);
		List<String[]> genes2 = getMatches(rNAs2, 1, inCmn, 0, upDownReport2);
		sortList(genes2, 0);
		
		//System.out.printf("cBoth: %d Individuals: %d\n", cBoth.size(),  getlements(cBoth, 0).size());
		//System.out.printf("cBoth: %d Individuals: %d\n", cHepatoCellular.size(),  getElements(cHepatoCellular, 0).size());
		//System.out.printf("notInCommon1: %d Individuals: %d\n", notInCommon1.size(), getElements(notInCommon1, 0).size());
		//System.out.printf("notInCommon2: %d Individuals: %d\n", notInCommon2.size(), getElements(notInCommon2, 0).size());
		//System.out.printf("genes: %d Individuals: %d\n", genes.size(), getElements(genes, 1).size());
		
		
		//System.out.println(FileManager.writeInFile(Paths.get(first.getPath().getParent().toString()+"/containBothWords_new.txt"),  first.header(), cBoth));
		//System.out.println(FileManager.writeInFile(Paths.get(first.getPath().getParent().toString()+"/containHepato_new.txt"),  second.header(), cHepatoCellular));
		//System.out.println(FileManager.writeInTxtFile(Paths.get(first.getPath().getParent().toString()+"/output3_new.txt"),  first.header(), notInCommon1));
		//System.out.println(FileManager.writeInFile(Paths.get(first.getPath().getParent().toString()+"/output4_new.txt"),  first.header(), notInCommon2));
		//System.out.println(FileManager.writeInTxtFile(Paths.get(first.getPath().getParent().toString()+"/nUpsDowns.txt"), "miRNA_ID\t\tnum\tUPs\tDOWNs", upDownReport));
		//System.out.println(FileManager.writeInFile(Paths.get(first.getPath().getParent().toString()+"/report01.txt"), "ID\tGENE\tTYPE\tSTATUS\tALSO KNOWN AS\t miRNAs", genes));
		/*System.out.println(FileManager.writeInCsvFile(Paths.get(first.getPath().getParent().toString()+"/report02.CSV"),
				new String[] {"ID", "GENE", "TYPE", "STATUS", "miRNAs"}, genes)); 
		*/
		/*System.out.println(FileManager.writeInCsvFile(Paths.get(first.getPath().getParent().toString()+"/finalReport02.CSV"),
						new String[] {"ID", "GENE", "TYPE", "STATUS", "miRNAs"}, genes2));
		*/
	}
	
	private static Set<String> getSetOf(String[] list) {
		Set<String> S = new HashSet<>();  
		for (String s : list)
			S.add(s);
		return S;
	}
	
	private static Set<String> getSetOf(String[] list, int fromIndx) {
		Set<String> S = new HashSet<>();  
		for (int i = fromIndx; i < list.length; i ++) 
			S.add(list[i]);
		return S;
	}
	
	private static Set<String> getAllGenes(List<String[]> list, int column) {
		Set<String> S = new HashSet<>(); 
		for (int i = 1; i < list.size(); i ++)
			S.add(list.get(i)[column]);
		return S;
	}
	
	private static Set<String> compareIDs(Set<String> first, Set<String> second) {
		Set<String> result = new HashSet<>();
		Iterator<String> fth = first.iterator();
		while(fth.hasNext()) {
			String k = fth.next();
			if (second.contains(k))
				result.add(k);
		}
		return result;
	}
	
	private static Set<String> compareGenes(Set<String> first, Set<String> second) {
		Set<String> result = new HashSet<>();
		Iterator<String> fth = first.iterator();
		while(fth.hasNext()) {
			String k = fth.next();
			if (second.contains(k))
				result.add(k);
		}
		return result;
	}
	
	private static List<String> changeSetToList(Set<String> S) {
		Iterator<String> sIt = S.iterator();
		List<String> result = new ArrayList<>();
		while (sIt.hasNext())
			result.add(sIt.next());
		Collections.sort(result);
		return result;
	}
	
	private static Map<String,Integer> getIndexesOf(List<String[]> fromThisList, int fromThisline, int fromThisIndex, Set<String> findThesesStrings) {
		Map<String,Integer> columnIndexes = new HashMap<>();
		String[] list = fromThisList.get(fromThisline);
		for (int i = fromThisIndex ; i < list.length; i ++)
			if (findThesesStrings.contains(list[i]))
				columnIndexes.put(list[i],i);
		return columnIndexes;
	}
	
	private static List<Integer> makeIndexes(Map<String,Integer> TheseColumns,List<String> ColumnPattern) {
		List<Integer> result = new ArrayList<>();
		for (String cP : ColumnPattern) {
			Integer index = TheseColumns.get(cP);
			if ( index != null)
				result.add(index);
		}
		return result;
	}
	
	private static List<List<String>> getP_G_data(List<String[]> allRowsFromThisFile, List<Integer> indexes, Set<String> genes) {
		List<List<String>> result = new ArrayList<>();
		genes.add("Hugo_Symbol");
		for (String[] row : allRowsFromThisFile) {
			if (!genes.contains(row[0]))
				continue;
			List<String> currentRow = new ArrayList<>();
			for (Integer index : indexes)
				currentRow.add(row[index]);
			result.add(currentRow);
		}
		return result;
	}
	
	private static List<List<String>> getReport(Set<String> allPs_S, List<String[]> targetFile, Set<String> genes) {
		Map<String,Integer> indexes = getIndexesOf(targetFile, 0, 2, allPs_S);
		indexes.put("Hugo_Symbol", 0);
		List<String> allPs_A = changeSetToList(allPs_S);
		allPs_A.add(0, "Hugo_Symbol");
		List<Integer> finalIndexes = makeIndexes(indexes, allPs_A);
		List<List<String>> report = getP_G_data(targetFile, finalIndexes,genes);
		return report;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		*/
		
		//runFirstPartOFproject();
		String[] filenames = new String[] {"02_data_RNA_Seq_v2_expression_median.txt","03_data_RNA_Seq_v2_mRNA_median_Zscores.txt","04_data_linear_CNA.txt","05_lihc_tcga_clinical_data.tsv"};
		FileManager sampleIds = new FileManager(Paths.get("/Users/Meijad/Desktop/fatima_data02/samplesIDs.txt"));
		Set<String> IDs = getSetOf(sampleIds.getSplittedContent().get(0));
		
		FileManager data_RNA = new FileManager(Paths.get("/Users/Meijad/Desktop/fatima_data02/" + filenames[0]));
		Set<String> newIDs = getSetOf(data_RNA.getSplittedContent().get(0),2);
		Set<String> genes = getAllGenes(data_RNA.getSplittedContent(),0);
		IDs = compareIDs (IDs, newIDs);
		
		for (int i = 1 ; i < filenames.length - 1 ; i++) {
			data_RNA = new FileManager(Paths.get("/Users/Meijad/Desktop/fatima_data02/" + filenames[i]));
			newIDs = getSetOf(data_RNA.getSplittedContent().get(0),2);
			Set<String> newGenes = getAllGenes(data_RNA.getSplittedContent(),0);
			IDs = compareIDs (IDs, newIDs);
			genes = compareGenes(genes , newGenes);
		}
		
		for (int i = 0 ; i < filenames.length - 1 ; i++) {
			//System.out.printf("Reading %s...", filenames[i]);
			data_RNA = new FileManager(Paths.get("/Users/Meijad/Desktop/fatima_data02/" + filenames[i]));
			//System.out.println("DONE!");
			List<List<String>> report = getReport(IDs, data_RNA.getSplittedContent(), genes);
			List<String> header = report.remove(0);
			Collections.sort(report, new Comparator<List<String>>() {
				@Override
				public int compare(List<String> o1, List<String> o2) {
					return o1.get(0).compareTo(o2.get(0));
				}
			});
			showDuplicationRow(report);
			String reportFileName = "/Report" + String.valueOf(i) + ".CSV";
			System.out.println(reportFileName + "\t" + report.size() + "\t" + report.get(10).size());
			//System.out.println(FileManager.writeInCsvFile2(Paths.get(sampleIds.getPath().getParent().toString() + reportFileName), header ,report));
		}
		data_RNA = new FileManager(Paths.get("/Users/Meijad/Desktop/fatima_data02/" + filenames[3]), 1);
		List<String[]> report = new ArrayList<>();
		for (String[] line : data_RNA.getSplittedContent())
			if (IDs.contains(line[1]))
				report.add(line);
		Collections.sort(report, new Comparator<String[]>() {
			@Override
			public int compare(String[] o1, String[] o2) {
				return o1[1].compareTo(o2[1]);
			}
		});
		String reportFileName = "/Report3.CSV";
		System.out.println(reportFileName + "\t" + report.size() + "\t" + data_RNA.getSplittedContent().size() + "\t" + data_RNA.size());
		//System.out.println(FileManager.writeInCsvFile(Paths.get(sampleIds.getPath().getParent().toString() + reportFileName), data_RNA.splittedHeader() ,report));
		
		/*DataBaseManager db = new DataBaseManager("genes");
		db.executeQuery("select * from main");*/
	}
}
