package rs.fon.ordinacija.kontrolori;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import rs.fon.ordinacija.modeli.Usluga;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import rs.fon.ordinacija.baza.Signal;
import rs.fon.ordinacija.modeli.Intervencija;
import rs.fon.ordinacija.sistemske_operacije.UslugaSO;

@EnableWebMvc
@Controller
@RequestMapping(value = "/usluga")
public class UslugaController {

    @Autowired
    private UslugaSO uslugaSO;

    @ResponseBody
    @RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public List<Usluga> api(ModelMap model, HttpSession session,  @RequestParam(required = false) Integer page) {
        return (List<Usluga>) uslugaSO.pretraga().getRezultat();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView pretraga(ModelMap model, HttpSession session,  @RequestParam(required = false) Integer page) {
        if (session.getAttribute("stomatolog") == null) {
            return new ModelAndView("redirect:/");
        }
        Signal<Usluga> signal = uslugaSO.pretraga();
        ArrayList<Usluga> result =(ArrayList<Usluga>) signal.getRezultat();
        PagedListHolder<Usluga> pagedListHolder = new PagedListHolder<>(result);
        pagedListHolder.setPageSize(5);
        model.addAttribute("maxPages", pagedListHolder.getPageCount());
        if (signal.getUspeh()) {
            
            
            model.addAttribute("uslugaList", pagedListHolder.getPageList());
            model.addAttribute("view", "usluga/pretraga");
        } else {
            model.addAttribute("error", signal.getPoruka());
            model.addAttribute("view", "404");
        }
        
         if(page==null || page < 1 || page > pagedListHolder.getPageCount())page=1;

        model.addAttribute("page", page);
        if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
            pagedListHolder.setPage(0);
            model.addAttribute("uslugaList", pagedListHolder.getPageList());
        }
        else if(page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page-1);
           model.addAttribute("uslugaList", pagedListHolder.getPageList());
        }
        return new ModelAndView("layout", model);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView detalji(@PathVariable int id, ModelMap model, HttpSession session) {
        if (session.getAttribute("stomatolog") == null) {
            return new ModelAndView("redirect:/");
        }
        Signal<Usluga> signal = uslugaSO.ucitaj(id);
        if (signal.getUspeh()) {
            model.addAttribute("usluga", signal.getRezultat());
            model.addAttribute("view", "usluga/detalji");
        } else {
            model.addAttribute("error", signal.getPoruka());
            model.addAttribute("view", "404");
        }
        return new ModelAndView("layout", model);
    }

    @RequestMapping(value = "/unos", method = RequestMethod.GET)
    public ModelAndView unos_get(ModelMap model, HttpSession session) {
        if (session.getAttribute("stomatolog") == null) {
            return new ModelAndView("redirect:/");
        }
        model.addAttribute("view", "usluga/unos");
        return new ModelAndView("layout", model);
    }

    @RequestMapping(value = "/unos", method = RequestMethod.POST)
    public ModelAndView unos_post(@ModelAttribute("form") Usluga usluga, ModelMap model, HttpSession session) {
        if (session.getAttribute("stomatolog") == null) {
            return new ModelAndView("redirect:/");
        }
        Signal<Usluga> signal = uslugaSO.sacuvaj(usluga);
        if (signal.getUspeh()) {
            return new ModelAndView("redirect:/usluga");
        } else {
            model.addAttribute("error", signal.getPoruka());
            model.addAttribute("view", "404");
        }
        return new ModelAndView("layout", model);
    }

    @RequestMapping(value = "/{id}/izmena", method = RequestMethod.GET)
    public ModelAndView izmena_get(@PathVariable int id, ModelMap model, HttpSession session) {
        if (session.getAttribute("stomatolog") == null) {
            return new ModelAndView("redirect:/");
        }
        Signal<Usluga> signal = uslugaSO.ucitaj(id);
        if (signal.getUspeh()) {
            model.addAttribute("usluga", signal.getRezultat());
            model.addAttribute("view", "usluga/izmena");
        } else {
            model.addAttribute("error", signal.getPoruka());
            model.addAttribute("view", "404");
        }
        return new ModelAndView("layout", model);
    }

    @RequestMapping(value = "/{id}/izmena", method = RequestMethod.POST)
    public ModelAndView izmena_post(@PathVariable int id, @ModelAttribute("form") Usluga usluga, ModelMap model, HttpSession session) {
        if (session.getAttribute("stomatolog") == null) {
            return new ModelAndView("redirect:/");
        }
        usluga.setUslugaID(id);
        Signal<Usluga> signal = uslugaSO.izmeni(usluga);
        if (signal.getUspeh()) {
            return new ModelAndView("redirect:/usluga");
        } else {
            model.addAttribute("error", signal.getPoruka());
            model.addAttribute("view", "404");
        }
        return new ModelAndView("layout", model);
    }

    @RequestMapping(value = "/{id}/brisanje", method = RequestMethod.GET)
    public ModelAndView brisanje_get(@PathVariable int id, ModelMap model, HttpSession session) {
        if (session.getAttribute("stomatolog") == null) {
            return new ModelAndView("redirect:/");
        }
        Signal<Usluga> signal = uslugaSO.ucitaj(id);
        if (signal.getUspeh()) {
            model.addAttribute("usluga", signal.getRezultat());
            model.addAttribute("view", "usluga/brisanje");
        } else {
            model.addAttribute("error", signal.getPoruka());
            model.addAttribute("view", "404");
        }
        return new ModelAndView("layout", model);
    }

    @RequestMapping(value = "/{id}/brisanje", method = RequestMethod.POST)
    public ModelAndView brisanje_post(@PathVariable int id, ModelMap model, HttpSession session) {
        if (session.getAttribute("stomatolog") == null) {
            return new ModelAndView("redirect:/");
        }
        Signal<Usluga> signal = uslugaSO.obrisi(id);
        if (signal.getUspeh()) {
            return new ModelAndView("redirect:/usluga");
        } else {
            model.addAttribute("error", signal.getPoruka());
            model.addAttribute("view", "404");
        }
        return new ModelAndView("layout", model);
    }
}
