package com.mti;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import com.github.wolfie.refresher.Refresher;
import com.mti.event.EnterLobbyViewEvent;
import com.mti.event.ShowGameViewEvent;
import com.mti.event.ShowStartViewEvent;
import com.mti.model.User;
import com.mti.view.GameViewImpl;
import com.mti.view.LobbyViewImpl;
import com.mti.view.StartViewImpl;
import com.mvplite.event.EventBus;
import com.mvplite.event.EventHandler;
import com.mvplite.event.RefresherGlobalEventBusDispatcher;
import com.mvplite.view.LiteNavigationController;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
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
            @WebInitParam(name = Constants.SERVLET_PARAMETER_PRODUCTION_MODE,
                    value = "false"),
            @WebInitParam(name = Constants.SERVLET_PARAMETER_PUSH_MODE,
                    value = "automatic")})
    @VaadinServletConfiguration(productionMode = false, ui = MainUI.class)
    public static class Servlet extends VaadinServlet {
    }

    private final EventBus eventBus = new EventBus();
    private StartViewImpl startView;
    private LobbyViewImpl lobbyView;
    private GameViewImpl gameView;
    private LiteNavigationController navigationController;
    private RefresherGlobalEventBusDispatcher globalDispatcher;
    private User user;

    @Override
    protected void init(VaadinRequest request) {
        bind();
        navigationController = new LiteNavigationController(eventBus);
        navigationController.setFire404OnUnknownUriFragment(false);
        globalDispatcher = new RefresherGlobalEventBusDispatcher(
                "toto", "1",
                null, eventBus);

        Refresher refresher = new Refresher();
        addExtension(refresher);
        startView = new StartViewImpl(eventBus, navigationController);

        setContent(startView);
    }

    @EventHandler
    public void onShowStartViewRequired(ShowStartViewEvent e) {
    }
    @EventHandler
    public void onEnterLobbyEvent(EnterLobbyViewEvent e) {

        user = e.getUser();
        String username = (String)getUI().getSession().getAttribute(Globals.SESSION_USERNAME);
        String sessionId = getSession().getSession().getId();

        lobbyView = new LobbyViewImpl(user, eventBus, navigationController);

        setContent(lobbyView);
        lobbyView.init();
        globalDispatcher = new RefresherGlobalEventBusDispatcher(username, sessionId,
                null, eventBus);

        Refresher refresher = new Refresher();
        addExtension(refresher);
    }

    @EventHandler
    public void onShowGameEvent(ShowGameViewEvent e) {
        gameView = new GameViewImpl(user, eventBus, navigationController);
        setContent(gameView);
        gameView.init();
    }

    private void bind(){
        eventBus.addHandler(this);
    }
}
