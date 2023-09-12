package sn.ept.git.dic2.projetjee.mbeans;

import jakarta.faces.application.NavigationHandler;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named("navigationBean")
public class NavigationBean {
    public String navigateToAnotherPage(String page) {

        FacesContext facesContext = FacesContext.getCurrentInstance();

        NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();

        navigationHandler.handleNavigation(facesContext, null, page);

        // Return null or an empty string to indicate that the navigation has been handled
        return null;
    }
}
