package com.mehmetgenc.loggingservice.repository;

import com.mehmetgenc.loggingservice.entity.ErrorLog;
import com.mehmetgenc.loggingservice.entity.InfoLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoLogRepository extends JpaRepository<InfoLog, Long> {
}
