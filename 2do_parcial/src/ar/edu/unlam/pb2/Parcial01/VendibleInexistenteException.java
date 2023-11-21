package ar.edu.unlam.pb2.Parcial01;

public class VendibleInexistenteException extends Exception {
    private static final long serialVersionUID = 1L;

    public VendibleInexistenteException(String message) {
        super(message);
    }
}
