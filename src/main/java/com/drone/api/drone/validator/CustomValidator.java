package com.drone.api.drone.validator;


import com.drone.api.drone.dao.DroneRepository;
import com.drone.api.drone.model.DroneLoadDto;
import com.drone.api.drone.model.Medication;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomValidator implements ConstraintValidator<CustomValidation, DroneLoadDto> {

    private final DroneRepository droneRepository;

    public CustomValidator(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    @Override
    public void initialize(CustomValidation constraintAnnotation) {
    }

    @Override
    public boolean isValid(DroneLoadDto droneLoadDto, ConstraintValidatorContext context) {
        double sum = droneLoadDto.getMedicationList().stream()
                .mapToDouble(Medication::getWeightInGram)
                .sum();
        Object eligibility = this.droneRepository.checkDroneLoadingEligibility(droneLoadDto.getDroneId(), sum);
        if (null == eligibility) {
            return true;
        }
        return (Boolean) eligibility;
    }
}

