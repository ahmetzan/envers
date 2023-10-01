package com.example.envers.model.request;

import lombok.NonNull;

import java.util.Date;

public record EmployeeSaveRequest(String name,
                                  String surname,
                                  Date birthDate,
                                  @NonNull
                                  Long id) {
}
