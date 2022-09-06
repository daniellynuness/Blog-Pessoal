package org.generation.blogPessoal.repository;

import java.util.List;

import org.generation.blogPessoal.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/*INDICA QUE A INTERFACE É DO TIPO REPOSITÓRIO, OU SEJA, É RESPONSÁVEL PELA COMUNICAÇÃO COM O BANCO DE DADOS ATRAVÉS DOS MÉTODOS PADRÃO E DAS 
 METHOD QUERIES (MÉTODOS PERSONALIZADOS), QUE SÃO AS CONSULTAS PERSONALIZADAS CRIADAS ATRAVÉS DE PALAVRAS CHAVE QUE REPRESENTAM AS INSTRUÇÕES 
 DA LINGUAGEM SQL.*/
@Repository
public interface PostagemRepository extends JpaRepository<Postagem,Long> {
	public List<Postagem> findAllByTituloContainingIgnoreCase(@Param("titulo")String titulo);
	
	
}
