package rs.fon.ordinacija.konfiguracija;
import rs.fon.mail.mail.MailSender;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import rs.fon.ordinacija.modeli.Usluga;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailException;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.orm.jpa.EntityManagerProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import rs.fon.ordinacija.baza.DBBroker;
import rs.fon.ordinacija.modeli.Pacijent;
import rs.fon.ordinacija.modeli.Grad;
import rs.fon.ordinacija.modeli.Intervencija;
import rs.fon.ordinacija.modeli.StavkaIntervencije;
import rs.fon.ordinacija.modeli.Stomatolog;
import rs.fon.ordinacija.sistemske_operacije.PacijentSO;
import rs.fon.ordinacija.sistemske_operacije.GradSO;
import rs.fon.ordinacija.sistemske_operacije.IntervencijaSO;
import rs.fon.ordinacija.sistemske_operacije.StomatologSO;
import rs.fon.ordinacija.sistemske_operacije.UslugaSO;


@Configuration
@ComponentScan("rs.fon.ordinacija.kontrolori")
@EnableTransactionManagement
public class ApplicationContextConfig {

    @Bean(name = "viewResolver")
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/forme/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/ordinacija");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;
    }
    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
        sessionBuilder.addAnnotatedClasses(Stomatolog.class, Usluga.class, Grad.class, Pacijent.class, Intervencija.class, StavkaIntervencije.class);
        return sessionBuilder.buildSessionFactory();
    }
    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
        return transactionManager;
    }
  @Autowired
    @Bean(name = "mailSender")
    public MailSender getMailSender(SessionFactory sessionFactory) {
        MailSender mailSender = new rs.fon.mail.mail.MailSender();
        return mailSender;
    }
   
    @Autowired
    @Bean(name = "dbb")
    public DBBroker getDBBroker(SessionFactory sessionFactory) {
        return new DBBroker(sessionFactory);
    }
  
    @Autowired
    @Bean(name = "stomatologSO")
    public StomatologSO getStomatologSO() {
        return new StomatologSO();
    }

    @Autowired
    @Bean(name = "uslugaSO")
    public UslugaSO getUslugaSO() {
        return new UslugaSO();
    }

    @Autowired
    @Bean(name = "gradSO")
    public GradSO getGradSO() {
        return new GradSO();
    }

    @Autowired
    @Bean(name = "pacijentSO")
    public PacijentSO getPacijentSO() {
        return new PacijentSO();
    }

    @Autowired
    @Bean(name = "intervencijaSO")
    public IntervencijaSO getIntervencijaSO() {
        return new IntervencijaSO();
    }

}
