package com.ruraaratech.p4dafrica.Document.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileRequest {
    @NotBlank(message ="sector id cannot be blank")
    private long sectorId;
    @NotBlank(message ="tittle cannot be blank")
    private String title;
    @NotBlank(message ="int year cannot be blank")
    private int year;
    @NotBlank(message ="int month cannot be blank")
    private int month;
}
