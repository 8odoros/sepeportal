package sepe.repository;

import org.springframework.data.repository.CrudRepository;
import sepe.domain.general.Document;

import java.util.List;

public interface DocumentRepository extends CrudRepository<Document, Long> {


    public Document findOne(Long id);

    public List<Document> findByDocId(Long docId);

    public List<Document> findAll();


    public void delete(Long id);


    public Document save(Document entity);

}
