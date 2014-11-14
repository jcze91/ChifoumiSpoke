package com.mti;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by JULIEN on 11/14/2014.
 */
public class GameView extends VerticalLayout implements View {
    private final Label youLabel = new Label();
    private final Label youScore = new Label();
    private final Label iaScore = new Label();
    private final Label youShot = new Label();
    private final Label iaShot = new Label();
    private final Label result = new Label();
    User IA = new User();
    User currentUser;

    public GameView() {
        setSizeFull();
        Panel topPanel = new Panel();
        Panel bodyPanel = new Panel();
        topPanel.setHeight(50, Unit.PIXELS);
        VerticalLayout content = new VerticalLayout();
        HorizontalLayout topContent = new HorizontalLayout();
        HorizontalLayout bodyContent = new HorizontalLayout();

        HorizontalLayout youContent = new HorizontalLayout();
        HorizontalLayout iaContent = new HorizontalLayout();
        iaContent.setWidth(200, Unit.PIXELS);
        youContent.setWidth(200, Unit.PIXELS);

        Button quitButton = new Button("Quit",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        UI ui = getUI();
                        getUI().getNavigator().navigateTo(Globals.VIEW_LOBBY);
                    }
                });
        Label iaLabel = new Label("IA");
        IA.setShot(new Shot(IAShot()));

        youContent.addComponent(youLabel);
        youContent.addComponent(youScore);
        iaContent.addComponent(iaLabel);
        iaContent.addComponent(iaScore);
        topContent.addComponent(youContent);
        topContent.addComponent(quitButton);
        topContent.addComponent(iaContent);
        topContent.setComponentAlignment(youContent, Alignment.MIDDLE_LEFT);
        topContent.setComponentAlignment(iaContent, Alignment.MIDDLE_RIGHT);

        bodyContent.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        Button rock = new Button("ROCK",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        currentUser.setShot(new Shot(ShotKind.PAPER));
                        Match m = new Match(currentUser, IA);
                        currentUser.getMatches().add(m);
                        IA.getMatches().add(m);
                        m.doMatch();
                    }
                });
        Button paper = new Button("PAPER",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        currentUser.setShot(new Shot(ShotKind.ROCK));
                        Match m = new Match(currentUser, IA);
                        currentUser.getMatches().add(m);
                        IA.getMatches().add(m);
                        m.doMatch();
                    }
                });
        Button scissors = new Button("SCISSORS",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        currentUser.setShot(new Shot(ShotKind.SCISSORS));
                        Match m = new Match(currentUser, IA);
                        currentUser.getMatches().add(m);
                        IA.getMatches().add(m);
                        m.doMatch();
                    }
                });
        Button spoke = new Button("SPOKE",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        currentUser.setShot(new Shot(ShotKind.SPOKE));
                        Match m = new Match(currentUser, IA);
                        currentUser.getMatches().add(m);
                        IA.getMatches().add(m);
                        m.doMatch();
                    }
                });
        Button lizard = new Button("LIZARD",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        currentUser.setShot(new Shot(ShotKind.LIZARD));
                        Match m = new Match(currentUser, IA);
                        currentUser.getMatches().add(m);
                        IA.getMatches().add(m);
                        m.doMatch();
                    }
                });

        bodyContent.addComponents(rock, paper, scissors, spoke, lizard);

        topPanel.setContent(topContent);
        bodyPanel.setContent(bodyContent);

        setDefaultComponentAlignment(Alignment.TOP_CENTER);

        addComponent(topPanel);
        addComponent(bodyPanel);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        UI ui = getUI();
        String username = (String)ui.getSession().getAttribute(Globals.SESSION_USERNAME);
        Lobby lobby = Lobby.getInstance();
        currentUser = lobby.findUserByName(username);
        youLabel.setValue(username);
    }

    private ShotKind IAShot() {
        final List<ShotKind> VALUES =
                Collections.unmodifiableList(Arrays.asList(ShotKind.values()));
        final int SIZE = VALUES.size();
        final Random RANDOM = new Random();

        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}