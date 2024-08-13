package com.example.insurance.service;

import com.example.insurance.model.AppliedPolicy;
import com.example.insurance.model.History;
import com.example.insurance.model.User;
import com.example.insurance.repository.AppliedPolicyRepository;
import com.example.insurance.repository.HistoryRepository;
import com.example.insurance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.nio.file.AtomicMoveNotSupportedException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Calendar;
@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private AppliedPolicyRepository appliedPolicyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HistoryRepository historyRepository;


//    @Scheduled(cron = "0 * * * * ?")
    @Scheduled(cron = "0 0 10 * * ?") // At 10:00 AM every day
    public void sendPaymentReminderEmails(){
        try {
            System.out.println("Scheduler triggered");
            java.util.Date today = new java.util.Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(today);
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            java.sql.Date tomorrow = new java.sql.Date(calendar.getTimeInMillis());
            System.out.println("Today: " + today);
            System.out.println("Tomorrow: " + tomorrow);

            List<AppliedPolicy> policies = appliedPolicyRepository.findByNextPaymentDate(tomorrow);
            System.out.println("Policies found: " + policies.size());

            for (AppliedPolicy policy : policies) {
                System.out.println("Processing policy: " + policy.getPolicyName() + " for user " + policy.getUserName());
                Optional<User> userOptional = userRepository.findByUserName(policy.getUserName());
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    String email = user.getEmail();
                    System.out.println("Sending email to: " + email);
                    String emailContent = generateEmailContent(policy, user);
                    sendEmail(email, "Payment Reminder", emailContent);
                } else {
                    System.out.println("User not found for policy: " + policy.getPolicyName());
                }
            }
        } catch (Exception e) {
            System.err.println("Error in sendPaymentReminderEmails: " + e.getMessage());
        }
    }


    //@Scheduled(cron = "0 0 10 * * ?") // At 10:00 AM every day
    @Scheduled(cron = "0 * * * * ?")
    public void sendTenureEndEmails(){
        try {
//            java.sql.Date today = new java.sql.Date();
            java.util.Date today = new java.util.Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(today);
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            java.sql.Date tomorrow = new java.sql.Date(calendar.getTimeInMillis());

            List<AppliedPolicy> policies = appliedPolicyRepository.findByTenure(tomorrow);
            System.out.println(today);
            System.out.println("Policies found: " + policies.size());
            for (AppliedPolicy policy : policies) {
                Optional<User> userOptional = userRepository.findByUserName(policy.getUserName());
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    String email = user.getEmail();
                    String emailContent = generateTenureEndEmailContent(policy, user);
                    sendEmail(email, "Tenure End Notification", emailContent);
                    storeInHistory(policy, today);
                }
            }
        } catch (Exception e) {
            System.err.println("Error in sendTenureEndEmails: " + e.getMessage());
        }
    }


    private void sendEmail(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(message);
            System.out.println("Email sent successfully to " + to);
        } catch (Exception e) {
            System.err.println("Error sending email: " + e.getMessage());
        }
    }

    private String generateEmailContent(AppliedPolicy policy, User user) {
        return String.format(
                "Dear %s,\n\n" +
                        "Your next payment is due tomorrow (%s).\n\n" +
                        "Policy Details:\n" +
                        "Policy Name: %s\n" +
                        "Plan Type: %s\n" +
                        "Customer Name: %s\n" +
//                        "User Name: %s\n" +
                        "Term: %s\n" +
                        "Period: %s\n" +
//                        "Current Date: %s\n" +
                        "Next Payment Date: %s\n" +
                        "Term Amount: %s\n" +
                        "Coverage Amount: %s\n\n" +
                        "Regards,\n" +
                        "insureALL\n" +
                        "Office: CBO XV, No.3, 4th Main Rd, United India Colony, Chennai-600 024.\n" +
                        "Email: projectspring54@gmail.com",
                policy.getCustomerName(),
                policy.getNextPaymentDate(),
                policy.getPolicyName(),
                policy.getPlanType(),
                policy.getCustomerName(),
                policy.getUserName(),
                policy.getTerm(),
                policy.getPeriod(),
                new java.util.Date(),
                policy.getNextPaymentDate(),
                policy.getTermAmount(),
                policy.getCoverageAmount()
        );
    }

    private String generateTenureEndEmailContent(AppliedPolicy policy, User user) {
        return String.format(
                "Dear %s,\n\n" +
                        "Your tenure for the policy %s has ended today (%s).\n\n" +
                        "Regards,\nInsureAll",
                policy.getCustomerName(),
                policy.getPolicyName(),
                policy.getTenure()
        );
    }

    private void storeInHistory(AppliedPolicy policy, Date emailSentDate) {
        History history = new History();
        history.setAppliedPolicyId(policy.getAppliedPolicyId());
        history.setPolicyName(policy.getPolicyName());
        history.setPlanType(policy.getPlanType());
        history.setCustomerName(policy.getCustomerName());
        history.setUserName(policy.getUserName());
        history.setTerm(policy.getTerm());
        history.setPeriod(policy.getPeriod());
        history.setCurrentDate(policy.getCurrentDate());
        history.setNextPaymentDate(policy.getNextPaymentDate());
        history.setTenure(policy.getTenure());
        history.setCoverageAmount(policy.getCoverageAmount());
        history.setTermAmount(policy.getTermAmount());
        history.setStatus(policy.getStatus());
        history.setEmailSentDate(emailSentDate);
        historyRepository.save(history);
    }

}





