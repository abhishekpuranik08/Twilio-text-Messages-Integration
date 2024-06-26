public class TwilioUtil {
    static BLogger logger;

    public static void sendTwilioSms(String accountSid, String authToken, String phoneNumber, String message, BLogger logger) {
        try {
            String ACCOUNT_SID = accountSid;
            String AUTH_TOKEN = authToken;
            String toPhoneNumber = phoneNumber;
            boolean countryCode = containsCountryCode(phoneNumber);
            if (!countryCode) {
                toPhoneNumber = 1 + phoneNumber;
            }
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message.creator(new com.twilio.type.PhoneNumber(toPhoneNumber), new com.twilio.type.PhoneNumber("your varified phone number with countrycode"), message).create();

        } catch (Exception e) {
            logger.error(e.getMessage());

        }
    }



    public static boolean containsCountryCode(String phoneNumber) {
        phoneNumber = phoneNumber.replaceAll("[^0-9+]", "");
        return phoneNumber.startsWith("+");
    }
}
