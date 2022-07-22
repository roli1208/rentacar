package com.rentacar.demo.Controller;

import com.rentacar.demo.Entity.Car;
import com.rentacar.demo.Entity.CarRepository;
import com.rentacar.demo.Entity.Customer;
import com.rentacar.demo.Entity.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Controller
public class CarController {
    @Autowired
    private CustomerRepository cusRep;
    @Autowired
    private CarRepository carRep;

    public CarController(CarRepository carRep) {
        this.carRep = carRep;
    }


    public List<Integer> notRentedCarIds(){
        List<Customer> all = cusRep.findAll();
        List<Car> cars = carRep.findAll();
        HashSet<Integer> rentedIDs = new HashSet<>();
        List<Integer> result = new ArrayList<>();
        for (Customer customer:
            all){
            rentedIDs.add(customer.getCarID());
        }
        for (Car car:
             cars) {
            if(!rentedIDs.contains(car.getID()) && car.isActive()){
                result.add(car.getID());
            }
        }
        return result;
    }
    @RequestMapping("/")
    public String welcome(){
        return "redirect:/index";
    }
    @RequestMapping(value = "/index")
    public String listCars(HttpServletRequest req, Model model) throws ParseException {
        if(req.getParameter("from") == null || req.getParameter("to") == null){
            return "index";
        }
        Date from;
        Date to ;
        from = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("from"));
        to = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("to"));
        if(from.after(to)){
            req.setAttribute("error","Hibás foglalási adatok!");
            return "index";
        }
        Instant dateInst = from.toInstant().plus(1,ChronoUnit.DAYS);
        ZonedDateTime actualDate = ZonedDateTime.now();
        if(dateInst.compareTo(actualDate.toInstant().truncatedTo(ChronoUnit.DAYS)) <= 0){
            req.setAttribute("error", "A foglalás kezdete nem lehet az aktuális dátum előtt!");
            return "index";
        }
        req.getSession().setAttribute("pickedfrom",from);
        req.getSession().setAttribute("pickedto",to);
        List<Customer> all = cusRep.findAll();
        int i = 0;
        int actualCarID;
        List<Car> result = new ArrayList<>(carRep.findAllById(notRentedCarIds()));
        while (i < all.size()) {
                if ((from.compareTo(all.get(i).getFromDate()) * all.get(i).getFromDate().compareTo(to)) < 0) { //chosen "from" not in a rent interval
                    if ((to.compareTo(all.get(i).getToDate()) * all.get(i).getToDate().compareTo(to)) < 0) //chosen "to" not in a rent interval
                    {
                        actualCarID = all.get(i).getCarID();
                        Car actualcar = new Car();
                        if(carRep.findById(actualCarID).isPresent())
                             actualcar = carRep.findById(actualCarID).get();
                        if(!result.contains(actualcar) && actualcar.isActive())
                            result.add(actualcar);
                    }
                }
           i++;
        }
        model.addAttribute("cars",result);
        req.getSession().setAttribute("carid",req.getParameter("carid"));
        return "index";
    }
}
