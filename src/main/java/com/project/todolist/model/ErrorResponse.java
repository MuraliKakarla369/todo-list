package com.project.todolist.model;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {

    private List<ErrorEntry> errors = new ArrayList<>();

    public List<ErrorEntry> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorEntry> errors) {
        this.errors = errors;
    }
}
