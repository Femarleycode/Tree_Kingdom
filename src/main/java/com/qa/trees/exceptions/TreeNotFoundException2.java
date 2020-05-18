package com.qa.trees.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "The trees doesn't exist")
public class TreeNotFoundException2 extends EntityNotFoundException {
}

