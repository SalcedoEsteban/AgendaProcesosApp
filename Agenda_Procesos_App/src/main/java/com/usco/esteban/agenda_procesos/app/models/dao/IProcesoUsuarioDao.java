package com.usco.esteban.agenda_procesos.app.models.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.usco.esteban.agenda_procesos.app.models.entity.Juzgado;
import com.usco.esteban.agenda_procesos.app.models.entity.ProcesoUsuario;


public interface IProcesoUsuarioDao extends PagingAndSortingRepository<ProcesoUsuario, Long>
{

	@Query("select pu from ProcesoUsuario pu join pu.usuario u where u.id=?1 and pu.proceso.estado = true and pu.proceso.juzgado=?2")
	Page<ProcesoUsuario> findAllById(Long id, Pageable pageable, Juzgado juzgado);
	
	@Query("select pu from ProcesoUsuario pu join pu.usuario u where u.id=?1 and pu.proceso.estado = true and pu.proceso.juzgado=?2")
	List<ProcesoUsuario> findAllById(Long id, Juzgado juzgado);
	
	@Query("select pu from ProcesoUsuario pu join pu.proceso p where p.estado = true and p.juzgado=?1")
	List<ProcesoUsuario> findAll(Juzgado juzgado);
	
	
	@Query("select pu from ProcesoUsuario pu join pu.usuario u where u.id=?1 and pu.proceso.juzgado=?2")
	Page<ProcesoUsuario> findAllBy(Long id, Pageable pageable, Juzgado juzgado);
	
	@Query("select pu from ProcesoUsuario pu join pu.usuario u where u.id=?1 and pu.proceso.estado = true and pu.proceso.radicado like %?2%")
	public Page<ProcesoUsuario> findByIdAndRadicado(Long id, Pageable pageable, String radicado);
	//@Query("select pu from ProcesoUsuario pu where pu.usuario = ?1")
	//Page<ProcesoUsuario> findAllByUsuario(Usuario usuario, Pageable pageable);
	
	@Query("select pu from ProcesoUsuario pu join pu.usuario u where u.id=?1 and pu.proceso.estado = true and pu.proceso.juzgado=?2 and pu.proceso.prioritario=true")
	Page<ProcesoUsuario> findByUsuarioAndEstadoAndJuzgadoAndPrioritario(Long id, Pageable pageable, Juzgado juzgado);
	
	/* query para ver todos los procesos activos de un juzgado para el admin*/
	@Query("select pu from ProcesoUsuario pu join pu.proceso p where p.juzgado=?1")
	Page<ProcesoUsuario> findAllByJuzgado(Juzgado juzgado, Pageable pageable);
	
	/* query para hacer ver los todos los procesos prioritarios y activos de un juzgado*/
	@Query("select pu from ProcesoUsuario pu join pu.proceso p where p.estado = true and p.juzgado=?1 and p.prioritario = true")
	Page<ProcesoUsuario> findAllByPrioritario(Juzgado juzgado, Pageable pageable);
	
	/* query para ver todos los procesos activos para el super_Admin */
	@Query("select pu from ProcesoUsuario pu join pu.proceso p where p.estado = true")
	Page<ProcesoUsuario> findAllBySuperAdmin(Pageable pageable);
}