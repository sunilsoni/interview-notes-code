package com.interview.notes.code.misc;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.*;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

@Log4j2
class DirectoryTest {
    public static final String ERROR_SUFFIX = "_ERROR.txt";
    private static final String DEFAULT_DIRECTORY = "/Important/Interview/2001";
    private static final String IMPORTED_SUFFIX = "_IMPORTED.txt";
    private static final String DIRECTORY_NAME = "/Important/Interview/2001";
    private Iterator iterator = null;
    private long practiceId;
    private int formatId;

    public static void main(String[] args) {
        DirectoryTest patientDataParser = new DirectoryTest();
        patientDataParser.getFileIterator();
        File currentFile = new File(DEFAULT_DIRECTORY);
        String currentFileS = patientDataParser.getImportedFilename(currentFile);
    }

    private void getFileIterator() {
        String[] fileFilterSuffixes = {IMPORTED_SUFFIX, ERROR_SUFFIX};
        try {

            SuffixFileFilter notFileFilter = new SuffixFileFilter(fileFilterSuffixes);
            IOFileFilter fileFilter = new NotFileFilter(new SuffixFileFilter(fileFilterSuffixes));

            iterator = FileUtils.iterateFiles(new File(DIRECTORY_NAME), fileFilter, TrueFileFilter.INSTANCE);
            while (iterator.hasNext()) {
                File nextFile = (File) iterator.next();
                try {
                    System.out.println("getName: " + nextFile.getCanonicalPath());
                } catch (IOException e) {
                    // throw new RuntimeException(e);
                }
            }

            //  IOFileFilter dirFilter =new IOFileFilter();
            Collection files = FileUtils.listFiles(
                    new File(DIRECTORY_NAME),
                    new RegexFileFilter("^(.*?)"), fileFilter
            );
            System.out.println("files: " + files);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getImportedFilename(File currentFile) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyddMMhhmmss");
        Date date = new Date();
        String time = sdf.format(date);
        return currentFile.getPath() + "_" + time + IMPORTED_SUFFIX;
    }

    private String convertPath(String path) {
        return path.replaceAll("\\\\", "/");
    }

    private boolean parsePracticeIdAndFormatId(File file) {
        String path = file.getPath();
        System.out.println("path " + path);
        if (File.separatorChar == '\\') {
            path = convertPath(path);
        }
        path = StringUtils.substringAfter(path, "directoryName");

        try {
            practiceId = Long.parseLong(StringUtils.substringBetween(path, "/", "/"));
            path = StringUtils.substringAfter(path, "/" + practiceId);
            int fmtId = Integer.parseInt(StringUtils.substringBetween(path, "/", "/"));
            if (formatId != fmtId) {
                formatId = fmtId;
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}