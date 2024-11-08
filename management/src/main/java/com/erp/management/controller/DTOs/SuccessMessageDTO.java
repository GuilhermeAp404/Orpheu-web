package com.erp.management.controller.DTOs;

public record SuccessMessageDTO<T>(String message, T data) {
}
