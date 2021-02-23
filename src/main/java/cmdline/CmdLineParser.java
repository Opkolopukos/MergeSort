package cmdline;

import merging.MergeUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CmdLineParser {
    private boolean isCmdParsingFeasible = true;
    private boolean isFlagOutputFile = true;
    private boolean isAscendingSort = true;
    private boolean isStringSort = true;
    private String outputFile = null;
    private final List<String> inputFiles = new ArrayList<>();

    public void parseCmd(String[] args) {

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
                isAscendingSort = false;
            } else if (arg.equalsIgnoreCase("-a")) {
                isAscendingSort = true;
            } else if (arg.equalsIgnoreCase("-s")) {
                isStringSort = true;
            } else if (arg.equalsIgnoreCase("-i")) {
                isStringSort = false;
            } else {
                if (isFlagOutputFile) {
                    isFlagOutputFile = false;
                    outputFile = arg;
                } else {
                    inputFiles.add(arg);
                }
            }
        }

        if (outputFile == null) {
            System.out.println("Param 'Output File' missing");
            isCmdParsingFeasible = false;
        }
        if (inputFiles.size() == 0) {
            System.out.println("Param 'Input File' missing");
            isCmdParsingFeasible = false;
        }

        if (isCmdParsingFeasible) {
            try {
                for (String file : inputFiles) {
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
            if (!isCmdParsingFeasible) {
                showUsage();
                System.exit(1);
            }
            try {
                MergeUtils.mergeFiles(isAscendingSort, isStringSort, outputFile, inputFiles);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
}