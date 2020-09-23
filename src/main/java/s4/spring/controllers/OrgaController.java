package s4.spring.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import s4.spring.models.Organization;
import s4.spring.repositories.GroupeRepository;
import s4.spring.repositories.OrgaRepository;
import s4.spring.repositories.UserRepository;

@Controller
@RequestMapping("/orgas")
public class OrgaController {
     
    @Autowired
    private OrgaRepository orgaRepo;
    @Autowired
    private GroupeRepository groupRepo;
    @Autowired
    private UserRepository userRepo;
    
    
    @RequestMapping("/")
    public String index(Model model) {
    	model.addAttribute("orga",orgaRepo.findAll());
    	return "orgaView";
    }
    
    @RequestMapping("new")
    public String newOrgaForm() {
        return "orgaNew";
    }
    
    @PostMapping("new")
    public void newOrga(Organization orga,HttpServletRequest req, HttpServletResponse resp) {
    	if(orga.getAliases()!="" && orga.getDomain()!="" && orga.getName()!="") {
            orgaRepo.saveAndFlush(orga);
            try {
				resp.sendRedirect("/orgas/");
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	else {
    		try {
				resp.sendRedirect("/orgas/new");
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }
    
    @RequestMapping("edit/{id}")
    public String editOrgaForm(Model model,@PathVariable int id) {
    	model.addAttribute("edit",orgaRepo.findById(id));
    	return "orgaEdit";  
    }
    
    @PostMapping("edit/{id}")
    public void editOrgaForm(Organization orga,@PathVariable int id,HttpServletRequest req, HttpServletResponse resp) {
    	orgaRepo.save(orga);
    	try {
			resp.sendRedirect("/orgas/");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @RequestMapping("delete/{id}")
    public void deleteOrga(@PathVariable int id,HttpServletRequest req, HttpServletResponse resp) {
    	orgaRepo.deleteById(id);
    	try {
			resp.sendRedirect("/orgas/");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @RequestMapping("display/{id}")
    public String displayOrga(Model model,@PathVariable int id) {
    	model.addAttribute("orga",orgaRepo.findById(id));
    	model.addAttribute("users",userRepo.findByOrganization_Id(id));
    	model.addAttribute("groupes",groupRepo.findByOrganization_Id(id));
    	return "orgaDisplay";  
    }
    
    @RequestMapping("details/{id}")
    public String detailsOrga(Model model,@PathVariable int id) {
    	Map<String, Integer> figures = new HashMap<String, Integer>();
    	figures.put("groupes" , groupRepo.findByOrganization_Id(id).size());
    	figures.put("users" , userRepo.findByOrganization_Id(id).size());
    	model.addAttribute("orga",orgaRepo.findAll());
    	model.addAttribute("users",userRepo.findByOrganization_Id(id));
    	model.addAttribute("groupes",groupRepo.findByOrganization_Id(id));
    	model.addAttribute("figures",figures);
    	return "orgaDetails";  
    }
}
