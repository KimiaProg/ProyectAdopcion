package com.animals.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import com.animals.models.SeguimientoModel;
import com.animals.models.SolicitudAnimalModel;

@Repository
@Transactional
public class SolicitudAnimalDAO {

	 @PersistenceContext
	 private EntityManager entityManager ;

	   	public void create(SolicitudAnimalModel request) {
	         entityManager.persist(request);
	    }
	
	    @SuppressWarnings("unchecked")
		public List<SolicitudAnimalModel> getAll() {
	    	return (List<SolicitudAnimalModel>) entityManager.createQuery(" from solicitudAnimal").getResultList();
	    }
	   
	    @SuppressWarnings("unchecked")
		public List<SolicitudAnimalModel> getSolicitudesDeUnUsuario(String nombreUsuario) {
	    	return (List<SolicitudAnimalModel>) entityManager.createNativeQuery("select s.nombreUsuario , animales.nombre from solicitudAnimal s, animales where s.animalesDNI = animales.DNI and animales.DNI in ("
	    			+ "select a.DNI from usuarios u , publicaciones p , animales a where u.nombreUsuario = p.nombreUsuario and p.id = a.publicaciones_id and u.nombreUsuario= '"+nombreUsuario+"'"
	    			+ ")").getResultList();
	    }
	    
	    @SuppressWarnings("unchecked")
		public List<SeguimientoModel> getSolicitud(String nombreUsuario , String animalesDNI, String fecha ) {
	        return (List<SeguimientoModel>) entityManager.createQuery(" from solicitudAnimal WHERE nombreUsuario = '"+nombreUsuario+"' and animalesDNI= '"+animalesDNI+"' and fecha= '"+fecha+"'").getResultList();
	    }

	    public void delete( String nombreUsuario , String animalesDNI, String fecha) {
	        if (getSolicitud(nombreUsuario ,  animalesDNI,  fecha).size()>0) {
		    	 entityManager.createNativeQuery("UPDATE solicitudAnimal SET deleted=true,estado='rechazada' WHERE nombreUsuario = '"+nombreUsuario+"' and animalesDNI= '"+animalesDNI+"' and fecha= '"+fecha+"'").executeUpdate();
	        }

	    }
}
