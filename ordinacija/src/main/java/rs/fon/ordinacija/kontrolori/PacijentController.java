package rs.fon.ordinacija.kontrolori;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpSession;
import rs.fon.ordinacija.modeli.Pacijent;
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
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import rs.fon.ordinacija.baza.Signal;
import rs.fon.ordinacija.modeli.Intervencija;
import rs.fon.ordinacija.sistemske_operacije.PacijentSO;

@EnableWebMvc
@Controller
@RequestMapping(value = "/pacijent")
public class PacijentController {

    @Autowired
    private PacijentSO pacijentSO;
    
    

    @ResponseBody
    @RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public List<Pacijent> api(ModelMap model, HttpSession session) {
       List<Pacijent> rezultat=(List<Pacijent>) pacijentSO.pretraga().getRezultat();
        System.out.println(rezultat.toString());
       Collections.sort(rezultat, (Pacijent p1, Pacijent p2) -> p1.getIme().toUpperCase().compareTo(p2.getIme().toUpperCase()));
        System.out.println(rezultat.toString());
        return  rezultat;
              
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy.");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @ResponseBody
    @RequestMapping(value = "/jmbg", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public String jmbg(@RequestParam(value = "jmbg") String jmbg, ModelMap model, HttpSession session) {
        List<Pacijent> list = (List<Pacijent>) pacijentSO.pretraga().getRezultat();
        for (Pacijent pacijent : list) {
            if (pacijent.getJmbg().toLowerCase().trim().equals(jmbg.toLowerCase().trim())) {
                return "{\"valid\":false}";
            }
        }
        return "{\"valid\":true}";
    }
    @ResponseBody
    @RequestMapping(value = "/pretraga", produces = "application/json", method = RequestMethod.GET)
    public List<Pacijent> pretraga(@RequestParam(value = "pretraga") String pretraga, ModelMap model, HttpSession session) {
        
       List<Pacijent> baza = (List<Pacijent>) pacijentSO.pretraga(pretraga);
               
//        for (Pacijent pacijent : baza) {
//            if(pacijent.getIme().toLowerCase().contains(pretraga.toLowerCase())||pacijent.getPrezime().toLowerCase().contains(pretraga.toLowerCase())){
//                rezultat.add(pacijent);
//            }
//        }
//         Collections.sort(rezultat, (Pacijent p1, Pacijent p2) -> p1.getIme().toUpperCase().compareTo(p2.getIme().toUpperCase()));
//        System.out.println(rezultat.toString()+"********");
     return baza;
    }
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView pretraga(ModelMap model, HttpSession session) {
        if (session.getAttribute("stomatolog") == null) {
            return new ModelAndView("redirect:/");
        }
        Signal<Pacijent> signal = pacijentSO.pretraga();
        if (signal.getUspeh()) {
            model.addAttribute("pacijentList", signal.getRezultat());
            model.addAttribute("view", "pacijent/pretraga");
        } else {
            model.addAttribute("error", signal.getPoruka());
            model.addAttribute("view", "404");
        }
        
        return new ModelAndView("layout", model);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView detalji(@PathVariable int id, ModelMap model, HttpSession session) {
        if (session.getAttribute("stomatolog") == null) {
            return new ModelAndView("redirect:/");
        }
        Signal<Pacijent> signal = pacijentSO.ucitaj(id);
        Pacijent p= (Pacijent) signal.getRezultat();
        List<Intervencija> lista = p.getIntervencijaList();
        Collections.sort(lista, (Intervencija i1, Intervencija i2) -> i2.getDatumVreme().compareTo(i1.getDatumVreme()));
        p.setIntervencijaList(lista);
        if (signal.getUspeh()) {
            model.addAttribute("pacijent", p);
            model.addAttribute("view", "pacijent/detalji");
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
        model.addAttribute("view", "pacijent/unos");
        return new ModelAndView("layout", model);
    }

    @RequestMapping(value = "/unos", method = RequestMethod.POST)
    public ModelAndView unos_post(@ModelAttribute("form") Pacijent pacijent, ModelMap model, HttpSession session) {
        if (session.getAttribute("stomatolog") == null) {
            return new ModelAndView("redirect:/");
        }
        System.out.println(pacijent);
        Signal<Pacijent> signal = pacijentSO.sacuvaj(pacijent);
        if (signal.getUspeh()) {
            return new ModelAndView("redirect:/pacijent");
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
        Signal<Pacijent> signal = pacijentSO.ucitaj(id);
        if (signal.getUspeh()) {
            model.addAttribute("pacijent", signal.getRezultat());
            model.addAttribute("view", "pacijent/izmena");
        } else {
            model.addAttribute("error", signal.getPoruka());
            model.addAttribute("view", "404");
        }
        return new ModelAndView("layout", model);
    }

    @RequestMapping(value = "/{id}/izmena", method = RequestMethod.POST)
    public ModelAndView izmena_post(@PathVariable int id, @ModelAttribute("form") Pacijent pacijent, ModelMap model, HttpSession session) {
        if (session.getAttribute("stomatolog") == null) {
            return new ModelAndView("redirect:/");
        }
        pacijent.setPacijentID(id);
        Signal<Pacijent> signal = pacijentSO.izmeni(pacijent);
        if (signal.getUspeh()) {
            return new ModelAndView("redirect:/pacijent");
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
        Signal<Pacijent> signal = pacijentSO.ucitaj(id);
        if (signal.getUspeh()) {
            model.addAttribute("pacijent", signal.getRezultat());
            model.addAttribute("view", "pacijent/brisanje");
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
        Signal<Pacijent> signal = pacijentSO.obrisi(id);
        if (signal.getUspeh()) {
            return new ModelAndView("redirect:/pacijent");
        } else {
            model.addAttribute("error", signal.getPoruka());
            model.addAttribute("view", "404");
        }
        return new ModelAndView("layout", model);
    }
}
