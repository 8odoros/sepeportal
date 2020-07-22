package sepe.handlers;

import sepe.config.Constants;

/**
 * Created by Annita on 5/16/2015.
 */
public class SepeRestException extends IllegalStateException {
    public SepeRestException(String message) {
        super(Constants.SEPE_ERROR_PREFIX+message);
    }
}
