package pizzashop.service;

import pizzashop.model.MenuDataModel;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;
import pizzashop.validator.PaymentValidator;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PaymentService {

    private MenuRepository menuRepo;
    private PaymentRepository payRepo;
    private PaymentValidator validator;

    public PaymentService(MenuRepository menuRepo, PaymentRepository payRepo, PaymentValidator validator) {
        this.menuRepo = menuRepo;
        this.payRepo = payRepo;
        this.validator = validator;
    }

    public List<MenuDataModel> getMenuData(){ try {
        return menuRepo.getMenu();
    }
    catch(Exception e)  {
        e.printStackTrace();
        System.out.print(e.getMessage());
        return Collections.emptyList();
    }
    }

    public List<Payment> getPayments(){return payRepo.getAll(); }

    public void addPayment(int table, PaymentType type, double amount){
        PaymentValidator.validate(table, amount);
        Payment payment= new Payment(table, type, amount);
        payRepo.add(payment);
    }

    public double getTotalAmount(PaymentType type){
        double total=0.0f;
        List<Payment> l=getPayments();
        if ((l==null) ||(l.size()==0)) return total;
        for (Payment p:l){
            if (p.getType().equals(type))
                total+=p.getAmount();
        }
        return total;
    }

}