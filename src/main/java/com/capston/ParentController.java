package com.capston;

import com.capston.dao.ParentDao;
import com.capston.entities.KidEntity;
import com.capston.entities.ParentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("parents")
public class ParentController {

    @Autowired
    private ParentDao parentDao;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getParents() {
        ModelAndView mav = new ModelAndView("parents");
        List<ParentEntity> parents = parentDao.findAll();
        System.out.println(parents.size());
        mav.addObject("parents", parents);
        mav.addObject("parent", new ParentEntity());
        mav.addObject("kid", new KidEntity());
        return mav;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView addParent(ParentEntity parentEntity) {
        parentDao.save(parentEntity);
        return getParents();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{parentId}")
    public ModelAndView deleteParent(@PathVariable Integer parentId) {
        ParentEntity parentToDelete = parentDao.findById(parentId);
        parentDao.delete(parentToDelete);
        return getParents();
    }
}
