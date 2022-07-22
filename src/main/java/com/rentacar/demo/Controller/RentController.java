package com.rentacar.demo.Controller;

import com.rentacar.demo.Entity.Car;
import com.rentacar.demo.Entity.CarRepository;
import com.rentacar.demo.Entity.Customer;
import com.rentacar.demo.Entity.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Date;

import static java.time.temporal.ChronoUnit.DAYS;

@Controller
public class RentController {
    String cusname;
    String cusemail;
    String cusaddress;
    String cusphonenumber;
    Date from;
    Date to;
    int id;
    int price;
    int sumprice;
    @Autowired
    private CustomerRepository cusRep;

    public RentController(CustomerRepository cusRep) {
        this.cusRep = cusRep;
    }

    @Autowired
    private CarRepository carRep;

    public void CarController(CarRepository carRep) {
        this.carRep = carRep;
    }

    public int convertToDays(Date date1,Date date2) {

        long date1InMs = date1.getTime();
        long date2InMs = date2.getTime();

        long timeDiff = 0;
        if(date1InMs > date2InMs) {
            timeDiff = date1InMs - date2InMs;
        } else {
            timeDiff = date2InMs - date1InMs;
        }

        int daysDiff = (int) (timeDiff / (1000 * 60 * 60* 24));
        return daysDiff;
    }

    public static String formatInt(double value) {
        DecimalFormat df = new DecimalFormat("###,###,###");
        return df.format(value);
    }
    @RequestMapping("/index/rentcar")
    public String hello(HttpServletRequest req){
        id = Integer.parseInt(req.getParameter("carid"));
       req.getSession().setAttribute("carid",id);
        from = (Date) req.getSession().getAttribute("pickedfrom");
        to = (Date) req.getSession().getAttribute("pickedto");
        int noOfDaysBetween = convertToDays(from,to);
        req.getSession().setAttribute("noofdays",noOfDaysBetween);
        if(carRep.findById(id).isPresent()) {
             sumprice = noOfDaysBetween * carRep.findById(id).get().getPrice();
        }
        req.getSession().setAttribute("sumprice", formatInt(sumprice));
        return "rentcar";
    }
    @RequestMapping(value = "/index/rentcar/rent", method = RequestMethod.POST)
    public String rent(HttpServletRequest req, Model model){
         cusname = req.getParameter("cusname");
         cusemail = req.getParameter("cusemail");
         cusaddress = req.getParameter("cusaddress");
         cusphonenumber = req.getParameter("cusphonenumber");
         cusRep.save(new Customer(0,cusname,cusemail,cusaddress,cusphonenumber,id,from,to,sumprice));
         req.setAttribute("success","Sikeres foglalás!");
        return "index";
    }
}

