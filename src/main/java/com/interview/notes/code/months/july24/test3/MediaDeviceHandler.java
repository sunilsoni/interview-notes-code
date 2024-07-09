package com.interview.notes.code.months.july24.test3;

import java.util.HashMap;
import java.util.Map;

/*
Create and implement the given interfaces
You need to create 3 interfaces:
• AudioPlayer with playMp3(String file) method.
• VideoPlayer with playMp4(String file) method.
• TextReader with readText(String file) method.
Then you need to create 3 concrete classes:
• SimpleEBook will implement TextReader interface.
• PocketPlayer will implement AudioPlayer interface.
• iPhone will implement all 3 interfaces.
In the input, you will have pairs «device-file» and you have to check if this device can play the file. And if it can, you should play it.
Input
N is the number of lines with pairs «device-file». Next N lines are these pairs.
Output
You should check each pair and if the device can play the file, you should play it.
Example #1
Input:
3
IPhone let-it-be.mp3
Player titanic.mp4
EBook war-and-peace.txt

Example #1
Input:
3
IPhone let-it-be.mp3
Player titanic.mp4
EBook war-and-peace.txt
Output:
Play let-it-be.mp3 on IPhone
Can't play titanic.mp4 on Player Read war-and-peace.txt on EBook
Example #2
Input:
2
Player let-it-be.mp3
EBook let-it-be.mp3
Output:
Play let-it-be.mp3 on Player
Can't play let-it-be.mp3 on EBook

 */
interface AudioPlayer {
    void playMp3(String file);
}

interface VideoPlayer {
    void playMp4(String file);
}

interface TextReader {
    void readText(String file);
}

class SimpleEBook implements TextReader {
    @Override
    public void readText(String file) {
        System.out.println("Read " + file + " on EBook");
    }
}

class PocketPlayer implements AudioPlayer {
    @Override
    public void playMp3(String file) {
        System.out.println("Play " + file + " on Player");
    }
}

class IPhone implements AudioPlayer, VideoPlayer, TextReader {
    @Override
    public void playMp3(String file) {
        System.out.println("Play " + file + " on IPhone");
    }

    @Override
    public void playMp4(String file) {
        System.out.println("Play " + file + " on IPhone");
    }

    @Override
    public void readText(String file) {
        System.out.println("Read " + file + " on IPhone");
    }
}

public class MediaDeviceHandler {
    private static final Map<String, Object> devices = new HashMap<>();

    static {
        devices.put("EBook", new SimpleEBook());
        devices.put("Player", new PocketPlayer());
        devices.put("IPhone", new IPhone());
    }

    public static void main(String[] args) {
        // Example input for testing
        solve("IPhone", "let-it-be.mp3");
        solve("Player", "titanic.mp4");
        solve("EBook", "war-and-peace.txt");
        solve("Player", "let-it-be.mp3");
        solve("EBook", "let-it-be.mp3");
    }

    public static void solve(String deviceName, String file) {
        if (devices.containsKey(deviceName)) {
            Object device = devices.get(deviceName);
            String fileType = file.substring(file.lastIndexOf('.') + 1);

            boolean handled = false;
            if (device instanceof AudioPlayer && fileType.equals("mp3")) {
                ((AudioPlayer) device).playMp3(file);
                handled = true;
            }
            if (device instanceof VideoPlayer && fileType.equals("mp4")) {
                ((VideoPlayer) device).playMp4(file);
                handled = true;
            }
            if (device instanceof TextReader && fileType.equals("txt")) {
                ((TextReader) device).readText(file);
                handled = true;
            }

            if (!handled) {
                System.out.println("Can't play " + file + " on " + deviceName);
            }
        } else {
            System.out.println("Device '" + deviceName + "' not recognized.");
        }
    }
}
