package com.kosta.springbootproject.persistence;

import org.springframework.data.repository.CrudRepository;

import com.kosta.springbootproject.model.ClassRoom;

public interface ClassRoomRepository extends CrudRepository<ClassRoom, Long>{

}
