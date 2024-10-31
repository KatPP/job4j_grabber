package ocp;

/**
 * Здесь нарушение при добавлении нового типа платежа (например, "Bitcoin") требует изменения существующего кода.
 * Следовательно, это нарушение, лучше использовать интерфейсы и абстракции для обработки различных типов платежей.
 */

class PaymentProcessor {
    public void processPayment(String paymentType) {
        if (paymentType.equals("CreditCard")) {
            System.out.println("Обработка платежа через кредитную карту.");
        } else if (paymentType.equals("PayPal")) {
            System.out.println("Обработка платежа через PayPal.");
        }
    }
}
