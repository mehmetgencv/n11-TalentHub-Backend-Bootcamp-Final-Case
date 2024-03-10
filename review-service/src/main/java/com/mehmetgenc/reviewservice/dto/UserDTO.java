package com.mehmetgenc.reviewservice.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record UserDTO(Long id,
                      @NotBlank(message = "Name is mandatory")
                      @Size(min = 3, max = 100, message = "Name length should be between 3 and 100")
                      String name,
                      @NotBlank(message = "Surname is mandatory")
                      @Size(min = 3, max = 100, message = "Surname length should be between 3 and 100")
                      String surname,
                      @NotBlank(message = "Email is mandatory")
                      @Size(min = 5, max = 100, message = "Email length should be between 5 and 100")
                      String email) {

}
