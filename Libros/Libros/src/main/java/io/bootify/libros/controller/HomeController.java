package io.bootify.libros.controller;

import io.bootify.libros.domain.Libros;
import io.bootify.libros.model.LibrosDTO;
import io.bootify.libros.service.LibrosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/libros")
public class HomeController {

    @Autowired
    private LibrosService librosService;

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
