package com.Charan.ProductServiceEcom.Repository;

import com.Charan.ProductServiceEcom.ElasticSearch.ProductDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElasticSearchProductRepository extends ElasticsearchRepository<ProductDocument, String> {


}
