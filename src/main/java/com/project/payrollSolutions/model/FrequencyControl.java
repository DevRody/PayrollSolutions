package com.project.payrollSolutions.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "frequency_control")
@Entity(name = "frequency_control")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FrequencyControl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double workedHours;

    private Integer absences;

    private Integer justifiedAbsences;

    public FrequencyControl(Double workedHours, Integer absences, Integer justifiedAbsences) {
        this.workedHours = workedHours;
        this.absences = absences;
        this.justifiedAbsences = justifiedAbsences;
    }
}
