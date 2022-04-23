package com.emeka.jedisdemo.service;

import java.util.*;
import java.util.stream.Collectors;

import com.emeka.jedisdemo.dao.ProgrammerRepository;
import com.emeka.jedisdemo.model.Programmer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProgrammerServiceImpl implements ProgrammerService{
	private static final Logger LOGGER = LoggerFactory.getLogger(ProgrammerServiceImpl.class);

	@Autowired
	ProgrammerRepository repository;
	
	@Override
	public void setProgrammerAsString(String idKey, String programmer) {
		LOGGER.info("Programmer with id {} created as {}", idKey, programmer);
		repository.setProgrammerAsString(idKey,programmer);
	}

	@Override
	public String getProgrammerAsString(String key) {
		LOGGER.info("Key: {}",key);
		return 	repository.getProgrammerAsString(key);
	}

	  //List
	@Override
	public void addToProgrammersList(Programmer programmer) {
		LOGGER.info("Programmer  {} added to List", programmer);
		repository.addToProgrammersList(programmer);

	}

	@Override
	public List<Programmer> getProgrammersListMembers() {
		LOGGER.info("Programmers currently in the list are {}", repository.getProgrammersListMembers());
		return  repository.getProgrammersListMembers();
	}

	@Override
	public Long getProgrammersListCount() {
		LOGGER.info("There are {} programmers in the list",repository.getProgrammersListCount());
		return repository.getProgrammersListCount();
	}

	@Override
	public void addToProgrammersSet(Programmer... programmers) {
		LOGGER.info("The programmers added to the set: {}",
		Arrays.stream(programmers).map(Programmer::getName).collect(Collectors.joining()));
		repository.addToProgrammersSet( programmers);

	}

	@Override
	public Set<Programmer> getProgrammersSetMembers() {
		LOGGER.info("All the programmers currently in the set are : {}",
				new HashSet<>(repository.getProgrammersSetMembers()));
		return repository.getProgrammersSetMembers();
	}

	@Override
	public boolean isSetMember(Programmer programmer) {
		LOGGER.warn("The programmer {} in set is {}",programmer.getName(),repository.isSetMember(programmer));
		return repository.isSetMember(programmer);
	}

	 //Hash

	@Override
	public void savePHash(Programmer programmer) {
		repository.saveHash(programmer);
		LOGGER.info("Programmer {} saved to Hash", programmer);

	}

	@Override
	public void updatePHash(Programmer programmer) {
		LOGGER.info("Programmer Before Update: {}",programmer);
		repository.updateHash( programmer);
		LOGGER.info("Programmer after Update: {}",programmer);

	}

	@Override
	public Map<Integer, Programmer> findAllPHash() {
		LOGGER.info("Programmers  are:{}", repository.findAllHash()
				.values().stream().map(programmer -> {
					return String.format("\nProgrammer [\"id\":%s, \"name\":%s, \"company\":%s\n", programmer.getId(), programmer.getName(), programmer.getCompany());
				})
				.collect(Collectors.joining("")));
		return repository.findAllHash();
	}

	@Override
	public Programmer findPInHash(int id) {
		LOGGER.info("Programmer with name {} exists",repository.findInHash(id).getName());
		return repository.findInHash( id);
	}

	@Override
	public void deletePhash(int id) {
		repository.deleteHash(id);

	}

}
