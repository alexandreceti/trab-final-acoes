package com.javaee.acoes.controllers.v1;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javaee.acoes.domain.Acao;
import com.javaee.acoes.services.AcaoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("This id Acao API")
@RestController
@RequestMapping(AcaoController.BASE_URL)
public class AcaoController {

	public static final String BASE_URL = "/api/v1/acoes";

    private final AcaoService acaoService;

    public AcaoController(AcaoService acaoService) {
        this.acaoService = acaoService;
    }

    @ApiOperation(value = "View List of Acoes", notes="These endpoint will return all acoes")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<Acao> getAll(){
        return acaoService.getAll();
    }

    @ApiOperation(value = "Get User by Id")
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public Acao getById(@PathVariable String id){
        return acaoService.getAcaoById(id);
    }

    @ApiOperation(value = "Create a new User")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Acao create(@RequestBody Acao acao){
        return acaoService.createNewAcao(acao);
    }

    @ApiOperation(value = "Update existing Acao")
    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public Acao updateAcao(@PathVariable String id, @RequestBody Acao acao){
        return acaoService.saveAcao(id, acao);
    }
    

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteAcao(@PathVariable String id){
    	acaoService.deleteAcaoById(id);
    }
}
