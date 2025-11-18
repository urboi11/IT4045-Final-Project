package com.it4045.finalproject.utils;

import jakarta.servlet.http.HttpServletRequest;

public class UtilsForProject {

    public String checkStatus(HttpServletRequest session) {
        try{
            if(session.getSession().getAttribute("CurrentUser").equals(null)) {
                return "redirect:/auth/login";
            }
            return null;
        }
        catch(Exception e){
            return "redirect:/auth/login";
        }
    }
    
}
