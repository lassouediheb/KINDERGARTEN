package tn.esprit.spring.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Evenements;
import tn.esprit.spring.entity.Jardin;

import tn.esprit.spring.repository.JardinRepository;
import tn.esprit.spring.repository.UserRepository;

@Service
public class JardinServiceImpl implements JardinService {

	@Autowired
	JardinRepository jardinRepository;

	@Autowired
	UserRepository userRepository;

	private static final Logger L = LogManager.getLogger(EvenementsServiceImpl.class);

	@Override
	public List<Jardin> retrieveAllJardin() {
		List<Jardin> jardins = (List<Jardin>) jardinRepository.findAll();

		for (Jardin jardin : jardins) {
			L.info("jardin +++ :" + jardin);
		}
		return jardins;
	}

	@Override
	public List<Jardin> getAllJardin() {
		return (List<Jardin>) jardinRepository.findAll();
	}

	@Override
	public Jardin retrieveJardin(String id) {
		Jardin jardin = jardinRepository.findById(Long.parseLong(id)).orElse(null);
		return jardin;
	}

	@Override
	public Jardin addJardin(Jardin j) {
		Jardin jardin = jardinRepository.save(j);
		return jardin;
	}

	@Override
	public Jardin updateJardin(Jardin j) {
		Jardin jardin = jardinRepository.save(j);
		return jardin;
	}

	@Override
	public void deleteJardin(String id) {
		jardinRepository.deleteById(Long.parseLong(id));
	}
	
	public List<Jardin> listAll(String keyword) {
		if (keyword != null) {
			return jardinRepository.search(keyword);
		}
		return (List<Jardin>) jardinRepository.findAll();
	}

}
