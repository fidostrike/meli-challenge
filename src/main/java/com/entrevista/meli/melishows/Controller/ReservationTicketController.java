package com.entrevista.meli.melishows.Controller;

import com.entrevista.meli.melishows.Model.Dto.ReservationTicketDto;
import com.entrevista.meli.melishows.Model.Dto.ReservationTicketInputDto;
import com.entrevista.meli.melishows.Model.Entity.Function;
import com.entrevista.meli.melishows.Model.Entity.ReservationTicket;
import com.entrevista.meli.melishows.Model.Entity.ReservationTicket_Seat;
import com.entrevista.meli.melishows.Model.Entity.Seat;
import com.entrevista.meli.melishows.Model.Service.IFunctionService;
import com.entrevista.meli.melishows.Model.Service.IReservationTicketService;
import com.entrevista.meli.melishows.Model.Service.ISeatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;

/**
 * ReservationTicketController para implementar los endpoints de busqueda de ticket y de realizacion de reserva
 */
@RestController
@RequestMapping(path="/reservation")
@Tag(name = "ReservationTicket", description = "the ReservationTicket controller")
public class ReservationTicketController extends ParentController{
    @Autowired
    ISeatService iSeatService;

    @Autowired
    IFunctionService iFunctionService;

    @Autowired
    IReservationTicketService iReservationTicketService;

    @Operation(description = "Envio los datos para realizar la reserva")
    @PostMapping(value = "/")
    public ResponseEntity<ReservationTicketDto> doReservation(@RequestBody ReservationTicketInputDto inputDto)
    {
        ResponseEntity<ReservationTicketDto> result;

        //search de seats
        List<Seat> lstSeats = iSeatService.findBySeatIdsFunction(inputDto.getLstSeatIds(),inputDto.getFunctionId());

        if(lstSeats!=null) {
            //sum the price
            Double finalPrice = lstSeats.stream().map(seat -> seat.getGroupSeat().getLstPrice()).flatMap(Collection::stream).mapToDouble(price -> price.getPrice()).sum();

            //search the function
            Function function = iFunctionService.findById(inputDto.getFunctionId());

            ReservationTicket reservationTicket = new ReservationTicket();
            reservationTicket.setDni(inputDto.getDni());
            reservationTicket.setFullName(inputDto.getFullName());
            reservationTicket.setFinalPrice(finalPrice.floatValue());

            ReservationTicket finalReservationTicket = reservationTicket;
            lstSeats.forEach(seat -> {
                ReservationTicket_Seat reservationTicket_seat = new ReservationTicket_Seat();
                reservationTicket_seat.setFunction(function);
                reservationTicket_seat.setReservationTicket(finalReservationTicket);
                reservationTicket_seat.setSeat(seat);
                finalReservationTicket.getLstReservationTicket_Seat().add(reservationTicket_seat);
            });

            reservationTicket = iReservationTicketService.save(reservationTicket);

            URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/reservation/{id}")
                    .buildAndExpand(reservationTicket.getId()).toUri();

            result = ResponseEntity.created(location).build();
        }
        else
        {
            result = ResponseEntity.badRequest().build();
        }

        return result;
    }

    @Operation(description = "Retorno los los datos del ticket de reserva por id")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservationTicketDto> getfunctionsByShow(@PathVariable Long id)
    {
        ResponseEntity<ReservationTicketDto> result;
        ReservationTicket reservationTicket = iReservationTicketService.findById(id);

        if(reservationTicket==null)
        {
            result = ResponseEntity.notFound().build();
        }
        else {

            result = ResponseEntity.ok(convertToDto(reservationTicket));
        }

        return result;
    }

    /**
     * Conviete un ReservationTicket a ReservationTicketDto mediante un modelmapper
     * @param reservationTicket
     * @return
     */
    private ReservationTicketDto convertToDto(ReservationTicket reservationTicket) {
        ReservationTicketDto reservationTicketDto = modelMapper().map(reservationTicket, ReservationTicketDto.class);

        return reservationTicketDto;
    }

}
