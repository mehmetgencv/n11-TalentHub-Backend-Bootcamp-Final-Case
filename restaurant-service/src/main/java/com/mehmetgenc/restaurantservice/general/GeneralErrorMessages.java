package com.mehmetgenc.restaurantservice.general;

import java.time.LocalDateTime;

public record GeneralErrorMessages(LocalDateTime now, String message, String description) {
}
