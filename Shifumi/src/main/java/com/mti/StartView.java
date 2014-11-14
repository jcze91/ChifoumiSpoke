package com.mti;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

/**
 * Created by JULIEN on 11/14/2014.
 */
public class StartView extends VerticalLayout implements View {
    public StartView() {
        setSizeFull();
        setHeight(800, Unit.PIXELS);

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
                        getUI().getNavigator().navigateTo(Globals.VIEW_LOBBY);
                        User u = new User();
                        u.setName(name.getValue());
                        Lobby.getInstance().getUsers().add(u);

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

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}