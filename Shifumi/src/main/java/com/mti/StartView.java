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
                    }
                });
        panelContent.addComponent(welcomeLabel);
        panelContent.addComponent(name);
        panelContent.addComponent(button);
        panelContent.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        panel.setContent(panelContent);
        addComponent(panel);
        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}