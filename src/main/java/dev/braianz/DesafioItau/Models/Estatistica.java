package dev.braianz.DesafioItau.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estatistica {
    private long count;
    private double sum;
    private double avg;
    private double min;
    private double max;

}
