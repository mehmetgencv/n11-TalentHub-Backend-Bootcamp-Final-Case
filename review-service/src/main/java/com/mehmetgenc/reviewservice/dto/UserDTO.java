package com.mehmetgenc.reviewservice.dto;

import com.mehmetgenc.reviewservice.entity.enums.Gender;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record UserDTO(Long id,
                      String name,
                      String surname,
                      String email,
                      Gender gender,
                      Double latitude,
                      Double longitude) {

}
