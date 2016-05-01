package net.awesomepowered.muddleport;

public class MuddleXception extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MuddleXception() {
        super();
    }

    public MuddleXception(String message) {
        super(message);
    }

    public MuddleXception(Throwable cause) {
        super(cause);
    }

    public MuddleXception(String message, Throwable cause) {
        super(message, cause);
    }

}
