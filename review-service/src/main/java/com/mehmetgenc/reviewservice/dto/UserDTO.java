package com.mehmetgenc.reviewservice.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record UserDTO(Long id,
                      String name,
                      String surname,
                      String email) {

}
