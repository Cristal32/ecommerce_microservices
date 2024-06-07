package com.ecommerce.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;


    public List<Payment> getAllPayments(){
        return paymentRepository.findAll();
    }

    public Payment getPayment(Long id){
        return paymentRepository.findById(id).get();
    }

    public Payment registerPayment(RegisterPaymentRequest request) {
        Payment payment = Payment.builder()
                .clientId(request.clientId())
                .orderId(request.orderId())
                .createdAt(request.createdAt())
                .build();

        // todo: check if email is valid
        // todo: check if email not taken
        // todo: store client in db
        return paymentRepository.saveAndFlush(payment);
    }

    /*public com.ecommerce.payment.Payment updateClient(Long id, RegisterClientRequest request) throws IOException {
        Client client = clientRepository.findById(id).get();
        client.setFirstName(request.firstName());
        client.setLastName(request.lastName());
        client.setEmail(request.email());
        return clientRepository.save(client);
    }*/

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

}
