package io.bootify.libros.service;

import io.bootify.libros.domain.Libros;
import io.bootify.libros.model.LibrosDTO;
import io.bootify.libros.repos.LibrosRepository;
import io.bootify.libros.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class LibrosService {

    private final LibrosRepository librosRepository;

    public LibrosService(final LibrosRepository librosRepository) {
        this.librosRepository = librosRepository;
    }

    public List<LibrosDTO> findAll() {
        final List<Libros> libroses = librosRepository.findAll(Sort.by("id"));
        return libroses.stream()
                .map(libros -> mapToDTO(libros, new LibrosDTO()))
                .toList();
    }

    public LibrosDTO get(final Long id) {
        return librosRepository.findById(id)
                .map(libros -> mapToDTO(libros, new LibrosDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final LibrosDTO librosDTO) {
        final Libros libros = new Libros();
        mapToEntity(librosDTO, libros);
        return librosRepository.save(libros).getId();
    }

    public void update(final Long id, final LibrosDTO librosDTO) {
        final Libros libros = librosRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(librosDTO, libros);
        librosRepository.save(libros);
    }

    public void delete(final Long id) {
        librosRepository.deleteById(id);
    }

    private LibrosDTO mapToDTO(final Libros libros, final LibrosDTO librosDTO) {
        librosDTO.setId(libros.getId());
        librosDTO.setTitulo(libros.getTitulo());
        librosDTO.setAutor(libros.getAutor());
        librosDTO.setEditorial(libros.getEditorial());
        librosDTO.setCategoria(libros.getCategoria());
        return librosDTO;
    }

    private Libros mapToEntity(final LibrosDTO librosDTO, final Libros libros) {
        libros.setTitulo(librosDTO.getTitulo());
        libros.setAutor(librosDTO.getAutor());
        libros.setEditorial(librosDTO.getEditorial());
        libros.setCategoria(librosDTO.getCategoria());
        return libros;
    }

}
