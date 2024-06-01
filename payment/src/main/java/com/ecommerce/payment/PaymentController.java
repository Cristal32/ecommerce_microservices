package com.ecommerce.payment;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // ============================= GET mapping =============================
    @GetMapping("getAll")
    public List<Payment> getAllPayments() {return paymentService.getAllPayments();}

    @GetMapping("getById/{id}")
    public Payment getPayment(@PathVariable("id") Long id) {return paymentService.getPayment(id);}

    // ============================= POST mapping =============================
    @PostMapping("add")
    public Payment registerPayment(@RequestBody RegisterPaymentRequest registerPaymentRequest) throws IOException {
        return paymentService.registerPayment(registerPaymentRequest);
    }

    // ============================= PUT mapping =============================
    /*@PutMapping("update/{id}")
    public Client updateClient(@PathVariable("id") Long id, @RequestBody RegisterClientRequest updateClientRequest) {
        try {
            return clientService.updateClient(id, updateClientRequest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/

    // ============================= DELETE mapping =============================
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deletePayment(@PathVariable("id") Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.ok("com.ecommerce.payment.Payment deleted successfully!");
    }


}
