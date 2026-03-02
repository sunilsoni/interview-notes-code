package com.interview.notes.code.year.y2026.march.microsoft.test3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// ---------------------------------------------------------
// 1. INTERFACES & DOMAIN CLASSES
// ---------------------------------------------------------

// Downstream memory service interface as provided in the problem description
interface IMemoryService {
    int allocate(int length); // Reserves a block; returns 1-based start index, 0 if failure
    byte[] read(int startIndex, int length); // Reads contiguous bytes
    int write(int startIndex, byte[] data); // Writes bytes; 0 success, 1 failure
    int free(int startIndex, int length);   // Frees block; 0 success, 1 failure
}

// Custom object to hold the memory location metadata for each file
class FileBlock {
    int startIndex; // Tracks where in the memory the block begins
    int capacity;   // Tracks the maximum reserved bytes to avoid unnecessary re-allocations
    int actualSize; // Tracks the exact size of the currently saved file data

    public FileBlock(int startIndex, int capacity, int actualSize) { // Constructor
        this.startIndex = startIndex; // Assign start index
        this.capacity = capacity;     // Assign capacity
        this.actualSize = actualSize; // Assign actual size
    }
}

// ---------------------------------------------------------
// 2. CORE IMPLEMENTATION
// ---------------------------------------------------------

public class InMemoryFileStore {
    private final IMemoryService _mem; // Reference to the downstream memory interface
    private final Map<Integer, FileBlock> fileMap; // Java 8 Map to link file ID to memory metadata
    private int nextFileId; // Simple counter to generate sequential unique IDs

    public InMemoryFileStore(IMemoryService mem) { // Constructor
        _mem = mem; // Inject the memory service dependency
        fileMap = new HashMap<>(); // Initialize the hash map to store file records
        nextFileId = 1; // Start our ID generation at 1 (since >0 is required)
    }

    public static void main(String[] args) {
        // Initialize our mock memory service to simulate the downstream system
        IMemoryService mockMemory = new MockMemoryService();
        // Instantiate the file store we just built
        InMemoryFileStore store = new InMemoryFileStore(mockMemory);

        System.out.println("--- Running Tests ---");

        // Test 1: Create(4) -> id=1
        int id1 = store.Create(4);
        check(id1 == 1, "Create(4) returns id 1");

        // Test 2: Create(3) -> id=2
        int id2 = store.Create(3);
        check(id2 == 2, "Create(3) returns id 2");

        // Test 3: Delete(1) -> 0
        int del1 = store.Delete(id1);
        check(del1 == 0, "Delete(1) returns 0 (success)");

        // Test 4: Create(2) -> id=3
        int id3 = store.Create(2);
        check(id3 == 3, "Create(2) returns id 3");

        // Test 5: Save(2, ['2','2','2']) -> 0
        byte[] data2 = new byte[]{'2', '2', '2'};
        int save2 = store.Save(id2, data2);
        check(save2 == 0, "Save(2, ['2','2','2']) returns 0");

        // Test 6: Save(3, ['3','3']) -> 0
        byte[] data3 = new byte[]{'3', '3'};
        int save3 = store.Save(id3, data3);
        check(save3 == 0, "Save(3, ['3','3']) returns 0");

        // Test 7: Get(3) -> ['3','3']
        byte[] fetched3 = store.Get(id3);
        check(Arrays.equals(fetched3, data3), "Get(3) returns correct data ['3','3']");

        // Test 8: Delete(999) -> 1
        int delUnknown = store.Delete(999);
        check(delUnknown == 1, "Delete(999) returns 1 (failure)");

        // Test 9: Handling dynamic resizing (Smaller data)
        byte[] smallerData = new byte[]{'A'};
        store.Save(id2, smallerData); // ID 2 currently has capacity 3
        byte[] fetchedSmaller = store.Get(id2);
        check(Arrays.equals(fetchedSmaller, smallerData) && fetchedSmaller.length == 1, "Dynamic Resize (Smaller): Get returns exactly 1 byte");

        // Test 10: Handling Large Data Input
        int largeId = store.Create(100000); // 100 KB
        byte[] largeData = new byte[100000];
        Arrays.fill(largeData, (byte) 'X'); // Fill with dummy data
        int saveLarge = store.Save(largeId, largeData);
        byte[] fetchedLarge = store.Get(largeId);
        check(saveLarge == 0 && Arrays.equals(fetchedLarge, largeData), "Large Data handling: 100,000 bytes successful");

        System.out.println("--- All Tests Completed ---");
    }

    // Helper method to print test results cleanly
    private static void check(boolean condition, String testName) {
        if (condition) System.out.println("[PASS] " + testName);
        else System.out.println("[FAIL] " + testName);
    }

    // Creates a new file block and returns the ID
    public int Create(int length) { // Method definition taking initial length
        if (length <= 0) return 0; // Guard against negative or zero lengths

        int startIndex = _mem.allocate(length); // Ask downstream to reserve contiguous memory
        if (startIndex == 0) return 0; // If downstream allocation fails, return failure (0)

        int fileId = nextFileId++; // Generate a new ID and increment counter for next time
        // Store metadata in map: initially, actual size equals requested capacity
        fileMap.put(fileId, new FileBlock(startIndex, length, length));

        return fileId; // Return the new valid file ID to the client
    }

    // Overwrites file content. Handles dynamic resizing gracefully.
    public int Save(int fileId, byte[] data) { // Method definition for saving data
        FileBlock block = fileMap.get(fileId); // Fetch the existing file metadata from map
        if (block == null) return 1; // If file ID doesn't exist, return failure (1)

        int dataLength = data.length; // Calculate the length of the incoming data array

        // OPTIMIZATION: If new data fits in the existing allocated space, reuse it
        if (dataLength <= block.capacity) { // Check if we can avoid expensive downstream allocation
            int writeResult = _mem.write(block.startIndex, data); // Overwrite existing memory block
            if (writeResult == 0) { // If the write was successful
                block.actualSize = dataLength; // Update actual size so Get() reads correct amount
                return 0; // Return success (0)
            }
            return 1; // Write failed downstream, return failure (1)
        }

        // If data is too big, we must allocate a completely new memory block
        int newStartIndex = _mem.allocate(dataLength); // Request larger block from downstream
        if (newStartIndex == 0) return 1; // If downstream fails to provide space, return failure (1)

        int writeResult = _mem.write(newStartIndex, data); // Write data to the new location
        if (writeResult != 0) { // If writing to the new space fails
            _mem.free(newStartIndex, dataLength); // Rollback: Free the newly allocated space
            return 1; // Return failure (1)
        }

        // Cleanup: We successfully moved data, so free the old block
        _mem.free(block.startIndex, block.capacity); // Free old block to prevent memory leaks

        // Update the file metadata to point to the new location
        block.startIndex = newStartIndex; // Update to the newly allocated start index
        block.capacity = dataLength; // Update the capacity to the new larger size
        block.actualSize = dataLength; // Update the actual data size

        return 0; // Return success (0)
    }

    // ---------------------------------------------------------
    // 3. TESTING HARNESS (MAIN METHOD & MOCK SERVICE)
    // ---------------------------------------------------------
    
    // Retrieves the exact file content
    public byte[] Get(int fileId) { // Method definition for fetching data
        FileBlock block = fileMap.get(fileId); // Look up the file ID in our map
        if (block == null) return null; // If not found, return null as per requirements

        // Read exactly 'actualSize' bytes from 'startIndex'
        return _mem.read(block.startIndex, block.actualSize); // Return the byte array from memory
    }

    // Deletes the file and frees downstream memory
    public int Delete(int fileId) { // Method definition for deletion
        FileBlock block = fileMap.get(fileId); // Retrieve metadata to know what to free
        if (block == null) return 1; // If file doesn't exist, return failure (1)

        int freeResult = _mem.free(block.startIndex, block.capacity); // Ask downstream to free memory
        if (freeResult == 0) { // If downstream successfully freed the block
            fileMap.remove(fileId); // Remove the reference from our Java map
            return 0; // Return success (0)
        }
        return 1; // Downstream free failed, return failure (1)
    }
}

// Dummy Mock memory service to simulate the downstream interactions for testing
class MockMemoryService implements IMemoryService {
    private final byte[] memory = new byte[1024 * 1024]; // 1MB simulated memory array
    private int currentOffset = 1; // 1-based start index requirement

    public int allocate(int length) {
        if (currentOffset + length > memory.length) return 0; // Simulates out of memory
        int start = currentOffset; // Remember start position
        currentOffset += length; // Advance pointer simulating allocation
        return start; // Return 1-based index
    }

    public byte[] read(int startIndex, int length) {
        byte[] res = new byte[length]; // Create buffer
        System.arraycopy(memory, startIndex, res, 0, length); // Copy from simulated memory
        return res; // Return copied bytes
    }

    public int write(int startIndex, byte[] data) {
        System.arraycopy(data, 0, memory, startIndex, data.length); // Overwrite simulated memory
        return 0; // Assume success always in mock
    }

    public int free(int startIndex, int length) {
        return 0; // Assume success (memory is 'freed' logically)
    }
}