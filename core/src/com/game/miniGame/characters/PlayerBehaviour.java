package com.game.miniGame.characters;
import com.game.miniGame.board.BoardVO;
import com.game.miniGame.board.GameBoard;
import com.game.miniGame.board.GameScreenPos;
import com.game.miniGame.characters.ufos.Ufo;

public class PlayerBehaviour {
    private final GameBoard gameBoard;


    public PlayerBehaviour(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    //Return true if a zombie is hit
    public boolean attackZombie(int aimingPosition, int damage) {
        if(gameBoard.getZombies(aimingPosition).size() == 0) return false;
        gameBoard.getZombies(aimingPosition).get(0).damage(damage);
        return true;
    }

    //Return true if there is UFo in the same pos. Attack it
    public boolean attackUfo(int aimingPosition, int damage) {
        Ufo ufo = gameBoard.getUfo(aimingPosition);
        if(ufo == null) return false;
        ufo.damage(damage);
        return true;
    }
}
