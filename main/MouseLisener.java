package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseLisener implements MouseListener, MouseMotionListener {

    // Spremenljivke za sledenje stanju miške
    public boolean leftButtonPressed = false;
    public int mouseX, mouseY;

    // Implementacija metod iz MouseListener vmesnika
    @Override
    public void mouseClicked(MouseEvent e) {
        // Koda za obdelavo klika miške
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Koda za obdelavo pritiska tipke na miški
        if (e.getButton() == MouseEvent.BUTTON1) { // BUTTON3 je desna miškina tipka
            leftButtonPressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Koda za obdelavo sprostitve tipke na miški
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftButtonPressed = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Koda za obdelavo vstopa miške v komponento
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Koda za obdelavo izstopa miške iz komponente
    }

    // Implementacija metod iz MouseMotionListener vmesnika
    @Override
    public void mouseDragged(MouseEvent e) {
        // Koda za obdelavo premikanja miške s pritisnjeno tipko
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // Posodobitev pozicije miške
        mouseX = e.getX();
        mouseY = e.getY();
    }
}
