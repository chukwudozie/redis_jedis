package com.emeka.jedisdemo.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.emeka.jedisdemo.model.Programmer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;



@Repository
public class ProgrammerRepositoryImpl implements ProgrammerRepository {

	public static final String REDIS_LIST_KEY = "ProgrammerList";// key to save list as its value
	public static final String REDIS_SET_KEY  = "ProgrammerSET";
	public static final String REDIS_HASH_KEY = "ProgrammerHash";

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Autowired
	@Qualifier("listOperations")
	private ListOperations<String, Programmer> ListOps;

	@Autowired
	@Qualifier("setOperations")
	 private SetOperations<String, Programmer> setOps;

	@Autowired
	HashOperations<String, Integer, Programmer> hashOps;
	
	
	//// ********** String *************

	@Override
	public void setProgrammerAsString(String idKey, String programmer) {
		// Ops for value is used for redis String data type
		redisTemplate.opsForValue().set(idKey, programmer);
		redisTemplate.expire(idKey, 60, TimeUnit.SECONDS);

	}

	@Override
	public String getProgrammerAsString(String idKey) {
		return (String) redisTemplate.opsForValue().get(idKey);
	}

	// ********** List *************
	@Override
	public void addToProgrammersList(Programmer programmer) {
//		redisTemplate.opsForList().leftPush(REDIS_LIST_KEY,programmer) is replaced below
		 ListOps.leftPush(REDIS_LIST_KEY, programmer);
	}

	@Override
	public List<Programmer> getProgrammersListMembers() {
		// Return all the elements of the list
		return ListOps.range(REDIS_LIST_KEY, 0, -1);
	}

	@Override
	public Long getProgrammersListCount() {
		return ListOps.size(REDIS_LIST_KEY);
	}

	//********** Set *************

	/**
	 * Many programmers can be added to the set at once(...).
	 * But the programmers are added on at a time in the list above
	 */
	@Override
	public void addToProgrammersSet(Programmer... programmers) {
		setOps.add(REDIS_SET_KEY, programmers);

	}

	@Override
	public Set<Programmer> getProgrammersSetMembers() {
		// TODO Auto-generated method stub
		return setOps.members(REDIS_SET_KEY);
	}

	@Override
	public boolean isSetMember(Programmer programmer) {
		// TODO Auto-generated method stub
		return Boolean.TRUE.equals(setOps.isMember(REDIS_SET_KEY, programmer));
	}

	 //********** Hash *************
	// Save and update will have the same code as they are both the same

	@Override
	public void saveHash(Programmer programmer) {
		hashOps.put(REDIS_HASH_KEY, programmer.getId(), programmer);
	}

	@Override
	public void updateHash(Programmer programmer) {
		hashOps.put(REDIS_HASH_KEY, programmer.getId(), programmer);

	}

	@Override
	public Map<Integer, Programmer> findAllHash() {
		return hashOps.entries(REDIS_HASH_KEY);
	}

	@Override
	public Programmer findInHash(int id) {
		return hashOps.get(REDIS_HASH_KEY, id);
	}

	@Override
	public void deleteHash(int id) {
		hashOps.delete(REDIS_HASH_KEY, id);
	}
}
