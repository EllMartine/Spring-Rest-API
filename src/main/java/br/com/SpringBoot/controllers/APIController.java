package br.com.SpringBoot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.SpringBoot.model.Usuario;
import br.com.SpringBoot.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class APIController {

	@Autowired
	UsuarioRepository usuarioRepository;

	/**
	 *
	 * @param name the name to greet
	 * @return greeting text
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String greetingText(@PathVariable String name) {
		return "Hello " + name + "!";
	}*/

	@RequestMapping(value = "/insertUser/{name}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String insertUser(@PathVariable String name) {
		Usuario usuario = new Usuario(name);
		usuarioRepository.save(usuario);
		return "Usuário cadastrado: " + usuario.toString();
	}

	@GetMapping(value = "/listAll")
	@ResponseBody
	public ResponseEntity<List<Usuario>> listAll() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}

	@PostMapping(value = "/save")
	@ResponseBody
	public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {
		Usuario novoUsuario = usuarioRepository.save(usuario);
		return new ResponseEntity<Usuario>(novoUsuario, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/delete")
	@ResponseBody
	public ResponseEntity<String> delete(@RequestParam Long id) {
		usuarioRepository.deleteById(id);
		return new ResponseEntity<String>("USUÁRIO DELETADO", HttpStatus.OK);
	}

	@GetMapping(value = "/findById")
	@ResponseBody
	public ResponseEntity<Usuario> findById(@RequestParam Long id) {
		Usuario usuario = usuarioRepository.findById(id).get();
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	@PutMapping(value = "/update")
    @ResponseBody
    public ResponseEntity<Usuario> update(@RequestBody Usuario usuario) {
    	Usuario novoUsuario = usuarioRepository.saveAndFlush(usuario);
    	return new ResponseEntity<Usuario>(novoUsuario, HttpStatus.OK);
    }

	@GetMapping(value = "/findByName")
    @ResponseBody
    public ResponseEntity<List<Usuario>> findByName(@RequestParam String name) {
    	List<Usuario> usuarios = usuarioRepository.findByName(name);
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }
}
