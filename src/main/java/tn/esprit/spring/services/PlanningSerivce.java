package tn.esprit.spring.services;

import java.util.Date;
import java.util.List;


import tn.esprit.spring.entity.Planning;
import tn.esprit.spring.entity.User;
import org.springframework.data.repository.query.Param;

public interface PlanningSerivce {
	public int AddPlanning(Planning p);
	public Planning updatePlannig(Planning p);
	public void DeletePlanning(int id);
	 public List<Planning> GetPlByDate(Date date);
	// public List<User> parents(Long id);
		public List<Planning> Getallplan();
	public	List<Planning> getAllPlansByIdJardin(long idJardin);

	
	 public String EnvoiPlanning();
	
	

}
