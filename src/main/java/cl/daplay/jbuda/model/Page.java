package cl.daplay.jbuda.model;

import java.io.Serializable;

public interface Page extends Serializable {

    int getTotalPages();

    int getTotalCount();

    int getCurrentPage();
    
}


