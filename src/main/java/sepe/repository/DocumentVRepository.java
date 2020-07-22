package sepe.repository;

import org.springframework.data.repository.CrudRepository;
import sepe.domain.general.DocumentV;

import java.util.List;

public interface DocumentVRepository extends CrudRepository<DocumentV, Long> {


    public DocumentV findOne(Long id);

    public List<DocumentV> findByDocId(Long docId);

    public List<DocumentV> findAll();


    public void delete(Long id);


    public DocumentV save(DocumentV entity);

}
