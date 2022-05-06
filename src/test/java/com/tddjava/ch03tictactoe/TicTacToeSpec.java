package com.tddjava.ch03tictactoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TicTacToeSpec {
    private TicTacToe ticTacToe;

    @BeforeEach
    public final void before() {
        ticTacToe = new TicTacToe();
    }

    // Nomes dos métodos
    // Deve ser claro quais condições precisam ser atendidas antes do teste,
    // quais ações serão realizadas e o que se espera.
    // Uma dica de Alex Garcia para nomes de métodos
    // Use os nomes do BDD (GWenTh)
    //  - Given: prefixo para descrever (pre)condicoes
    //      * Precondicoes são cumpridas por métodos como before()
    //      * Caso Não precise, Given pode ser omitida
    //  - When : prefixo para descrever ações
    //      Ex.: When X outside board (quando X estiver fora do board)
    //  - Then : prefixo para descrever o que se espera como resultado
    //      Ex.: When ... Then Runtime Exception
    @Test
    // REQUIREMENT 1.1
    public void whenXOutsideBoardThenRuntimeException() throws RuntimeException {
        assertThrows(RuntimeException.class, () -> {
            ticTacToe.play(5, 2);

            /*
                IMPORTANTE
                - Primeira execução do teste: DEVE FALHAR
                  Por que? Não existe método "play"

                - Segunda execução do teste: DEVE FALHAR
                  Por que? RuntimeException não é lançado

                - Segunda execução do teste: DEVE PASSAR
                  Por que? Runtime é lançado
            */
        });
    }

    @Test
    // REQUIREMENT 1.2
    public void whenYOutsideBoardThenRuntimeException() throws RuntimeException {
        assertThrows(RuntimeException.class, () -> {
            ticTacToe.play(2, 5);
        });
    }

    @Test
    // REQUIREMENT 1.3
    public void whenOccupiedThenRuntimeException() throws RuntimeException {
        ticTacToe.play(2, 1);
        assertThrows(RuntimeException.class, () -> {
           ticTacToe.play(2,1);
        });
    }

    @Test
    // REQUIREMENT 2.1
    public void givenFirstTurnWhenNextPlayerThenX() {
        assertEquals('X', ticTacToe.nextPlayer());
    }

    @Test
    // REQUIREMENT 2.2
    public void givenLastTurnWasXWhenNextPlayerThenO() {
        ticTacToe.play(1, 1);
        assertEquals('O', ticTacToe.nextPlayer());
    }

    @Disabled
    @Test
    // REQUIREMENT 2.3
    // Observe que o código passa no teste sem a gente tocar na implementação.
    // É um falso positivo, descarte-o.
    public void givenLastTurnWasOWhenNextPlayerThenX() {
        ticTacToe.play(1, 1);
        ticTacToe.play(1, 2);
        assertEquals('X', ticTacToe.nextPlayer());
    }

    @Test
    public void whenPlayThenNoWinner() {
        String actual = ticTacToe.play(1, 1);
        assertEquals("No winner", actual);
    }

    @Test
    public void whenPlayAndWholeHorizontalLineThenWinner() {
        ticTacToe.play(1, 1); // X
        ticTacToe.play(1, 2); // O
        ticTacToe.play(2, 1); // X
        ticTacToe.play(2, 2); // O
        String actual = ticTacToe.play(3, 1); // X
        assertEquals("X is the winner", actual);
    }

    @Test
    public void whenPlayAndWholeVerticalLineThenWinner() {
        ticTacToe.play(2, 1); // X
        ticTacToe.play(1, 1); // O
        ticTacToe.play(3, 1); // X
        ticTacToe.play(1, 2); // O
        ticTacToe.play(2, 2); // X
        String actual = ticTacToe.play(1, 3); // O
        assertEquals("O is the winner", actual);
    }

    @Test
    public void whenPlayAndTopBottomDiagonalLineThenWinner() {
        ticTacToe.play(1, 1); // X
        ticTacToe.play(1, 2); // O
        ticTacToe.play(2, 2); // X
        ticTacToe.play(1, 3); // O
        String actual = ticTacToe.play(3, 3); // X
        assertEquals("X is the winner", actual);
    }

    @Test
    public void whenPlayAndBottomTopDiagonalLineThenWinner() {
        ticTacToe.play(1, 3); // X
        ticTacToe.play(1, 1); // O
        ticTacToe.play(2, 2); // X
        ticTacToe.play(1, 2); // O
        String actual = ticTacToe.play(3, 1); // X
        assertEquals("X is the winner", actual);
    }

    @Test
    // ÚLTIMO REQUISITO: CONDIÇÃO DE EMPATE
    public void whenAllBoxesAreFilledThenDraw() {
        ticTacToe.play(1, 1);
        ticTacToe.play(1, 2);
        ticTacToe.play(1, 3);
        ticTacToe.play(2, 1);
        ticTacToe.play(2, 3);
        ticTacToe.play(2, 2);
        ticTacToe.play(3, 1);
        ticTacToe.play(3, 3);
        String actual = ticTacToe.play(3, 2);
        assertEquals("The result is draw", actual);
    }
}

