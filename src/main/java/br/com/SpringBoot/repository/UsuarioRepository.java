package br.com.SpringBoot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.SpringBoot.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	@Query(value = "SELECT u FROM Usuario u WHERE u.nome LIKE %?1%")
	public List<Usuario> findByName(String name);

}
