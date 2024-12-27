package com.anju.demo.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anju.demo.Exception.NoPrivilegeException;
import com.anju.demo.Model.Skill;
import com.anju.demo.Repository.SkillRepository;
import com.anju.demo.component.User;

/** URL Mappings [Ref: Postman >> SpringBootRevision]
	--GET--
	http://localhost:9052/home/listAllSkill
	http://localhost:9052/home/listSkillByStatus?status=1
	http://localhost:9052/home/getSkill?name=Java
	http://localhost:9052/home/getActiveSkill?name=Java&status=1
	--POST--
	http://localhost:9052/home/saveSkill
	http://localhost:9052/home/getSkillBasedOnUser
	--PUT--
	http://localhost:9052/home/updateSkill
	--DELETE--
	http://localhost:9052/home/pathVariableTest/Java-111
	http://localhost:9052/home/pathVariableTest/Java-
	http://localhost:9052/home/deleteSkill
* 
*/

@RestController
@RequestMapping("/home")
public class HomeRestController {
	
	@Autowired
	SkillRepository sr;
	
	@GetMapping("/listAllSkill")
	private List<Skill> listAllSkills() {
		List <Integer> l = new ArrayList(Arrays.asList(1,2,3));
		l.stream().forEach(a -> System.out.println(a));
		return sr.findAll();
	}
	
	@GetMapping("/listSkillByStatus")
	private List<Skill> listSkillByStatus(@RequestParam("status") Integer status){
		return sr.findByIsActive(status);
	}
	
	@GetMapping("/getSkill")
	private List<Skill> getAParticularSkill(@RequestParam("name") String skillName) {
		return sr.findBySkillName(skillName);
	}
	
	@GetMapping("/getActiveSkill")
	private List<Skill> getAnActiveSkill(@RequestParam("name") String skillName, @RequestParam("status") Integer status) {
		return sr.findBySkillNameAndIsActive(skillName, status);
	}
	
	@PostMapping("/getSkillBasedOnUser")
	private List<Skill> getSkillBasedOnUserPrivilege(@RequestBody User user){
		System.out.println(user);
		if(user.getIsAdmin()) return sr.findAll();
		else throw new NoPrivilegeException("User doesnot have the Privilege to view the data");
	}
	
	@PostMapping("/saveSkill")
	private String saveSkill(@RequestBody Skill skill) {
		List<Skill> existing = sr.findBySkillName(skill.getSkillName());
		if(existing.size() > 0) {
			return "Skill already exists";
		}
		else {
			sr.save(skill);
			return "Successfully saved";
		}
	}
	
	@PutMapping("/updateSkill")
	private String updateSkill(@RequestBody Skill skill) {
		Optional<Skill> existing = sr.findById(skill.getIdSkill());
		if(existing.isPresent()) {
			sr.save(skill);
			return "Updates Successfully..";
		}
		else {
			return "Cannot update. Unable to find the skill..";
		}
	}
	
	@DeleteMapping("/pathVariableTest/{pathvariable1}-{pathvariable2}")
	private String pathVariableTest(@RequestBody Skill skill, @PathVariable String pathvariable1, @PathVariable(name="pathvariable2", required=false) Integer p2) {
		System.out.println(pathvariable1);
		System.out.println(p2);
		System.out.println(skill);
		return "Ref: HomeRestController >> pathVariableTest() \ncheck console..";
	}
	
	@DeleteMapping("/deleteSkill")
	private String deleteSkill(@RequestBody Skill skill) {
		Optional<Skill> existing = sr.findById(skill.getIdSkill());
		if(existing.isPresent()) {
			sr.delete(skill);
			return "Deleted Successfully..";
		}else {
			return "Unable to delete.. Cannot find the skill..";
		}
	}
	
	
}
