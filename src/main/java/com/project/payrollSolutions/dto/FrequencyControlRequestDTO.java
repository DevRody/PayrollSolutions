package com.project.payrollSolutions.dto;

import com.project.payrollSolutions.model.FrequencyControl;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FrequencyControlRequestDTO {
    private Long id;

    @NotNull
    private Double workedHours;

    @NotNull
    private Integer absences;

    @NotNull
    private Integer justifiedAbsences;

    public FrequencyControl transformToFrequencyControl() {
        return new FrequencyControl(this.id, this.workedHours, this.absences, this.justifiedAbsences);
    }
}
