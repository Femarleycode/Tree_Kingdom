package com.qa.tree.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "The tree doesn't exist")
public class TreeNotFoundException2 extends EntityNotFoundException {
}

