package ru.netology.web.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class TransferInfo {
        private String amount;
        private String numberCard1;
        private String numberCard2;
        private String idCard1;
        private String idCard2;
        private String wrongNumberCard;
    }

    public static TransferInfo getTransferInfo() {
        return new TransferInfo("1500", "5559000000000001", "5559000000000002"
                , "92df3f1c-a033-48e6-8390-206f6b1f56c0", "0f3f5c2a-249e-4c3d-8287-09f7a039391d"
                , "5560000000000001");
    }

}