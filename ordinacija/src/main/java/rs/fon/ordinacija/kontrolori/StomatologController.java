package rs.fon.ordinacija.kontrolori;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import rs.fon.ordinacija.baza.Signal;
import rs.fon.ordinacija.modeli.Stomatolog;
import rs.fon.ordinacija.sistemske_operacije.StomatologSO;

@Controller
@RequestMapping(value = "/")
public class StomatologController {

    @Autowired
    private StomatologSO stomatologSO;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView prijava_get(ModelMap model) {
        model.addAttribute("view", "main");
        return new ModelAndView("layout", model);
    }

    @RequestMapping(value = "/prijava", method = RequestMethod.GET)
    public ModelAndView prijava_get(ModelMap model, HttpSession session) {
        Stomatolog stomatolog = null;
        stomatolog = (Stomatolog) session.getAttribute("stomatolog");
        if (stomatolog != null) {
            return new ModelAndView("redirect:/");
        } else {
            model.addAttribute("view", "stomatolog/prijava");
            return new ModelAndView("layout", model);
        }

    }

    @RequestMapping(value = "/prijava", method = RequestMethod.POST)
    public ModelAndView prijava_post(@ModelAttribute("form") Stomatolog stomatolog, ModelMap model, HttpSession session) {
        Signal<Stomatolog> signal = stomatologSO.prijava(stomatolog);
        if (signal.getUspeh()) {
            session.setAttribute("stomatolog", signal.getRezultat());
            return new ModelAndView("redirect:/");
        } else {
            model.addAttribute("error", signal.getPoruka());
            model.addAttribute("view", "stomatolog/prijava");
            return new ModelAndView("layout", model);
        }
    }

    @RequestMapping(value = "/odjava", method = RequestMethod.GET)
    public ModelAndView odjava(ModelMap model, HttpSession session) {
        session.invalidate();
        return new ModelAndView("redirect:/");
    }

}
