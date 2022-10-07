package com.capston;

import com.capston.dao.TeamDao;
import com.capston.entities.TeamEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("teams")
public class TeamController {

    @Autowired
    private TeamDao teamDao;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getTeams() {
        ModelAndView mav = new ModelAndView("team");
        List<TeamEntity> teams = teamDao.findAll();
        mav.addObject("teams", teams);
        return mav;
    }
}
