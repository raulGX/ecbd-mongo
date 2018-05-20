package com.ecbd.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Bad credentials")
public class UnauthorizedException extends RuntimeException {}
