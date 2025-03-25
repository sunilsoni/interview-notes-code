package com.interview.notes.code.year.y2025.march.common.test20;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

interface MenuStream {
    String nextLine(); // returns null if stream ends
}

class MenuItem {
    String id, type, name;
    Double price;
    List<String> linkedIds = new ArrayList<>();

    MenuItem(String id, String type, String name, Double price) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.join("\n",
                id,
                type,
                name,
                (price != null) ? String.format("%.2f", price) : "",
                String.join("\n", linkedIds));
    }
}

class Menu {
    Map<String, MenuItem> items = new HashMap<>();

    void parseStream(MenuStream stream) {
        List<String> buffer = new ArrayList<>();
        String line;
        while ((line = stream.nextLine()) != null) {
            if (line.trim().isEmpty()) {
                addItem(buffer);
                buffer.clear();
            } else {
                buffer.add(line);
            }
        }
        if (!buffer.isEmpty()) addItem(buffer); // Add last item if stream ends without empty line
    }

    private void addItem(List<String> data) {
        String id = data.get(0);
        String type = data.get(1);
        String name = data.get(2);
        Double price = (type.equals("CATEGORY")) ? null : Double.parseDouble(data.get(3));
        List<String> linkedIds = (type.equals("CATEGORY")) ?
                data.subList(3, data.size()) : data.subList(4, data.size());

        MenuItem item = new MenuItem(id, type, name, price);
        item.linkedIds.addAll(linkedIds);
        items.put(id, item);
    }

    // reconstruct stream
    String reconstruct() {
        return items.values().stream()
                .map(MenuItem::toString)
                .collect(Collectors.joining("\n\n"));
    }
}
