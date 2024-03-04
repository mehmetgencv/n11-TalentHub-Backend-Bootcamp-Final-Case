package com.mehmetgenc.reviewservice.general;

import java.time.LocalDateTime;

public record GeneralErrorMessages(LocalDateTime now, String message, String description) {
}
