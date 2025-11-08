package ci.ada.services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CrudService<D> {


    D save(D d);

    D saveWithSlug(D d);


    D update(D d);

    D partialUpdate(D d);

    List<D> getAll();

    D getById(Long id);

    D getBySlug(String slug);

    List<D> getAllById(Long id);

    void delete(Long id);

    void deleteAll(List<D> d);

}