package com.project.payrollSolutions.service;

import com.project.payrollSolutions.dto.FrequencyControlRequestDTO;
import com.project.payrollSolutions.model.FrequencyControl;
import com.project.payrollSolutions.repository.FrequencyControlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FrequencyControlService {
    private final FrequencyControlRepository frequencyControlRepository;

    @Autowired
    public FrequencyControlService(FrequencyControlRepository frequencyControlRepository) {
        this.frequencyControlRepository = frequencyControlRepository;
    }

    public FrequencyControl createFrequencyControl(FrequencyControl frequencyControl) {
        frequencyControl = frequencyControlRepository.save(frequencyControl);

        return frequencyControl;
    }

    public void updateFrequencyControl(FrequencyControlRequestDTO frequencyControlRequestDTO) {
        Optional<FrequencyControl> frequencyControlOptional = frequencyControlRepository.findById(frequencyControlRequestDTO.getId());

        if (frequencyControlOptional.isPresent()) {
            FrequencyControl frequencyControl = frequencyControlRequestDTO.transformToFrequencyControl();

            frequencyControlRepository.save(frequencyControl);

        } else {
            throw new RuntimeException("FrequencyControl by id " + frequencyControlRequestDTO.getId() + " was not found");
        }
    }

    public void deleteFrequencyControl(Long id) {
        Optional<FrequencyControl> frequencyControlOptional = frequencyControlRepository.findById(id);

        if (frequencyControlOptional.isPresent()) {
            FrequencyControl frequencyControl = frequencyControlOptional.get();

            frequencyControlRepository.delete(frequencyControl);
        } else {
            throw new RuntimeException("FrequencyControl by id " + id + " was not found");
        }
    }
}
