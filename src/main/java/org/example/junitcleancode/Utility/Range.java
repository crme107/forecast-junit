package org.example.junitcleancode.Utility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Range {
    private int start;
    private int end;

    public static boolean valueInRange(int value, int start, int end) {
        new Range(start, end);
        return value >= start && value <= end;
    }
}
