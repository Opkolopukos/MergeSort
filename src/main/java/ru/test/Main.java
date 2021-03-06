package ru.test;

import ru.test.cmdline.CmdLineParser;
import ru.test.cmdline.ContentsType;
import ru.test.cmdline.SortingOrder;
import ru.test.comparators.AscendingMyComparator;
import ru.test.comparators.DescendingMyComparator;
import ru.test.comparators.MyComparator;
import ru.test.io.*;
import ru.test.merging.MergingUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Objects;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        CmdLineParser cmdLineParser = new CmdLineParser();
        cmdLineParser.parseCmd(args);

        SortingOrder sortingOrder = cmdLineParser.getSortingOrder();
        MyComparator myComparator = sortingOrder.equals(SortingOrder.ASCENDING)
                ? new AscendingMyComparator()
                : new DescendingMyComparator();

        ContentsType type = cmdLineParser.getContentsType();
        switch (type) {
            case INTEGER -> {
                try (Stream<InputObject<Integer>> inputObjectWithIntegers = cmdLineParser.getInputFileNames()
                        .stream()
                        .map(name -> {
                            try {
                                return new FileReader(name);
                            } catch (FileNotFoundException e) {
                                System.out.println("File not found: " + name + " " + e.getMessage());
                                return null;
                            }
                        })
                        .filter(Objects::nonNull)
                        .map(InputObjectWithIntegers::new);
                     OutputObject<Integer> outputObjectWithIntegers = new OutputObjectWithIntegers(cmdLineParser.getOutputFileName())) {
                    MergingUtils.mergeFiles(myComparator, outputObjectWithIntegers, inputObjectWithIntegers);
                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }
            case STRING -> {
                try (Stream<InputObject<String>> inputObjectWithIntegers = cmdLineParser.getInputFileNames()
                        .stream()
                        .map(name -> {
                            try {
                                return new FileReader(name);
                            } catch (FileNotFoundException e) {
                                System.out.println("File not found: " + name + " " + e.getMessage());
                                return null;
                            }
                        })
                        .filter(Objects::nonNull)
                        .map(InputObjectWithStrings::new);
                     OutputObject<String> outputObjectWithIntegers = new OutputObjectWithStrings(cmdLineParser.getOutputFileName())) {
                    MergingUtils.mergeFiles(myComparator, outputObjectWithIntegers, inputObjectWithIntegers);
                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        System.out.println("MergeSort is finished!");
    }
}
