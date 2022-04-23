package com.emeka.jedisdemo.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.emeka.jedisdemo.model.Programmer;
import com.emeka.jedisdemo.service.ProgrammerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@RestController
@RequestMapping("/programmer")
public class ProgrammerController {

	@Autowired
	ProgrammerService programmerService;
	// *******************String Demo*******************//

	@PostMapping("/string")
	public void addProgrammer(@RequestBody Programmer programmer) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		programmerService.setProgrammerAsString(String.valueOf(programmer.getId()),
				mapper.writeValueAsString(programmer));

	}

	@GetMapping("/string/{id}")
	public String readString(@PathVariable String id) {
		return programmerService.getProgrammerAsString(id);

	}

	// *******************LIST Demo*******************//

	// add programmer to list
	@PostMapping("/list")
	public void addToProgrammerList(@RequestBody Programmer programmer) {
		programmerService.addToProgrammersList(programmer);

	}

	// get all programmers from a list
	@GetMapping("/list")
	public List<Programmer> getProgrammerListMembers() {
		return programmerService.getProgrammersListMembers();

	}

	// count all programmers in a list
	@GetMapping("/list/count")
	public Long getProgrammerListCount() {
		return programmerService.getProgrammersListCount();

	}

	// *******************SET Demo*******************//

	// add programmers to set
//	@RequestMapping(method = RequestMethod.POST, value = "/programmers-set")
	@PostMapping("/set")
	public void addProgrammersToSet(@RequestBody Programmer... programmers) {
		programmerService.addToProgrammersSet(programmers);

	}

	// get all programmers from a set
//	@RequestMapping(method = RequestMethod.GET, value = "/programmers-set")
	@GetMapping("/set")
	public Set<Programmer> getProgrammersSetMembers() {
		return programmerService.getProgrammersSetMembers();

	}

	// Check if programmer already exists in the set
//	@RequestMapping(method = RequestMethod.POST, value = "/programmers-set/member")
	@PostMapping("/set/member")
	public boolean isSetMember(@RequestBody Programmer programmer) {
		return programmerService.isSetMember(programmer);

	}

	// *****************HASH Demo**********************//

	// add programmer to hash
//	@RequestMapping(method = RequestMethod.POST, value = "/programmers-hash")
	@PostMapping("/hash")
	public void savePHash(@RequestBody Programmer programmer) {
		programmerService.savePHash(programmer);

	}

	// update programmer in hash
//	@RequestMapping(method = RequestMethod.PUT, value = "/programmers-hash")
	@PutMapping("/hash")
	public void updatePHash(@RequestBody Programmer programmer) {
		programmerService.updatePHash(programmer);

	}

	// get all programmers from hash
//	@RequestMapping(method = RequestMethod.GET, value = "/programmers-hash")
	@GetMapping("/hash")
	public Map<Integer, Programmer> FindAllPHash() {
		return programmerService.findAllPHash();

	}

	// get programmer from hash
//	@RequestMapping(method = RequestMethod.GET, value = "/programmers-hash/{id}")
	@GetMapping("/hash/{id}")
	public Programmer findPInHash(@PathVariable int id) {
		return programmerService.findPInHash(id);

	}

	// delete programmer from hash
//	@RequestMapping(method = RequestMethod.DELETE, value = "/programmers-hash/{id}")
	@DeleteMapping("/hash/{id}")
	public void deletePhash(@PathVariable int id) {
		programmerService.deletePhash(id);
	}

}
