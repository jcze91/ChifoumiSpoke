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
    private final Button rock;
    private final Button scissors;
    private final Button paper;
    private final Button lizard;
    private final Button spoke;
    private final Button nextPlay;
    User IA = new User();
    User currentUser;
    private Match m = null;

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
        IA.setName("IA");
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

        rock = new Button("ROCK",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        currentUser.setShot(new Shot(ShotKind.PAPER));
                        m = new Match(currentUser, IA);
                        currentUser.getMatches().add(m);
                        IA.getMatches().add(m);
                        m.doMatch();
                        displayWinner();
                    }
                });
        paper = new Button("PAPER",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        currentUser.setShot(new Shot(ShotKind.ROCK));
                        m = new Match(currentUser, IA);
                        currentUser.getMatches().add(m);
                        IA.getMatches().add(m);
                        m.doMatch();
                        displayWinner();

                    }
                });
        scissors = new Button("SCISSORS",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        currentUser.setShot(new Shot(ShotKind.SCISSORS));
                        m = new Match(currentUser, IA);
                        currentUser.getMatches().add(m);
                        IA.getMatches().add(m);
                        m.doMatch();
                        displayWinner();

                    }
                });
        spoke = new Button("SPOKE",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        currentUser.setShot(new Shot(ShotKind.SPOKE));
                        m = new Match(currentUser, IA);
                        currentUser.getMatches().add(m);
                        IA.getMatches().add(m);
                        m.doMatch();
                        displayWinner();

                    }
                });
        lizard = new Button("LIZARD",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        currentUser.setShot(new Shot(ShotKind.LIZARD));
                        m = new Match(currentUser, IA);
                        currentUser.getMatches().add(m);
                        IA.getMatches().add(m);
                        m.doMatch();
                        displayWinner();

                    }
                });
        nextPlay = new Button("Play next",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                      playNextShot();
                    }
                });
        bodyContent.addComponents(rock, paper, scissors, spoke, lizard);
        bodyContent.addComponent(youShot);
        bodyContent.addComponent(result);
        bodyContent.addComponent(iaShot);
        bodyContent.addComponent(nextPlay);
        topPanel.setContent(topContent);
        bodyPanel.setContent(bodyContent);

        setDefaultComponentAlignment(Alignment.TOP_CENTER);

        addComponent(topPanel);
        addComponent(bodyPanel);
    }

    private void playNextShot()
    {
        youShot.setVisible(false);
        iaShot.setVisible(false);
        rock.setVisible(true);
        spoke.setVisible(true);
        lizard.setVisible(true);
        scissors.setVisible(true);
        paper.setVisible(true);
        nextPlay.setVisible(false);
        result.setVisible(false);
    }

    private void displayWinner()
    {
        youShot.setVisible(true);
        iaShot.setVisible(true);
        youScore.setValue(currentUser.getScore().toString());
        iaScore.setValue(IA.getScore().toString());
        youShot.setValue(String.format("    Your shot : %s   ", currentUser.getShot().toString()));
        iaShot.setValue(String.format("    IA shot : %s   ", IA.getShot().toString()));
        rock.setVisible(false);
        spoke.setVisible(false);
        lizard.setVisible(false);
        scissors.setVisible(false);
        paper.setVisible(false);
        nextPlay.setVisible(true);
        result.setVisible(true);
        result.setValue(String.format("    %s wins !    ", m.getWinner().getName()));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        UI ui = getUI();
        String username = (String)ui.getSession().getAttribute(Globals.SESSION_USERNAME);
        Lobby lobby = Lobby.getInstance();
        currentUser = lobby.findUserByName(username);
        youLabel.setValue(username);
        playNextShot();
    }

    private ShotKind IAShot() {
        final List<ShotKind> VALUES =
                Collections.unmodifiableList(Arrays.asList(ShotKind.values()));
        final int SIZE = VALUES.size();
        final Random RANDOM = new Random();

        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}