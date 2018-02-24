package cl.daplay.jbuda.signer;

import cl.daplay.jbuda.Signer;

public enum NOOPSigner implements Signer {
    INSTANCE;

	public String sign(final String body,
                       final String method,
                       final String path, 
                       long nonce) {
        return "";
    }

}
