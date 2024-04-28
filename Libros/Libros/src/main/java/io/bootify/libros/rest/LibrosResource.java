package io.bootify.libros.rest;

import io.bootify.libros.model.LibrosDTO;
import io.bootify.libros.service.LibrosService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/libross", produces = MediaType.APPLICATION_JSON_VALUE)
public class LibrosResource {

    private final LibrosService librosService;

    public LibrosResource(final LibrosService librosService) {
        this.librosService = librosService;
    }

    @GetMapping
    public ResponseEntity<List<LibrosDTO>> getAllLibross() {
        return ResponseEntity.ok(librosService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibrosDTO> getLibros(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(librosService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createLibros(@RequestBody @Valid final LibrosDTO librosDTO) {
        final Long createdId = librosService.create(librosDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateLibros(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final LibrosDTO librosDTO) {
        librosService.update(id, librosDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibros(@PathVariable(name = "id") final Long id) {
        librosService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
