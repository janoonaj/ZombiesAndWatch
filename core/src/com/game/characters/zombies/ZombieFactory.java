package com.game.characters.zombies;

import com.badlogic.gdx.graphics.Texture;
import com.game.AssetsFactory;
import com.game.Metronome;
import com.game.board.GameBoard;
import com.game.board.PositionOnBoardVO;

//TODO: object pooling
public class ZombieFactory {
    private final GameBoard board;
    private final Metronome metronome = new Metronome();

    public ZombieFactory(GameBoard board) {
        this.board = board;
    }

    public Metronome getMetronome() {
        return metronome;
    }

    public Zombie createZombieRight() {
        return createZombie(board.getRightest(), Movement.LEFT, AssetsFactory.instance().getZombieLeft());
    }

    public Zombie createZombieLeft() {
        return createZombie(board.getLeftest(), Movement.RIGHT, AssetsFactory.instance().getZombieRight());
    }

    public Zombie createZombie(PositionOnBoardVO pos, Movement movement, Texture texture) {
        Zombie zombie = new Zombie(texture, pos.getBoardPos(), movement, board);
        zombie.setPosition(pos.getScreenCoords().x, pos.getScreenCoords().y);
        board.addZombie(pos.getBoardPos(), zombie);
        metronome.subscribe(zombie);
        return zombie;
    }

    public void deleteZombie(Zombie zombie) {
        metronome.unsubscribe(zombie);
    }
}
