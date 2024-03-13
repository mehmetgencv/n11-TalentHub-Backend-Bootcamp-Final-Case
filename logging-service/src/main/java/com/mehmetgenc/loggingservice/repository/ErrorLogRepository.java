package com.mehmetgenc.loggingservice.repository;

import com.mehmetgenc.loggingservice.entity.ErrorLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorLogRepository extends JpaRepository<ErrorLog, Long> {
}
