package com.mti;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Constants;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.ui.UI;

@Theme("mytheme")
@SuppressWarnings("serial")
@Push(value = PushMode.AUTOMATIC)
public class MainUI extends UI
{

    @WebServlet(value = "/*", asyncSupported = true,
            initParams = {
                    @WebInitParam(name = Constants.SERVLET_PARAMETER_PUSH_MODE,
                            value = "automatic")})
    @VaadinServletConfiguration(productionMode = false, ui = MainUI.class)
    public static class Servlet extends VaadinServlet {
    }

    Navigator navigator;

    @Override
    protected void init(VaadinRequest request) {
        // Create a navigator to control the views
        navigator = new Navigator(this, this);

        // Create and register the views
        navigator.addView(Globals.VIEW_START, new StartView());
        navigator.addView(Globals.VIEW_LOBBY, new LobbyView());
        navigator.addView(Globals.VIEW_GAME, new GameView());

        navigator.navigateTo(Globals.VIEW_START);
    }

}
