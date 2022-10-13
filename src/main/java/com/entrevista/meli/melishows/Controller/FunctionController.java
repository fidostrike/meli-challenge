package com.entrevista.meli.melishows.Controller;

import com.entrevista.meli.melishows.Model.Dto.FunctionDto;
import com.entrevista.meli.melishows.Model.Entity.Function;
import com.entrevista.meli.melishows.Model.Service.IFunctionService;
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
 * FunctionController para implementar de busqueda de funciones filtrado por show
 */
@RestController
@RequestMapping(path="/function")
@Tag(name = "function", description = "the function controller")
public class FunctionController extends ParentController {
    @Autowired
    IFunctionService iFunctionService;

    @Operation(description = "Retorno los datos de las funciones filtradas por el id del show")
    @GetMapping(value = "/show/{showId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FunctionDto>> getfunctionsByShow(@PathVariable Long showId)
    {
        ResponseEntity<List<FunctionDto>> result;
        List<Function> lstFunctions = iFunctionService.findByShow(showId);

        if(lstFunctions==null)
        {
            result = ResponseEntity.notFound().build();
        }
        else {
            result = ResponseEntity.ok(lstFunctions.stream().map(this::convertToDto).collect(Collectors.toList()));
        }

        return result;
    }

    /**
     * Convierte un entity Function a FunctionDto mediante un modelmapper
     * @param function
     * @return
     */
    private FunctionDto convertToDto(Function function) {
        FunctionDto functionDto = modelMapper().map(function, FunctionDto.class);

        return functionDto;
    }

}
