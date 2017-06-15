package coffee.synyx.config.oauth;

/**
 * Exception if the public key could not be fetched.
 *
 * @author  Tobias Schneider
 */
class PublicKeyException extends RuntimeException {

    PublicKeyException(String message, Throwable cause) {

        super(message, cause);
    }
}
