package com.entrevista.meli.melishows.Controller;

import com.entrevista.meli.melishows.Model.Dto.SeatDto;
import com.entrevista.meli.melishows.Model.Dto.ShowDto;
import com.entrevista.meli.melishows.Model.Entity.Seat;
import com.entrevista.meli.melishows.Model.Entity.Show;
import com.entrevista.meli.melishows.Model.Service.ISeatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * SeatController para implementar el endpoint para busqueda de butacas libres filtrado por funcion
 */
@RestController
@RequestMapping(path="/seats")
@Tag(name = "Seat", description = "the seat controller")
public class SeatController extends ParentController{

    @Autowired
    ISeatService iSeatService;

    @Operation(description = "Busco las butacas filtradas por el id de la funcion")
    @GetMapping(value = "/function/{functionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SeatDto>> findByFunction(@PathVariable Long functionId)
    {
        ResponseEntity<List<SeatDto>> result;
        List<Seat> lstSeats = iSeatService.findByFunction(functionId);

        if(lstSeats==null)
        {
            result = ResponseEntity.notFound().build();
        }
        else {
            result = ResponseEntity.ok(lstSeats.stream().map(this::convertToDto).collect(Collectors.toList()));
        }

        return result;
    }

    /**
     * Convierte un Seat a SeatDto mediante un modelmapper
     * @param seat
     * @return
     */
    private SeatDto convertToDto(Seat seat) {
        SeatDto seatDto = modelMapper().map(seat, SeatDto.class);

        return seatDto;
    }

}
