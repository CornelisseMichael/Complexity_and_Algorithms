package com.saxion.nl;

public class Color {

    /**
     * @param nodeColor abbreviation of the color
     * @return String full word of the color
     */
    public static String getFullColor(String nodeColor) {
        switch (nodeColor) {
            case "O":
                return "Orange";
            case "P":
                return "Purple";
            case "G":
                return "Green";
            case "B":
                return "Black";
        }
        return "Undefined";
    }
}
