package org.generation.blogPessoal.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity // INDICA QUE A CLASSE É UMA ENTIDADE, OU SEJA, ELE SERÁ UTILIZADA PARA GERAR UMA TABELA NO BANCO DE DADOS.
@Table(name = "postagem") // INDICA O NOME DA TABELA NO BANCO DE DADOS.
public class Postagem {

	@Id // INDICA QUE O ATRIBUTO É A CHAVE PRIMÁRIA DA TABELA
	@GeneratedValue(strategy = GenerationType.IDENTITY) // @GeneratedValue INDICA QUE A CHAVE PRIMÁRIA SERÁ GERADA AUTOMATICAMENTE PELO BANCO DE DADOS.
	//GenerationType.IDENTITY INDICA QUE SERÁ UMA SEQUÊNCIA NUMÉRICA INICIANDO EM 1 E SERÁ RESPONSABILIDADE DO BANCO DE DADOS GERAR ESTA SEQUÊNCIA.
	private Long id;

	@NotNull // INDICA QUE UM ATRIBUTO NÃO PODE SER NULO
	@Size(min = 5, max = 100) // TEM A FUNÇÃO DE DEFINIR O TAMANHO MINIMO E MÁXIMO DE CARACTERES DE UM ATRIBUTO STRING.
	private String titulo;

	@NotNull
	@Size(min = 10, max = 500)
	private String texto;

	@UpdateTimestamp // INDICA SE O ATRIBUTO RECEBERÁ UM TIMESTAMP (DATA E HORA DO SISTEMA) E SEMPRE QUE A POSTAGEM FOR ATUALIZADA O ATRIBUTO TAMBÉM SERÁ
	private LocalDateTime data;

	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
