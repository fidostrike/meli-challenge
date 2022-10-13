package com.entrevista.meli.melishows.Model.Dto;

import com.entrevista.meli.melishows.Model.Entity.Function;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
public class PriceDto {
    private Long id;

    private Float price;

    private FunctionDto function;
}
