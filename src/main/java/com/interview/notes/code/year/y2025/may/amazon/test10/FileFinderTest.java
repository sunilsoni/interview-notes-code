package com.interview.notes.code.year.y2025.may.amazon.test10;/* ===================================================================================== */
/*                                      TEST CODE                                       */
/* ===================================================================================== */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * A simple test driver with a main method.  It:
 * 1) Creates a temporary root directory.
 * 2) Fills it with a “big” file (> 5 MB), some XML files, some other files, and a nested subdir.
 * 3) Creates a “manyFiles” subdir with 1,000 small files to simulate a large tree.
 * 4) Runs FileFinder.find(...) for each test scenario, printing PASS or FAIL.
 * <p>
 * We do NOT use JUnit; instead, we run everything in a single main(...) for demonstration.
 */
public class FileFinderTest {
    public static void main(String[] args) {
        // We’ll keep track of test numbers for clarity.
        int testNum = 1;

        try {
            // ============ 1) Create a temporary directory as our “root” for testing. ============
            Path root = Files.createTempDirectory("FileFinderTestRoot_");
            // We ensure it’s deleted on JVM exit for cleanliness.
            root.toFile().deleteOnExit();

            // ============ 2) Under root: create a “big” file > 5 MB ===============================
            Path bigFile = root.resolve("bigFile.bin");
            writeRandomFile(bigFile, 6 * 1024 * 1024); // 6 MB

            // “smallFile.txt” with ~1 KB of data
            Path smallFile = root.resolve("smallFile.txt");
            writeRandomFile(smallFile, 1024); // 1 KB

            // “data.xml” with a few bytes
            Path dataXml = root.resolve("data.xml");
            Files.write(dataXml, "<root></root>".getBytes());

            // ============ 3) Create a subdirectory “subdir/” with an XML and a TXT =================
            Path subdir = root.resolve("subdir");
            Files.createDirectories(subdir);
            subdir.toFile().deleteOnExit();

            Path insideXml = subdir.resolve("inside.xml");
            Files.write(insideXml, "<sub></sub>".getBytes());

            Path ignoreTxt = subdir.resolve("ignore.txt");
            Files.write(ignoreTxt, "ignore me".getBytes());

            // ============ 4) Create a “manyFiles” subdirectory with 1,000 small files =============
            Path manyFilesDir = root.resolve("manyFiles");
            Files.createDirectories(manyFilesDir);
            manyFilesDir.toFile().deleteOnExit();

            for (int i = 0; i < 1000; i++) {
                // e.g., file0.txt, file1.txt, ..., file999.txt
                Path f = manyFilesDir.resolve("file" + i + ".txt");
                writeRandomFile(f, 512); // ~512 bytes each
            }

            // ============ 5) TEST 1: Find all files > 5 MB =========================================
            try {
                List<Path> bigMatches = FileFinder.find(root, new SizeGreaterThanCriteria(5 * 1024 * 1024));
                // We expect exactly one match: “bigFile.bin”
                if (bigMatches.size() == 1 && bigMatches.get(0).equals(bigFile)) {
                    System.out.println("Test " + (testNum++) + ": PASS (Size > 5MB)");
                } else {
                    System.out.println("Test " + (testNum++) + ": FAIL – expected 1 result (bigFile.bin), got: " + bigMatches);
                }
            } catch (Exception e) {
                System.out.println("Test " + (testNum++) + ": FAIL – exception: " + e.getMessage());
            }

            // ============ 6) TEST 2: Find all .xml files ============================================
            try {
                List<Path> xmlMatches = FileFinder.find(root, new ExtensionCriteria(".xml"));
                // We expect two matches: data.xml and subdir/inside.xml (order undefined)
                if (xmlMatches.size() == 2
                        && xmlMatches.contains(dataXml)
                        && xmlMatches.contains(insideXml)) {
                    System.out.println("Test " + (testNum++) + ": PASS (Extension .xml)");
                } else {
                    System.out.println("Test " + (testNum++) + ": FAIL – expected [data.xml, subdir/inside.xml], got: " + xmlMatches);
                }
            } catch (Exception e) {
                System.out.println("Test " + (testNum++) + ": FAIL – exception: " + e.getMessage());
            }

            // ============ 7) TEST 3: Non‐existent directory should throw IllegalArgumentException =====
            try {
                Path bogus = Paths.get("/path/that/does/not/exist_12345");
                FileFinder.find(bogus, new ExtensionCriteria(".xml"));
                // If no exception, that’s a FAIL
                System.out.println("Test " + (testNum++) + ": FAIL – expected IllegalArgumentException for nonexistent dir");
            } catch (IllegalArgumentException ex) {
                // PASS, because we expected that exception
                System.out.println("Test " + (testNum++) + ": PASS (nonexistent directory throws)");
            } catch (Exception e) {
                // Some other exception is a FAIL
                System.out.println("Test " + (testNum++) + ": FAIL – expected IllegalArgumentException, got: " + e);
            }

            // ============ 8) TEST 4: Empty directory – no .pdf files, so expect empty list ==========
            try {
                Path emptyDir = Files.createTempDirectory("emptyTestDir_");
                emptyDir.toFile().deleteOnExit();
                List<Path> pdfMatches = FileFinder.find(emptyDir, new ExtensionCriteria(".pdf"));
                if (pdfMatches.isEmpty()) {
                    System.out.println("Test " + (testNum++) + ": PASS (empty dir, no matches)");
                } else {
                    System.out.println("Test " + (testNum++) + ": FAIL – expected empty list, got: " + pdfMatches);
                }
            } catch (Exception e) {
                System.out.println("Test " + (testNum++) + ": FAIL – exception: " + e.getMessage());
            }

            // ============ 9) TEST 5: Large “manyFiles” directory, search for .xml → expect empty =========
            try {
                List<Path> none = FileFinder.find(manyFilesDir, new ExtensionCriteria(".xml"));
                if (none.isEmpty()) {
                    System.out.println("Test " + (testNum++) + ": PASS (large input, no .xml)");
                } else {
                    System.out.println("Test " + (testNum++) + ": FAIL – expected empty in manyFilesDir, got: " + none);
                }
            } catch (Exception e) {
                System.out.println("Test " + (testNum++) + ": FAIL – exception on large input: " + e.getMessage());
            }

            // ============ Clean‐up comment: We rely on deleteOnExit() for temp files. =================
        } catch (IOException e) {
            System.err.println("Failed to set up test directories: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Helper method that writes 'sizeBytes' random bytes into the given path.
     * If the file does not exist, it’s created; if it does exist, it’s overwritten.
     * We break the write into chunks (e.g., 4096 bytes) to avoid allocating a giant array at once.
     *
     * @param path      Where to write the random data
     * @param sizeBytes Total number of random bytes to write
     * @throws IOException if an I/O error occurs
     */
    private static void writeRandomFile(Path path, long sizeBytes) throws IOException {
        // We’ll write in 4 KB chunks to avoid allocating a giant byte[] for very large files
        final int CHUNK_SIZE = 4 * 1024; // 4 KB
        byte[] buffer = new byte[CHUNK_SIZE];
        // Initialize buffer with some arbitrary data (all zeroes is fine for testing)
        // If you wanted truly random data, you could call new Random().nextBytes(buffer),
        // but for a size test, zeroes suffice.
        long bytesWritten = 0;

        // Open an OutputStream (auto‐close with try‐with‐resources)
        try (var out = Files.newOutputStream(path)) {
            while (bytesWritten < sizeBytes) {
                // Determine how many bytes to write in this iteration
                int toWrite = (int) Math.min(CHUNK_SIZE, sizeBytes - bytesWritten);
                out.write(buffer, 0, toWrite);
                bytesWritten += toWrite;
            }
        }
        // Mark the file for deletion on JVM exit
        path.toFile().deleteOnExit();
    }
}