package practice3;

public class Owner {
    private final String identityNumber;
    private final String fullName;
    private final String email;

    public Owner(String identityNumber, String fullName, String email) {
        if (!identityNumber.matches("\\d{12}")) {
            throw new IllegalArgumentException("Identity number must contain 12 digits");
        }
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException("Invalid email");
        }
        this.identityNumber = identityNumber;
        this.fullName = fullName;
        this.email = email;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    @Override
    public String toString() {
        return fullName + " (" + identityNumber + ", " + email + ")";
    }
}

