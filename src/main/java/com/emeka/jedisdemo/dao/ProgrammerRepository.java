package com.emeka.jedisdemo.dao;

import com.emeka.jedisdemo.model.Programmer;

import java.util.List;
import java.util.Map;
import java.util.Set;


public interface ProgrammerRepository {
	
	// String: Define two Service classes for setting a programmer with the Id as Key and getting the programmer id
		void setProgrammerAsString(String idKey, String programmer);

		String getProgrammerAsString(String idKey);
		
//
//		// list: define three APIs; add a programmer to the list, get the programmers in list and get the list count
		void addToProgrammersList(Programmer programmer);

		List<Programmer> getProgrammersListMembers();

		Long getProgrammersListCount();

//		// Set
		void addToProgrammersSet(Programmer ... programmers);

		Set<Programmer> getProgrammersSetMembers();

		boolean isSetMember(Programmer programmer);
//
//		// Hash
		void saveHash(Programmer programmer);

		void updateHash(Programmer programmer);

		Map<Integer, Programmer> findAllHash();

		Programmer findInHash(int id);

		void deleteHash(int id);

		
}
