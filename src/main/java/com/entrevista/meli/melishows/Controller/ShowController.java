package com.entrevista.meli.melishows.Controller;

import com.entrevista.meli.melishows.Constants.ENUM_SHOW_FIELD_ORDER;
import com.entrevista.meli.melishows.Model.Dto.ShowDto;
import com.entrevista.meli.melishows.Model.Dto.ShowWithFunctionsDto;
import com.entrevista.meli.melishows.Model.Entity.Show;
import com.entrevista.meli.melishows.Model.Service.IShowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ShowController para implementar los endpoints de busqueda de shows
 */
@RestController
@RequestMapping(path="/show")
@Tag(name = "Shows", description = "the show controller")
public class ShowController extends ParentController{
    @Autowired
    IShowService iShowService;

    @Operation(description = "Retorno los shows segun multiples filtros. Las fechas estan en formato yyyy-MM-dd")
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ShowDto>> getAll(@RequestParam(required = false) Date dateFrom, @RequestParam(required = false) Date dateTo, @RequestParam(required = false) Float priceFrom, @RequestParam(required = false) Float priceTo, @RequestParam(defaultValue = "true") boolean ascendant, @RequestParam(defaultValue = "E_NAME") ENUM_SHOW_FIELD_ORDER field)
    {
        ResponseEntity<List<ShowDto>> result;
        List<Show> lstShows = iShowService.findByMultiple(null, dateFrom,dateTo,priceFrom,priceTo);

        Comparator<Show> comp = null;

        switch (field)
        {
            case E_NAME:
                comp = Comparator.comparing(Show::getName);
                break;
            case E_DESCRIPTION:
                comp = Comparator.comparing(Show::getDescription);
                break;
        }

        if(!ascendant)
        {
            comp = comp.reversed();
        }

        lstShows = lstShows.stream().sorted(comp).collect(Collectors.toList());

        if(lstShows==null)
        {
            result = ResponseEntity.notFound().build();
        }
        else {
            result = ResponseEntity.ok(lstShows.stream().map(this::convertToDto).collect(Collectors.toList()));
        }

        return result;
    }

    @Operation(description = "Retorno el show con los detalles de las funciones segun multiples filtros. Las fechas estan en formato yyyy-MM-dd")
    @GetMapping(value = "/showWhitFunctions/{showId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShowWithFunctionsDto> getfunctionsByShow(@PathVariable Long showId, @RequestParam( required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTo, @RequestParam(required = false) Float priceFrom, @RequestParam(required = false) Float priceTo)
    {
        ResponseEntity<ShowWithFunctionsDto> result;

        List<Show> showResults = iShowService.findByMultiple(showId, dateFrom,dateTo,priceFrom,priceTo);

        Show showSelected = (showResults.isEmpty()?null:showResults.get(0));

        if(showSelected==null)
        {
            result = ResponseEntity.notFound().build();
        }
        else {
            result = ResponseEntity.ok(convertWithFunctionsToDto(showSelected));
        }

        return result;
    }

    /**
     * Usa el mapper para convertir el entity Show a ShowWithFunctionsDto
     * @param show
     * @return
     */
    private ShowWithFunctionsDto convertWithFunctionsToDto(Show show) {
        ShowWithFunctionsDto showDto = modelMapper().map(show, ShowWithFunctionsDto.class);

        return showDto;
    }

    /**
     * Usa el mapper para convertir el entity Show a ShowDto
     * @param show
     * @return
     */
    private ShowDto convertToDto(Show show) {
        ShowDto showDto = modelMapper().map(show, ShowDto.class);

        return showDto;
    }

}
