package cl.daplay.jbuda.real;

import java.io.Serializable;

public interface Page extends Serializable {

    int getTotalPages();

    int getTotalCount();

    int getCurrentPage();
    
}


