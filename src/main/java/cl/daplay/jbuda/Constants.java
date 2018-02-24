package cl.daplay.jbuda;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.LongSupplier;

import static java.lang.System.currentTimeMillis;

public interface Constants {
    /**
     * by default, this client will retry any HTTP error 5 times, returning the fifth Exception.
     *
     * You may customize this number by environment variable "JBUDA.HTTP_MAX_RETRY"
     */
    int HTTP_MAX_RETRY = Integer.parseInt(System.getProperty("JBUDA.HTTP_MAX_RETRY", "5"), 10);

    /**
     * @return default nonce implementation, can't be shared among clients
     */
    static LongSupplier newNonce() {
        return new AtomicLong(currentTimeMillis())::getAndIncrement;
    }

    static DecimalFormat newBigDecimalFormat(){
        final DecimalFormat format = new DecimalFormat();

        final DecimalFormatSymbols decimalFormatSymbols = format.getDecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');

        format.setMaximumFractionDigits(9);
        format.setMinimumFractionDigits(1);
        format.setGroupingUsed(false);
        format.setDecimalFormatSymbols(decimalFormatSymbols);

        return format;
    }
}
