package com.interview.notes.code.months.march24.test14;

// Mapper function
class NameMapper {
    void map(Record record) {
        //  emit(record.getName(), 1); // Emit name as key and count as value
    }
}

// Reducer function
class NameReducer {
    void reduce(String name, Iterable<Integer> counts) {
        int total = 0;
        for (int count : counts) {
            total += count; // Sum the counts for each name
        }
        if (total > 1) {
            //  emit(name, total); // Emit duplicate names along with their counts
        }
    }
}
