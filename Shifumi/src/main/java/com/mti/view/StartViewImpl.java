package com.mti.view;

import com.mti.Globals;
import com.mti.event.ShowStartViewEvent;
import com.mti.presenter.StartPresenter;
import com.mvplite.event.EventBus;
import com.mvplite.event.EventHandler;
import com.mvplite.event.ShowViewEvent;
import com.mvplite.view.NavigationController;
import com.vaadin.ui.*;

/**
 * Created by JULIEN on 11/14/2014.
 */
public class StartViewImpl extends VerticalLayout implements StartView {

    private static final long serialVersionUID = -8755323074558632618L;

    private final NavigationController navigationController;
    private final EventBus eventBus;
    private final StartPresenter presenter;

    public StartViewImpl(EventBus eventBus,
                         NavigationController navigationController) {

        this.eventBus = eventBus;
        this.navigationController = navigationController;
        this.presenter = new StartPresenter(this, eventBus);
        bind();
        setSizeFull();
        setHeight(800, Unit.PIXELS);
        generateUI();
    }

    private void generateUI() {

        Panel panel = new Panel();
        VerticalLayout panelContent = new VerticalLayout();

        Label welcomeLabel = new Label("Play to ChifumiSpoke");
        TextField name = new TextField("Username");
        Button button = new Button("Enter in the Lobby",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        UI ui = getUI();
                        ui.getSession().setAttribute(Globals.SESSION_USERNAME,
                                name.getValue());
                        presenter.enterLobby(name.getValue());
                    }
                });
        panelContent.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        panelContent.addComponent(welcomeLabel);
        panelContent.addComponent(name);
        panelContent.addComponent(button);
        panel.setContent(panelContent);
        addComponent(panel);
        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
    }

    private void bind(){
        eventBus.addHandler(this);
    }
    @EventHandler
    public void onShowStartViewRequired(ShowStartViewEvent e) {
    }

    @Override
    public String getUriFragment() {
        return "start";
    }

    @Override
    public String getBreadcrumbTitle() {
        return "Some";
    }

    @Override
    public ShowViewEvent getEventToShowThisView() {
        return new ShowStartViewEvent();
    }

}