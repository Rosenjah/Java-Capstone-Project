package com.capston;

import com.capston.dao.KidDao;
import com.capston.entities.KidEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("kids")
public class KidController {

    @Autowired
    private KidDao kidDao;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAllKids() {
        ModelAndView mav = new ModelAndView("kids");
        List<KidEntity> kids = kidDao.findAll();
        mav.addObject("kids", kids);
        return mav;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView addKid(KidEntity kidEntity) {
        Long kidId = (Long)kidDao.save(kidEntity);
        kidEntity.setKidId(kidId);
        ModelAndView mav = new ModelAndView("savedKid");
        mav.addObject("savedKid", kidEntity);
        return mav;
    }

    @RequestMapping(path = "/{kidId}", method = RequestMethod.DELETE)
    public ModelAndView delete(@PathVariable Long kidId) {
        KidEntity kidEntity = kidDao.findById(kidId);
        kidDao.delete(kidEntity);
        ModelAndView mav = new ModelAndView("deleteKid");
        mav.addObject(kidEntity);
        return mav;
    }
}
