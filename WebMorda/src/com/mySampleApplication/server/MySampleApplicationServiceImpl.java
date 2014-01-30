package com.mySampleApplication.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mySampleApplication.client.MySampleApplicationService;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MySampleApplicationServiceImpl extends RemoteServiceServlet implements MySampleApplicationService {

    public MySampleApplicationServiceImpl(){

    }
    // Implementation of sample interface method
    public String getMessage(String msg) {
        return "Client said: \"" + msg;
    }

}