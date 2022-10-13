package com.entrevista.meli.melishows.Model.Dto;

import com.entrevista.meli.melishows.Model.Entity.Function;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ShowWithFunctionsDto extends ShowDto{
    private Set<FunctionDto> lstFunction = new HashSet<>();
}
