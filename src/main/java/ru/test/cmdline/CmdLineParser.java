package ru.test.cmdline;

import java.io.File;
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
    private ContentsType contentsType = null;
    private SortingOrder sortingOrder = SortingOrder.ASCENDING;
    private String outputFile = null;
    private final List<String> inputNames = new ArrayList<>();

    public void parseCmd(String[] args) {

        if (args.length == 0) {
            System.out.println("No arguments were given");
            showUsage();
            System.exit(0);
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

        if (sortingSettingsCount > 1) {
            isCmdParsingFeasible = false;
            System.out.println("MergeSort has stopped. Sorting order param can be applied only once");
            showUsage();
            System.exit(0);
        }
        if (typeSettingsCount > 1) {
            isCmdParsingFeasible = false;
            System.out.println("MergeSort has stopped. Content type param can be applied only once");
            showUsage();
            System.exit(0);
        }
        if (typeSettingsCount == 0) {
            isCmdParsingFeasible = false;
            System.out.println("MergeSort has stopped. All required parameters should be set");
            showUsage();
            System.exit(0);
        }

        if (outputFile == null) {
            System.out.println("Param 'Output File' missing");
            isCmdParsingFeasible = false;
            showUsage();
            System.exit(0);
        }
        if (inputNames.size() == 0) {
            System.out.println("Param 'Input File' missing");
            isCmdParsingFeasible = false;
            showUsage();
            System.exit(0);
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
        System.out.println("inputFileN.txt: filename for additional input data files, optional");
    }

    public ContentsType getContentsType() {
        return contentsType;
    }

    public SortingOrder getSortingOrder() {
        return sortingOrder;
    }

    public String getOutputFileName() {
        return outputFile;
    }

    public List<String> getInputFileNames() {
        return inputNames;
    }
}
