package com.rentacar.demo.Controller;

import com.rentacar.demo.Entity.Car;
import com.rentacar.demo.Entity.CarRepository;
import com.rentacar.demo.Entity.Customer;
import com.rentacar.demo.Entity.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private CustomerRepository cusRep;
    @Autowired
    private CarRepository carRep;

    public AdminController(CustomerRepository cusRep) {
        this.cusRep = cusRep;
    }
    List<Car> allcar;

    public void saveImage(MultipartFile file) throws IOException {
        String folder = "src/main/webapp/";
        byte[] bytes = file.getBytes();
        Path path = Paths.get(folder + file.getOriginalFilename());
        Files.write(path,bytes);
    }

    @RequestMapping("/admin")
    public String adminPage(Model model){
        List<Customer> reservations = cusRep.findAll();
        model.addAttribute("reservations",reservations);
        return "admin";
    }
    @RequestMapping("/admin/carmanagement")
        public String listAllCar(Model model){
        allcar = carRep.findAll(Sort.by(Sort.Direction.ASC, "ID"));
        model.addAttribute("allcar",allcar);
        return "carmanagement";
    }

    @RequestMapping(path = "/admin/carmanagement/modifycar", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String modifyCar(@RequestParam("image")MultipartFile file, HttpServletRequest req, Model model) throws IOException {
        if(req.getParameter("changestatus") != null){
            Car update = carRep.getOne(Integer.parseInt(req.getParameter("id")));
            update.setActive(!update.isActive());
            carRep.save(update);
        }
        if(req.getParameter("save") != null){
            Car update = carRep.getOne(Integer.parseInt(req.getParameter("id")));
            update.setName(req.getParameter("carname"));
            update.setPrice(Integer.parseInt(req.getParameter("carprice")));
            if(!file.isEmpty()){
                saveImage(file);
                String imgPath = ("/" + file.getOriginalFilename());
                update.setImage(imgPath);
            }
            carRep.save(update);
        }
        return "redirect:/admin/carmanagement";
    }
    @RequestMapping(path = "/admin/carmanagement/addcar", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String addCar(@RequestParam("image")MultipartFile file, HttpServletRequest req, Model model) throws IOException {
        boolean active;
        active = req.getParameter("isactive") != null;
        Car newcar;
        if(!file.isEmpty())
        {
            saveImage(file);
            String imgPath = ("/" + file.getOriginalFilename());
            newcar = new Car(0,req.getParameter("newcarname"),imgPath,Integer.parseInt(req.getParameter("price")),active);
        }else{
            newcar = new Car(0,req.getParameter("newcarname"),"",Integer.parseInt(req.getParameter("price")),active);}
        carRep.save(newcar);
        return "redirect:/admin/carmanagement";
    }
}
