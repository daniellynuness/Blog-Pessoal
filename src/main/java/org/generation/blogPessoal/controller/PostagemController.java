package org.generation.blogPessoal.controller;

import java.util.List;

import javax.validation.Valid;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
/*INDICA QUE A CLASSE É UMA RESTCONTROLLER, OU SEJA, É RESPONSÁVEL POR RESPONDER TODAS AS REQUISIÇÕES HTTP ENVIADAS PARA UM ENDPOINT 
 (ENDEREÇO) DEFINIDO NA ANOTAÇÃO @REQUESTMAPPING*/

@RequestMapping("/postagens")
//INDICA O ENDPOINT (ENDEREÇO) QUE A CONTROLADORA RESPONDERÁ AS REQUISIÇÕES

@CrossOrigin("*")
/*INDICA QUE A CLASSE CONTROLADORA PERMITIRÁ O RECEBIMENTO DE REQUISIÇÕES REALIZADAS DE FORA DO DOMÍNIO (LOCALHOST, EM NOSSO CASO) AO QUAL 
ELA PERTENCE. ESSA ANOTAÇÃO É ESSENCIAL PARA QUE O FRONT-END (ANGULAR OU REACT), TENHA ACESSO À NOSSA API (O TERMO TÉCNICO É CONSUMIR A API)
EM PRODUÇÃO, O * É SUBSTITUIDO PELO ENDEREÇO DE DOMÍNIO (EXEMPLO: WWW.MEUDOMINIO.COM) DO FRONTEND*/

public class PostagemController {
	
	@Autowired
	private PostagemRepository repository;
	
	//INDICA QUE O MÉTODO ABAIXO RESPONDERÁ TODAS AS REQUISIÇÕES DO TIPO GET QUE FOREM ENVIADAS NO ENDPOINT /POSTAGENS
	@GetMapping
	public ResponseEntity<List<Postagem>> GetAll() {
		//O MÉTODO GETALL() SERÁ DO TIPO RESPONSEENTITY PORQUE ELE RESPONDERÁ A REQUISIÇÃO (REQUEST), COM UMA HTTP RESPONSE (RESPOSTA HTTP).
		/*<LIST<POSTAGEM>>: O MÉTODO ALÉM DE RETORNAR UM OBJETO DA CLASSE RESPONSEENTITY (OK 🡪 200), NO PARÂMETRO BODY (CORPO DA RESPOSTA), 
		SERÁ RETORNADO UM OBJETO DA CLASSE LIST (COLLECTION), CONTENDO TODOS OS OBJETOS DA CLASSE POSTAGEM PERSISTIDOS NO BANCO DE DADOS, NA TABELA 
		 TB_POSTAGENS.*/
		return ResponseEntity.ok(repository.findAll());
		/*EXECUTA O MÉTODO FindAll() (MÉTODO PADRÃO DA INTERFACE JPAREPOSITORY), QUE RETORNARÁ TODOS OS OBJETOS DA CLASSE POSTAGEM PERSISTIDOS NO 
		 * BANCO DE DADOS (<LIST<POSTAGEM>>). COMO A LIST SEMPRE SERÁ GERADA (VAZIA OU NÃO), O MÉTODO SEMPRE RETORNARÁ O STATUS 200 🡪 OK.
 		O MÉTODO FindAll() É EQUIVALENTE A CONSULTA SQL: SELECT * FROM TB_POSTAGENS;*/
	}
	
	/*INDICA QUE O MÉTODO ABAIXO RESPONDERÁ TODAS AS REQUISIÇÕES DO TIPO GET QUE FOREM ENVIADAS NO ENDPOINT /POSTAGENS/ID, ONDE ID É UMA VARIÁVEL
	 DE CAMINHO (PATH VARIABLE), QUE RECEBERÁ EM NOSSO O EXEMPLO O ID QUE VOCÊ DESEJA ENCONTRAR*/
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById(@PathVariable Long id) {
		/*@PATHVARIABLE LONG ID: ANOTAÇÃO QUE INSERE A VARIÁVEL DE CAMINHO (PATH VARIABLE) ID, QUE FOI INFORMADA NO ENDEREÇO DA REQUISIÇÃO, 
		 E INSERE NO PARÂMETRO ID DO MÉTODO GETBYID.*/
		return repository.findById(id).map(resposta -> ResponseEntity.ok(resposta)).orElse(ResponseEntity.notFound().build());
		/*.MAP(RESPOSTA -> RESPONSEENTITY.OK(RESPOSTA)): SE A POSTAGEM EXISTIR, A FUNÇÃO MAP (TIPO OPTIONAL), APLICA O VALOR DO OBJETO RESPOSTA 
		 (OBJETO DO TIPO POSTAGEM COM O RETORNO DO MÉTODO FINDBYID(ID) NO MÉTODO: RESPONSEENTITY.OK(RESPOSTA); E RETORNA O STATUS OK => 200*/
	}
	
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}

	
	//ANOTAÇÃO QUE INDICA QUE O MÉTODO ABAIXO RESPONDERÁ TODAS AS REQUISIÇÕES DO TIPO POST QUE FOREM ENVIADAS NO ENDPOINT /POSTAGENS
	@PostMapping
	public ResponseEntity<Postagem> postPostagem (@Valid @RequestBody Postagem postagem){
		/*@VALID: VALIDA O OBJETO POSTAGEM ENVIADO NO CORPO DA REQUISIÇÃO (REQUEST BODY), CONFORME AS REGRAS DEFINIDAS NA MODEL POSTAGEM. 
		 CASO ALGUM ATRIBUTO NÃO SEJA VALIDADO, O MÉTODO RETORNARÁ UM STATUS 400 => BAD REQUEST.*/
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
		//EXECUTA O MÉTODO SAVE(POSTAGEM) E RETORNA O STATUS CREATED = 201 SE O OBJETO POSTAGEM FOI INSERIDO NA TABELA POSTAGENS NO BANCO DE DADOS.
	}
	
	//ANOTAÇÃO QUE INDICA QUE O MÉTODO ABAIXO RESPONDERÁ TODAS AS REQUISIÇÕES DO TIPO PUT QUE FOREM ENVIADAS NO ENDPOINT /POSTAGENS
	@PutMapping
	public ResponseEntity<Postagem> putPostagem (@RequestBody Postagem postagem) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
		//EXECUTA O MÉTODO SAVE(POSTAGEM) E RETORNA O STATUS OK = 200 SE O OBJETO POSTAGEM FOI ATUALIZADO NA TABELA POSTAGENS NO BANCO DE DADOS.
	}
	
	//INDICA QUE O MÉTODO ABAIXO RESPONDERÁ TODAS AS REQUISIÇÕES DO TIPO DELETE QUE FOREM ENVIADAS NO ENDPOINT /POSTAGENS/ID
	@DeleteMapping("/{id}")
	public void deletePostagem (@PathVariable Long id) {
		repository.deleteById(id);
	}
}