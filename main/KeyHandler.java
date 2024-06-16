package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, keySpacePressed, shotKeyPressed;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (gp.gameState == gp.titleState) {
            titleState(code);
        } else if (gp.gameState == gp.playState) {
            playState(code);
        } else if (gp.gameState == gp.pauseState) {
            pauseState(code);
        } else if (gp.gameState == gp.waveOver) {
            waveState(code);
        } else if (gp.gameState == gp.characterState) {
            characterState(code);
        }
    }

    public void titleState(int code) {
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 1;
            }
        }

        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 1) {
                gp.ui.commandNum = 0;
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0) {
                gp.gameState = gp.playState;
            }
            if (gp.ui.commandNum == 1) {
                System.exit(0);
            }
        }
    }

    public void playState(int code) {
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_C) {
            gp.gameState = gp.characterState;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.pauseState;
        }
        if (code == KeyEvent.VK_SPACE) {
            keySpacePressed = true;
            shotKeyPressed = true;
        }
        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.waveOver;
        }
    }

    public void pauseState(int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }
    }

    public void waveState(int code) {

        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            gp.ui.updgradeNum--;
            if (gp.ui.updgradeNum < 0) {
                gp.ui.updgradeNum = 2;
            }
        }

        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            gp.ui.updgradeNum++;
            if (gp.ui.updgradeNum > 2) {
                gp.ui.updgradeNum = 0;
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.updgradeNum == 0) {
                
                // Speed update
                gp.player.speedLvl++;
                gp.player.speed = gp.player.getSpeed();
                gp.player.setDefaultValues();
                gp.gameState = gp.playState;
            }
            if (gp.ui.updgradeNum == 1) {
                // Health update
                gp.player.healthLvl++;
                gp.player.maxLife = gp.player.getMaxLife();
                gp.player.life = gp.player.maxLife;
                gp.player.setDefaultValues();
                gp.gameState = gp.playState;
            }
            if (gp.ui.updgradeNum == 2) {
                // Damage update
                gp.player.attackLvl++;
                gp.player.attack = gp.player.getAttack();
                gp.player.setDefaultValues();
                gp.gameState = gp.playState;
            }
        }
    }

    public void characterState(int code) {
        if (code == KeyEvent.VK_C) {
            gp.gameState = gp.playState;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_SPACE) {
            keySpacePressed = false;
            shotKeyPressed = false;
        }
    }
}
