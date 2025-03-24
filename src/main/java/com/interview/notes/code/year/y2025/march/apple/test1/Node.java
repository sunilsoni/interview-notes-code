package com.interview.notes.code.year.y2025.march.apple.test1;

/*

### 💻 **Distributed Systems – Node Count Algorithm Design**

#### **Problem Statement:**

You are tasked with writing a distributed program that runs on a network of state-less nodes arranged in a **linear topology**, like a **linked list**, where each node can only communicate with its **immediate left** and **right neighbors**.

The objective of this program is:
👉 **Each node must determine and print the total number of nodes present in the network.**

---

#### **Topology Overview:**

```
Node 1 --- Node 2 --- Node 3 --- Node 4 --- Node 5 --- Node 6
   |         |           |           |           |         |
 leftmost                                                   rightmost
```

- Node 1 is the **leftmost** node, and Node 6 is the **rightmost** node.
- Each `---` represents a **communication link** between two nodes.
- Nodes **cannot** skip communication; they can only message their **immediate neighbor(s)**.

---

#### **Predefined API Functions (you cannot modify these):**

You are given a set of API functions to interact between nodes. They are defined in the provided code snippets:

##### ✅ Utility Functions:

```c
boolean isLeftMost();
boolean isRightMost();
```

- `isLeftMost()` → returns `true` only for the leftmost node.
- `isRightMost()` → returns `true` only for the rightmost node.

##### ✅ Message Sending:

```c
int sendLeft(Message *m);   // sends message to the left neighbor
int sendRight(Message *m);  // sends message to the right neighbor
```

- You must create a `Message` object and pass it using these functions.
- Returns `0` on success, `-1` if there's no node on that side.

##### ✅ Message Receiving Handlers:

```c
void onReceiveLeft(struct Message *m);  // receives from left neighbor
void onReceiveRight(struct Message *m); // receives from right neighbor
```

- These are callback functions that run in **separate threads** upon receiving messages.

##### ✅ Driver Function (per node):

```c
int main(int argc, char *argv[]);
```

- This is the main execution function for each node.

---

#### 📦 Data Structure:

You can use and modify this struct to pass your data:

```c
typedef struct Message {
   // You can design fields here
} Message;
```

 */
public class Node {

    static int totalNodes = 0;

    // Message class representing messages between nodes
    static class Message {
        int count;
        int type; // 1 = counting phase, 2 = broadcast phase

        public Message(int count, int type) {
            this.count = count;
            this.type = type;
        }
    }

    // Driver function for each node
    public static void main(String[] args) {
        if (isLeftMost()) {
            Message m = new Message(1, 1); // count = 1, type = counting
            sendRight(m);
        }
    }

    // Invoked when message is received from left neighbor
    public static void onReceiveLeft(Message m) {
        if (m.type == 2) { // Broadcast phase
            totalNodes = m.count;
            System.out.println("Final Node Count at node: " + totalNodes);

            if (!isLeftMost()) {
                sendLeft(m);
            }
            // No need to free message manually (Java GC handles it)
        }
    }

    // Invoked when message is received from right neighbor
    public static void onReceiveRight(Message m) {
        if (m.type == 1) { // Counting phase
            m.count += 1;

            if (!isRightMost()) {
                sendRight(m);
            } else {
                // Reached rightmost node, start broadcasting
                m.type = 2;
                sendLeft(m);
            }
        }
    }

    // Placeholder stubs for system-provided methods
    public static boolean isLeftMost() {
        // Stub to be replaced by system
        return false;
    }

    public static boolean isRightMost() {
        // Stub to be replaced by system
        return false;
    }

    public static int sendLeft(Message m) {
        // Stub to be replaced by system
        return 0;
    }

    public static int sendRight(Message m) {
        // Stub to be replaced by system
        return 0;
    }
}
