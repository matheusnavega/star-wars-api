package com.api.starWars.controller;

import com.api.starWars.document.Planet;
import com.api.starWars.dto.EnvelopeResponseDTO;
import com.api.starWars.dto.PlanetDTO;
import com.api.starWars.service.PlanetService;
import com.api.starWars.util.APIUtil;
import com.api.starWars.util.Messages;
import com.api.starWars.util.RouterUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = RouterUtil.PLANETAS)
public class PlanetController {

    @Autowired
    private PlanetService planetService;

    @GetMapping
    @ApiOperation(value = "Método listar os planetas cadastrados")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Existem planetas para listar."),
            @ApiResponse(code = 204, message = "Lista vazia.")
    })
    public ResponseEntity<List<Planet>> listarTodos() {
        List<Planet> lista = planetService.getAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(lista);
    }

    @GetMapping(path = RouterUtil.FIND_BY_ID)
    @ApiOperation(value = "Método listar o planeta pelo id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Exibe os dados do planeta."),
            @ApiResponse(code = 404, message = "Não existe planeta com o id passado.")
    })
    public ResponseEntity<PlanetDTO> findById(@PathVariable(name = "id") String id) {
        Planet planet = planetService.findById(id);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(new PlanetDTO(planet));
    }

    @GetMapping(path = RouterUtil.FIND_BY_NOME)
    @ApiOperation(value = "Método listar o planeta pelo nome")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Exibe os dados do planeta."),
            @ApiResponse(code = 404, message = "Não existe planeta com o nome passado.")
    })
    public ResponseEntity<PlanetDTO> findByName(@PathVariable(name = "nome") String nome) {
        Planet planet = planetService.findByName(nome);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(new PlanetDTO(planet));
    }

    @PostMapping
    @ApiOperation(value = "Método para cadastrar planetas")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Planeta cadastrado com sucesso."),
            @ApiResponse(code = 400, message = "Erro no formulário ou o planeta já foi cadastrado antes."),
            @ApiResponse(code = 503, message = "Erro ao se conectar com a API pública do Star Wars.")
    })
    public ResponseEntity<?> create(@Valid @RequestBody PlanetDTO planetaDto, UriComponentsBuilder uriComponentsBuilder) throws MethodArgumentNotValidException {
        Planet planet = PlanetDTO.toEntity(planetaDto);
        planetService.addPlanet(planet);
        URI uri = uriComponentsBuilder.path("/api/planetas/{id}").buildAndExpand(planet.getId()).toUri();
        return ResponseEntity.created(uri).body
                (new EnvelopeResponseDTO<>(new PlanetDTO(planet), Messages.CREATE_SUCESS));
    }

    @DeleteMapping(path = RouterUtil.FIND_BY_ID)
    @ApiOperation(value = "Método para deletar um planeta pelo id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Exclusão bem sucedida."),
            @ApiResponse(code = 404, message = "Não existe planeta com o id passado.")
    })
    public ResponseEntity<?> remove(@PathVariable(name = "id") String id) {
        this.planetService.removePlanet(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new EnvelopeResponseDTO<>(Messages.DELETE_SUCESS));
    }

}
