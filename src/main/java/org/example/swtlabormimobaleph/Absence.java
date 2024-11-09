package org.example.swtlabormimobaleph;

public enum Absence {
    NO("No"),        // NO for presence
    SICK("Sick"),         // Sick for illness
    VACATION("Vacation"),        // Vacation for holiday
    FLEXTIME("Flex Time"),  // Flextime for flexible hours
    TRAVEL("Travel"),
    WEEKEND("Weekend");     // Weekend for weekends


    private final String label;

    Absence(String label) {
        this.label=label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
}
