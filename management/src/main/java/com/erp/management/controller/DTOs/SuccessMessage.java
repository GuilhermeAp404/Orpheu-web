package com.erp.management.controller.DTOs;

public record SuccessMessage<T>(String message, T data) {
}
