package tn.esprit.spring.services;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;

import org.springframework.stereotype.Service;


import tn.esprit.spring.entity.Publicity;
import tn.esprit.spring.repository.PublicityRepository;




@Service 
@EnableScheduling
public class PublicityServiceImpl implements IPublicityService {
	  @Autowired
	    private PublicityRepository PublicityRepository;
	  
		private static final Logger L =(Logger) LogManager.getLogger(PublicityServiceImpl.class);
	
		@Override
	public List<Publicity> retrieveAllPublicities(Long id) {

		
		return PublicityRepository.listPub(id);
		
		}
	
	
		
	
	@Override
	public List<Publicity> retrieveAllPublicitiesPub() {

		
		return PublicityRepository.findAll();
		}
	
	

	

	@Override
	public String addPublicity(Publicity p)  {
		List<Publicity> publicity =new ArrayList<Publicity>();
		publicity=PublicityRepository.findAll();

		
		for( Publicity pub : publicity)
		{
			if (pub.getProductName().equals(p.getProductName()))
			{
				return "cette publicité existe déja";
			}
			
			else PublicityRepository.save(p);
			
	}	
       
		PublicityRepository.save(p);
		return "la publicité est enregistré";
		
	}
	

	
	

	@Override
	public void deletePublicity(int i) {
		PublicityRepository.deleteById(i);
		
	}
	
	

	
	/*@Override
	public List<User> retrieveAllUsers() {
		List<User> users = (List<User>) UserRepository.findAll();
		for (User user : users) {
			L.info("user +++ : " + user);
		}
		return users;
	}*/
    
	
	@Override
	@Transactional
	//@Scheduled(cron="*/10 * * * * ?") 
	public void updatePublicity() {
		System.out.println("manel");
	List<Publicity> pubs =new ArrayList<Publicity>();
	pubs=PublicityRepository.findAll();
	for(Publicity p : pubs){
		if(p.getPriceSponsoring()>200){
			p.setPriceSponsoring(p.getPriceSponsoring()-200);
			
			PublicityRepository.save(p);
			System.out.println(p.getPriceSponsoring());
		}
	}
		
		
	}

	@Override
	//@Transactional
	//@Scheduled(cron="*/10 * * * * ?")
	public void updateRating() {
		
		List<Publicity> publicity =new ArrayList<Publicity>();
		publicity=PublicityRepository.findAll();

	
	for( Publicity p : publicity){
	
			p.setAverage( (PublicityRepository.SommeNote(p.getId()) ) / (PublicityRepository.nbUserNote(p.getId()))  );
			
			PublicityRepository.save(p);
	
		}
		}
	

	@Override
	public Publicity retrievePublicity(int id) {
		Publicity u = PublicityRepository.findById((id)).orElse(null); 
		
		return u;
	}
	
	@Override
	public int addOrUpdatePub(Publicity pub) {
		PublicityRepository.save(pub);
	return pub.getId();
	}
	
	@PersistenceContext
	private EntityManager em;
	public Publicity find(Long id) {
	return em.find(Publicity.class, id);
	}

	@Override
	public byte[] findImage(int imageId) {
		return PublicityRepository.getImage(imageId);
		
	}
	
	
	@Override
	public float getRating(int id) {
		return PublicityRepository.getRating(id);
	
	}
	
	
	

}