package rs.fon.ordinacija.kontrolori;

import java.text.ParseException;
import org.json.JSONObject;



import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpSession;
import rs.fon.ordinacija.modeli.Intervencija;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import rs.fon.mail.mail.MailSender;
import rs.fon.ordinacija.baza.Signal;
import rs.fon.ordinacija.modeli.Pacijent;
import rs.fon.ordinacija.modeli.Stomatolog;
import rs.fon.ordinacija.modeli.Usluga;
import rs.fon.ordinacija.sistemske_operacije.IntervencijaSO;
import rs.fon.ordinacija.sistemske_operacije.PacijentSO;
import rs.fon.ordinacija.sistemske_operacije.UslugaSO;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.xml.soap.MessageFactory;
import org.springframework.beans.support.PagedListHolder;



@EnableWebMvc
@Controller
@RequestMapping(value = "/intervencija")
public class IntervencijaController {
    
        private String API_URL = "https://api.sendgrid.com/v3/mail/send";
	private String API_TOKEN = "Bearer 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";
	private String API_TEMPLATE = "";
    //****twelio sms api keys 
    public static final String ACCOUNT_SID = "AC9fff22e59cdc6cfc87df58e136660c83";
    public static final String AUTH_TOKEN = "7bc4c4a208305e6d5e73968b7977f58a";

    public static final String YOUR_NUMBER = "381638357914";
    public static final String HER_NUMBER = "381638357914";
        
    @Autowired
    private IntervencijaSO intervencijaSO;
    @Autowired
    private PacijentSO pacijentSo;
    @Autowired
    private MailSender mailSender;

    @Autowired
    private UslugaSO uslugaSO;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy. HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @ResponseBody
    @RequestMapping(value = "/stavkaIntervencije", method = RequestMethod.GET)
    public ModelAndView stavkaIntervencije(@RequestParam(value = "rb") int rb, @RequestParam(value = "uslugaID") int uslugaID, ModelMap model) {
        Signal<Usluga> signal = uslugaSO.ucitaj(uslugaID);
        model.addAttribute("rb", rb);
        model.addAttribute("usluga", signal.getRezultat());
        return new ModelAndView("intervencija/stavkaIntervencije", model);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView pretraga(ModelMap model, HttpSession session, @RequestParam(required = false) Integer page) {
        if (session.getAttribute("stomatolog") == null) {
            return new ModelAndView("redirect:/");
        }
        Signal<Intervencija> signal = intervencijaSO.pretraga();
        List<Intervencija> list = (List<Intervencija>) signal.getRezultat();
        List<Intervencija> result = new ArrayList<Intervencija>();
        for(Intervencija i : list){
            if (i.getStatus() == 1) {
                result.add(i);
            }
        }
        if (signal.getUspeh()) {
            model.addAttribute("intervencijaList", result);
            model.addAttribute("view", "intervencija/pretraga");
        } else {
            model.addAttribute("error", signal.getPoruka());
            model.addAttribute("view", "404");
        }
        return new ModelAndView("layout", model);
    }

    @RequestMapping(value = "/rezervacija", method = RequestMethod.GET)
    public ModelAndView rezervacije(ModelMap model, HttpSession session, @RequestParam(required = false) Integer page) {
        if (session.getAttribute("stomatolog") == null) {
            return new ModelAndView("redirect:/");
        }
        Signal<Intervencija> signal = intervencijaSO.pretraga();
        List<Intervencija> list = (List<Intervencija>) signal.getRezultat();
        List<Intervencija> result = new ArrayList<Intervencija>();
        for(Intervencija i : list){
            if (i.getStatus() == 0) {
                result.add(i);
            }
        }
        Collections.sort(result, (Intervencija i1, Intervencija i2) -> i2.getDatumVreme().compareTo(i1.getDatumVreme()));
//        Collections.sort(result, new Comparator<Intervencija>() {
//
//		
//			public int compare(Intervencija i1, Intervencija i2) {
//				
//				return i2.getDatumVreme().compareTo(i1.getDatumVreme());
//			}
//        	
//        	
//		});
        PagedListHolder<Intervencija> pagedListHolder = new PagedListHolder<>(result);
        pagedListHolder.setPageSize(5);
        model.addAttribute("maxPages", pagedListHolder.getPageCount());
        if (signal.getUspeh()) {
            model.addAttribute("intervencijaList", pagedListHolder.getPageList());
            model.addAttribute("view", "intervencija/rezervacija");
        } else {
            model.addAttribute("error", signal.getPoruka());
            model.addAttribute("view", "404");
        }
        if(page==null || page < 1 || page > pagedListHolder.getPageCount())page=1;

        model.addAttribute("page", page);
        if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
            pagedListHolder.setPage(0);
            model.addAttribute("intervencijaList", pagedListHolder.getPageList());
        }
        else if(page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page-1);
           model.addAttribute("intervencijaList", pagedListHolder.getPageList());
        }
        return new ModelAndView("layout", model);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView detalji(@PathVariable int id, ModelMap model, HttpSession session) {
        if (session.getAttribute("stomatolog") == null) {
            return new ModelAndView("redirect:/");
        }
        Signal<Intervencija> signal = intervencijaSO.ucitaj(id);
        if (signal.getUspeh()) {
            model.addAttribute("intervencija", signal.getRezultat());
            model.addAttribute("view", "intervencija/detalji");
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
        model.addAttribute("view", "intervencija/unos");
        return new ModelAndView("layout", model);
    }

    @RequestMapping(value = "/unos", method = RequestMethod.POST)
    public ModelAndView unos_post(@ModelAttribute("form") Intervencija intervencija, ModelMap model, HttpSession session) throws AddressException, MessagingException, ParseException  {
    	System.out.println("usao u kontrolor"+intervencija.getPacijentID()
        );
    	
         SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy. HH:mm");
       
    	
        if (session.getAttribute("stomatolog") == null) {
            return new ModelAndView("redirect:/");
        }
        Stomatolog stomatolog = (Stomatolog) session.getAttribute("stomatolog");
        intervencija.setStomatologID(stomatolog.getStomatologID());
        
        Date d = intervencija.getDatumVreme();
        Instant instant = Instant.ofEpochMilli(d.getTime());
        
        LocalDateTime ldt1 = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
        
        Signal<Intervencija> signaIntervencijal = intervencijaSO.pretraga();
        
        List<Intervencija> list = (List<Intervencija>) signaIntervencijal.getRezultat();
        for (Intervencija i : list) {
           
            Instant instant1 = Instant.ofEpochMilli(i.getDatumVreme().getTime());
            System.out.println("bitno"+intervencija.getDatumVreme().getClass()+"hdhhh a lista "+i.getDatumVreme().getClass());
               
             LocalDateTime ldt2 = LocalDateTime.ofInstant(instant1, ZoneOffset.UTC);
             LocalDateTime tempDateTime = LocalDateTime.from( ldt1 );

             long years = tempDateTime.until( ldt2, ChronoUnit.YEARS);
             tempDateTime = tempDateTime.plusYears( years );

             long months = tempDateTime.until( ldt2, ChronoUnit.MONTHS);
             tempDateTime = tempDateTime.plusMonths( months );

             long days = tempDateTime.until( ldt2, ChronoUnit.DAYS);
             tempDateTime = tempDateTime.plusDays( days );


             long hours = tempDateTime.until( ldt2, ChronoUnit.HOURS);
             tempDateTime = tempDateTime.plusHours( hours );

             long minutes = tempDateTime.until( ldt2, ChronoUnit.MINUTES);
             tempDateTime = tempDateTime.plusMinutes( minutes );

             long seconds = tempDateTime.until( ldt2, ChronoUnit.SECONDS);
             
            if(years==0 && months==0&& hours==0&&minutes<5&&minutes>-5){
            	 System.out.println("ne moze da se zakaze ");
            	 model.addAttribute("error", "Nije moguce zakazati intervenciju zato sto je utom terminu intervenciju vec zakazao  "+i.getPacijent().getIme()+" "+i.getPacijent().getPrezime());
            	 model.addAttribute("view", "404");
            	 return new ModelAndView("layout", model);
             }
           
//             if(years==0 && months==0&& hours==0&&minutes<15){
//            	 System.out.println("ne moze da se zakaze ");
//            	 model.addAttribute("error", "Nije moguce zakazati intervenciju zato sto je utom terminu intervenciju vec zakazao  "+i.getPacijent().getIme()+" "+i.getPacijent().getPrezime());
//            	 model.addAttribute("view", "404");
//            	 return new ModelAndView("layout", model);
//             }
        
   
         }
             

             
            
           
		
        Signal<Intervencija> signal = intervencijaSO.sacuvaj(intervencija);
        Intervencija i = (Intervencija) signal.getRezultat();
        Pacijent p = new Pacijent();
        
        p   = pacijentSo.ucitajPAcijent(intervencija.getPacijentID());
       
       
       posaljiMail(p,intervencija);
     //  posaljiSMS();
        if (signal.getUspeh()) {
            return new ModelAndView("redirect:/intervencija/rezervacija");
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
        Signal<Intervencija> signal = intervencijaSO.ucitaj(id);
        if (signal.getUspeh()) {
            model.addAttribute("intervencija", signal.getRezultat());
            model.addAttribute("view", "intervencija/izmena");
        } else {
            model.addAttribute("error", signal.getPoruka());
            model.addAttribute("view", "404");
        }
        return new ModelAndView("layout", model);
    }

    @RequestMapping(value = "/{id}/izmena", method = RequestMethod.POST)
    public ModelAndView izmena_post(@PathVariable int id, @ModelAttribute("form") Intervencija intervencija, ModelMap model, HttpSession session) {
        if (session.getAttribute("stomatolog") == null) {
            return new ModelAndView("redirect:/");
        }
        intervencija.setIntervencijaID(id);
        if(intervencija.getStavkaIntervencijeList()!=null){
            int rb=0;
            for(int i = 0;i<intervencija.getStavkaIntervencijeList().size();i++){
                if(intervencija.getStavkaIntervencijeList().get(i).getStatus()==1){
                    rb++;
                    intervencija.getStavkaIntervencijeList().get(i).setIntervencijaID(id);
                    intervencija.getStavkaIntervencijeList().get(i).setRedniBrojStavke(rb);
                } else {
                    intervencija.getStavkaIntervencijeList().remove(i);
                }
            }
        }
        intervencija.setStatus(1);
        Signal<Intervencija> signal = intervencijaSO.izmeni(intervencija);
        if (signal.getUspeh()) {
            return new ModelAndView("redirect:/intervencija");
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
        Signal<Intervencija> signal = intervencijaSO.ucitaj(id);
        if (signal.getUspeh()) {
            model.addAttribute("intervencija", signal.getRezultat());
            model.addAttribute("view", "intervencija/brisanje");
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
        Signal<Intervencija> signal = intervencijaSO.obrisi(id);
        if (signal.getUspeh()) {
            return new ModelAndView("redirect:/intervencija/rezervacija");
        } else {
            model.addAttribute("error", signal.getPoruka());
            model.addAttribute("view", "404");
        }
        return new ModelAndView("layout", model);
    }

    private String posaljiMail(Pacijent p, Intervencija i) throws AddressException{
        
        Date d = i.getDatumVreme();
        Instant instant = Instant.ofEpochMilli(d.getTime());
        
        LocalDateTime date = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
        String poruka = "Postovani " + p.getIme() + " " + p.getPrezime() + ",  imate zakazanu stomatolosku intervenciju  " + date.format(DateTimeFormatter.ISO_DATE) + ".";
            String jsonRequest = 
		 "{"
		+"	\"personalizations\": ["
		+"		{"
		+"			\"to\": ["
		+"				{"
		+"					\"email\": "
		+ "\"" + "mrhanac@gmail.com" + "\""
		+"				}"
		+"			],"
		+"			\"substitutions\": {"
		+"				\":name\": "
		+ "\"" + "dsdsdsds" + "\","
		+"				\":link\": "
		+ "\"" + "ddsdsdsdsdsds" + "\""
		+"			}"
		+"		}"
		+"	],"
		+"	\"from\": {"
		+"		\"email\": \"sasa@teodesk.com\","
		+"		\"name\": \"Stomatoloska Ordinacija Lecron\""
		+"	},"
		//+"	\"template_id\": \"46d824f8-979e-49cd-ad0a-316cd2d81c01\","
		+"	\"subject\": \"Zakazana intervencija \","
		+"	\"content\": ["
		+"		{"
		+"			\"type\": \"text/html\","
		+"			\"value\": \""+poruka+"\""
		+"		}"
		+"	]"
		+"}";
	
	
	RestTemplate restTemplate = new RestTemplate();
	
	try{
		
		System.out.println(jsonRequest);
		
		// create request body
		JSONObject request = new JSONObject(jsonRequest);

		// set headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", API_TOKEN);
		HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);

		// send request and parse result
		ResponseEntity<String> loginResponse = restTemplate
		  .exchange(API_URL, HttpMethod.POST, entity, String.class);
		
		if (loginResponse.getStatusCode() == HttpStatus.OK) {
			System.out.println("STATUS OK");
		} else {
			System.out.println("STATUS BAD");
		}
		
	} catch(Exception e){
		e.printStackTrace();
	}
	
			return "500";
		}

  
    }
            
          

    

