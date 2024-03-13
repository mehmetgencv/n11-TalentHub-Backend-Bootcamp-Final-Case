package com.mehmetgenc.loggingservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class InfoLog {

    @Id
    @GeneratedValue(generator = "InfoLog", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "InfoLog", sequenceName = "INFO_LOG_ID_SEQ")
    private Long id;

    private LocalDateTime date;
    private String message;
    private String description;
}
