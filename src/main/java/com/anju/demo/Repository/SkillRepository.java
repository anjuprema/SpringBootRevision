package com.anju.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anju.demo.Model.Skill;

public interface SkillRepository extends JpaRepository<Skill, Integer> {
	public List<Skill> findByIsActive(Integer id_skill);
	public List<Skill> findBySkillName(String skill_name);
	public List<Skill> findBySkillNameAndIsActive(String skill_name, Integer is_active);
}
