package cmdline;

import IO.*;
import comparators.AscendingComparator;
import comparators.Comparator;
import comparators.DescendingComparator;
import merging.Merger;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CmdLineParser {
    private boolean isCmdParsingFeasible = true;
    private boolean isFlagOutputFile = true;
    private int sortingSettingsCount = 0;
    private int typeSettingsCount = 0;
    private ContentsType contentsType = ContentsType.STRING;
    private SortingOrder sortingOrder = SortingOrder.ASCENDING;
    private String outputFile = null;
    private final List<String> inputNames = new ArrayList<>();

    public void parseCmd(String[] args) throws FileNotFoundException {

        if (args.length == 0) {
            System.out.println("No arguments were given");
            showUsage();
        }
        int i = 0;
        String arg;
        System.out.println("Args:" + args.length);
        while (i < args.length) {
            arg = args[i++];
            if (arg.equalsIgnoreCase("-d")) {
                sortingOrder = SortingOrder.DESCENDING;
                sortingSettingsCount++;
            } else if (arg.equalsIgnoreCase("-a")) {
                sortingOrder = SortingOrder.ASCENDING;
                sortingSettingsCount++;
            } else if (arg.equalsIgnoreCase("-s")) {
                contentsType = ContentsType.STRING;
                typeSettingsCount++;
            } else if (arg.equalsIgnoreCase("-i")) {
                contentsType = ContentsType.INTEGER;
                typeSettingsCount++;
            } else {
                if (isFlagOutputFile) {
                    isFlagOutputFile = false;
                    outputFile = arg;
                } else {
                    inputNames.add(arg);
                }
            }
        }
        System.out.println(inputNames);

        if (sortingSettingsCount>1){
            isCmdParsingFeasible=false;
            System.out.println("MergeSort has stopped. Sorting order settings can be applied only once");
        }
        if (typeSettingsCount>1){
            isCmdParsingFeasible=false;
            System.out.println("MergeSort has stopped. Content type settings can be applied only once");
        }

        if (outputFile == null) {
            System.out.println("Param 'Output File' missing");
            isCmdParsingFeasible = false;
        }
        if (inputNames.size() == 0) {
            System.out.println("Param 'Input File' missing");
            isCmdParsingFeasible = false;
        }

        if (isCmdParsingFeasible) {
            try {
                for (String file : inputNames) {
                    File f = new File(file);
                    boolean exists = f.exists();
                    if (!exists) {
                        System.out.println("File not found: " + f.toString());
                        isCmdParsingFeasible = false;
                    } else {
                        Path path = Paths.get(f.toString());
                        if (!Files.isReadable(path)) {
                            System.out.println("No permission to read from file: " + path);
                            isCmdParsingFeasible = false;
                        }
                    }
                }
            } catch (SecurityException se) {
                System.out.println("Security exception: " + se.getMessage());
                isCmdParsingFeasible = false;
            }

            Comparator comparator = sortingOrder.equals(SortingOrder.ASCENDING)
                    ? new AscendingComparator()
                    : new DescendingComparator();


            if (contentsType == ContentsType.STRING) {
                List<InputObject<String>> stringInputList = new ArrayList<>();
                for (String name : inputNames) {
                    stringInputList.add(new InputObjectWithStrings(name));
                }
                OutputObject<String> outputObject = new OutputObjectWithStrings(outputFile);
                Merger.merge(comparator, outputObject, stringInputList);
                outputObject.close();
            } else {
                List<InputObject<Integer>> integerInputObjects = new ArrayList<>();
                for (String name : inputNames) {
                    integerInputObjects.add(new InputObjectWithIntegers(name));
                }
                OutputObject<Integer> outputObject = new OutputObjectWithIntegers(outputFile);
                Merger.merge(comparator, outputObject, integerInputObjects);
                outputObject.close();
            }
            System.out.println("MergeSort is successfully finished");
        }

        if (!isCmdParsingFeasible) {
            showUsage();
            System.exit(0);
        }
    }


    public static void showUsage() {
        System.out.println("Usage: program [-a|-d] <-s|-i> <outputFile> <inputFile1> <inputFile2> ... <inputFileN>");
        System.out.println("-a: set ascending sorting order (default), optional ");
        System.out.println("-d: set descending sorting order, optional ");
        System.out.println("-s|-i: String or Integer input data type, required");
        System.out.println("outputFilename.txt: filename for output data file, required");
        System.out.println("inputFile.txt: filename for input data file, required");
        System.out.println("inputFileN.txt: filename for additional input data file, optional");
    }

//    static class OutputFactory{
//
//        static OutputObject getOutput(ContentsType type, String filename) throws FileNotFoundException {
//            return switch (type) {
//                case STRING -> new OutputObjectWithStrings(filename);
//                case INTEGER -> new OutputObjectWithIntegers(filename);
//            };
//        }
//
//    }
}