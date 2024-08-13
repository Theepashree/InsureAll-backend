//package com.example.insurance.task;
//
//import com.example.insurance.model.AppliedPolicy;
//import com.example.insurance.model.User;
//import com.example.insurance.repository.AppliedPolicyRepository;
//import com.example.insurance.repository.UserRepository;
//import com.example.insurance.service.EmailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//@Component
//public class PaymentReminderTask {
//
//    @Autowired
//    private AppliedPolicyRepository appliedPolicyRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private EmailService emailService;
//
//    @Scheduled(cron = "0 0 8 * * ?") // Run every day at 8 AM
//    public void sendPaymentReminders() {
//        LocalDate tomorrow = LocalDate.now().plusDays(1);
//        List<AppliedPolicy> policies = appliedPolicyRepository.findByNextPaymentDate(tomorrow);
//
//        for (AppliedPolicy policy : policies) {
//            Optional<User> user = userRepository.findByUserName(policy.getUserName());
//            if (user.isPresent()) {
//                String email = user.get().getEmail();
//                String subject = "Payment Reminder";
//                String text = "Dear " + user.get().getFirstName() + ",\n\nThis is a reminder that your payment for policy " + policy.getPolicyName() + " is due tomorrow.\n\nThank you,\nInsurance Company";
//                emailService.sendEmail(email, subject, text);
//            }
//        }
//    }
//}
