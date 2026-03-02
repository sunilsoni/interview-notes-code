package com.interview.notes.code.year.y2026.march.microsoft.test2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

// Defines the downstream memory service interface exactly as provided in the problem
interface IMemoryService {
    int allocate(int length); // Reserves a contiguous block; returns 1-based start index or 0 on failure
    byte[] read(int startIndex, int length); // Reads contiguous bytes from the memory block
    int write(int startIndex, byte[] data); // Writes bytes to memory; 0 success, 1 failure
    int free(int startIndex, int length); // Frees the memory block; 0 success, 1 failure
}

public class InMemoryFileStore {
    
    private final IMemoryService _mem; // Stores the reference to the downstream memory service
    private final Map<Integer, FileInfo> fileMap = new HashMap<>(); // Maps fileId to its memory location data
    private int nextId = 1; // Counter to generate unique, strictly positive file IDs starting at 1

    public InMemoryFileStore(IMemoryService mem) { // Constructor for our store
        this._mem = mem; // Injects the memory service dependency into our class instance
    }

    // Main method to run all tests
    public static void main(String[] args) {
        System.out.println("Starting Java 8 Tests..."); // Prints a starting banner

        MockMemoryService mockMem = new MockMemoryService(); // Instantiates our dummy memory system
        InMemoryFileStore store = new InMemoryFileStore(mockMem); // Instantiates the system under test

        // Test 1: Sequence from the problem description
        int id1 = store.Create(4); // Create(4) -> id=1
        check("Test 1a - Create id1", id1 == 1); // Verifies ID generation logic

        int id2 = store.Create(3); // Create(3) -> id=2
        check("Test 1b - Create id2", id2 == 2); // Verifies sequential ID generation

        int del1 = store.Delete(1); // Delete(1) -> 0
        check("Test 1c - Delete id1", del1 == 0); // Verifies successful deletion returns 0

        int id3 = store.Create(2); // Create(2) -> id=3
        check("Test 1d - Create id3", id3 == 3); // Verifies ID continues incrementing

        int save1 = store.Save(2, new byte[]{'2','2','2'}); // Save(2, ['2','2','2']) -> 0
        check("Test 1e - Save id2", save1 == 0); // Verifies saving correct length works

        int save2 = store.Save(3, new byte[]{'3','3'}); // Save(3, ['3','3']) -> 0
        check("Test 1f - Save id3", save2 == 0); // Verifies saving correct length works again

        byte[] get1 = store.Get(3); // Get(3) -> ['3','3']
        check("Test 1g - Get id3", Arrays.equals(get1, new byte[]{'3','3'})); // Verifies data integrity on retrieval

        int delUnknown = store.Delete(999); // Delete(999) -> 1
        check("Test 1h - Delete unknown", delUnknown == 1); // Verifies deleting non-existent file returns failure

        // Test 2: Variable length updates (shrinking and growing)
        int id4 = store.Create(5); // Creates a new file of size 5
        store.Save(id4, new byte[]{1, 2, 3, 4, 5}); // Fills it with 5 bytes
        int saveShrink = store.Save(id4, new byte[]{9, 9}); // Overwrites it with only 2 bytes (shrinking)
        check("Test 2a - Save shrink", saveShrink == 0); // Validates the save succeeded
        check("Test 2b - Get shrunk data", Arrays.equals(store.Get(id4), new byte[]{9, 9})); // Validates data reflects new size

        int saveGrow = store.Save(id4, new byte[]{7, 7, 7, 7, 7, 7, 7}); // Overwrites it with 7 bytes (growing)
        check("Test 2c - Save grow", saveGrow == 0); // Validates the reallocation save succeeded
        check("Test 2d - Get grown data", Arrays.equals(store.Get(id4), new byte[]{7, 7, 7, 7, 7, 7, 7})); // Validates data expanded correctly

        // Test 3: Large data inputs using Java 8 Streams
        int id5 = store.Create(1000000); // Allocates a very large file (1 million bytes)

        // Using Java 8 IntStream to generate a large array of bytes efficiently
        byte[] largeData = new byte[1000000]; // Initializes a large byte array
        IntStream.range(0, 1000000).forEach(i -> largeData[i] = (byte) (i % 256)); // Streams over range to populate array with dummy data

        int saveLarge = store.Save(id5, largeData); // Saves the massive block
        check("Test 3a - Save large data", saveLarge == 0); // Confirms large save works seamlessly

        byte[] retrievedLarge = store.Get(id5); // Reads the massive block back
        check("Test 3b - Get large data", Arrays.equals(largeData, retrievedLarge)); // Validates perfectly matched huge data sets

        System.out.println("All tests completed."); // Concluding message
    }

    // Helper method to keep test output clean and standardized without JUnit
    private static void check(String testName, boolean condition) {
        if (condition) { // Checks if the test condition passed
            System.out.println("PASS: " + testName); // Outputs pass cleanly
        } else { // Triggers if condition failed
            System.out.println("FAIL: " + testName); // Outputs fail aggressively
        }
    }

    public int Create(int length) { // Creates a new file allocation
        int start = _mem.allocate(length); // Asks the downstream service to reserve a block of the requested length
        if (start == 0) return 0; // If allocation fails (returns 0), we immediately return 0 to indicate failure

        int id = nextId++; // Grabs the current available ID and increments the counter for the next file
        fileMap.put(id, new FileInfo(start, length)); // Stores the newly created file's metadata in our hash map
        return id; // Returns the successfully generated file ID to the client
    }

    public int Save(int fileId, byte[] data) { // Saves or updates data for an existing file
        FileInfo info = fileMap.get(fileId); // Retrieves the existing file's memory metadata using its ID
        if (info == null) return 1; // If the file ID doesn't exist in our map, return 1 indicating failure

        if (data.length == info.length()) { // Checks if the new data perfectly fits the currently allocated block
            return _mem.write(info.startIndex(), data); // Writes directly to the existing block to save expensive re-allocations
        } else { // Triggers if the new data size differs from the currently allocated size (resizing scenario)
            int newStart = _mem.allocate(data.length); // Allocates a brand new block of memory matching the new data size
            if (newStart == 0) return 1; // If the system cannot allocate the new block, fail the save but keep old data intact

            int writeStatus = _mem.write(newStart, data); // Attempts to write the new data into the newly allocated block
            if (writeStatus != 0) { // Checks if the writing process itself failed
                _mem.free(newStart, data.length); // Rolls back by freeing the newly allocated block since we can't use it
                return 1; // Returns 1 to indicate the save operation failed
            }

            _mem.free(info.startIndex(), info.length()); // Safely frees the old memory block now that data is moved
            fileMap.put(fileId, new FileInfo(newStart, data.length)); // Updates our internal dictionary with the new memory location
            return 0; // Returns 0 indicating the resize and save was totally successful
        }
    }

    public byte[] Get(int fileId) { // Retrieves the file content
        FileInfo info = fileMap.get(fileId); // Looks up the file metadata using the provided file ID
        if (info == null) return null; // Returns null if the file does not exist, as per requirements
        return _mem.read(info.startIndex(), info.length()); // Commands the memory service to read and return the byte array
    }

    // --- MOCK SERVICE & MAIN METHOD FOR TESTING ---
    
    public int Delete(int fileId) { // Deletes the file and frees memory
        FileInfo info = fileMap.remove(fileId); // Removes the file entry from our map and retrieves its metadata simultaneously
        if (info == null) return 1; // If the file was not found in the map, return 1 to indicate failure
        return _mem.free(info.startIndex(), info.length()); // Frees the associated memory block and returns the operation's status
    }

    /**
     * @param startIndex Stores where the file begins in the downstream memory
     * @param length     Stores the exact size of the allocated memory block
     */ // Java 8 equivalent of the record: a simple data class to hold memory metadata
        private record FileInfo(int startIndex, int length) {
        // Constructor to initialize the metadata
        // Assigns the starting index
        // Assigns the length of the block
    }

    // A simple mock of the memory service to allow standalone testing without external dependencies
    static class MockMemoryService implements IMemoryService {
        private final Map<Integer, byte[]> memoryStorage = new HashMap<>(); // Simulates actual RAM storage blocks
        private int memoryPointer = 1; // Simulates memory addresses, starting at 1

        public int allocate(int length) { // Simulates memory allocation
            int start = memoryPointer++; // Assigns the current pointer as the start index and moves it forward
            memoryStorage.put(start, new byte[length]); // Creates a dummy byte array representing allocated space
            return start; // Returns the simulated memory address
        }

        public byte[] read(int startIndex, int length) { // Simulates reading from memory
            byte[] block = memoryStorage.get(startIndex); // Retrieves the simulated memory block
            if (block == null) return null; // Failsafe if the block doesn't exist
            byte[] copy = new byte[length]; // Creates a new array to return
            System.arraycopy(block, 0, copy, 0, Math.min(block.length, length)); // Copies data to simulate reading
            return copy; // Returns the read bytes
        }

        public int write(int startIndex, byte[] data) { // Simulates writing to memory
            if (!memoryStorage.containsKey(startIndex)) return 1; // Fails if the address is unallocated
            memoryStorage.put(startIndex, data.clone()); // Simulates writing by replacing the block with cloned data
            return 0; // Returns 0 for success
        }

        public int free(int startIndex, int length) { // Simulates freeing memory
            if (memoryStorage.remove(startIndex) != null) return 0; // Removes the block and returns 0 if it existed
            return 1; // Returns 1 if the block didn't exist to simulate double-free errors
        }
    }
}