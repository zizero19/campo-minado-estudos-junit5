package br.com.cod3r.cm.modelo;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.cod3r.cm.excecao.ExplosaoException;

public class CampoTeste {

    private Campo campo;

    @BeforeEach // Inicializa este antes de cada teste
    void iniciarCampo() {
        campo = new Campo(3, 3);
    }

    @Test
    void testeVizinhoDistanciaEsquerda() {
        Campo vizinhoEsquerda = new Campo(3, 2);
        boolean resultadoEsquerda = campo.adicionarVizinho(vizinhoEsquerda);
        assertTrue(resultadoEsquerda);
    }

    @Test
    void testeVizinhoDistanciaDireita() {
        Campo vizinhoDireita = new Campo(3, 4);
        boolean resultadoDireita = campo.adicionarVizinho(vizinhoDireita);
        assertTrue(resultadoDireita);
    }

    @Test
    void testeVizinhoDistanciaEmCima() {
        Campo vizinhoEmCima = new Campo(2, 3);
        boolean resultadoEmCima = campo.adicionarVizinho(vizinhoEmCima);
        assertTrue(resultadoEmCima);
    }

    @Test
    void testeVizinhoDistanciaEmBaixo() {
        Campo vizinhoEmBaixo = new Campo(4, 3);
        boolean resultadoEmBaixo = campo.adicionarVizinho(vizinhoEmBaixo);
        assertTrue(resultadoEmBaixo);
    }

    @Test
    void testeVizinhoDistanciaDiagonal() {
        Campo vizinhoDiagonal = new Campo(2, 2);
        boolean resultadoDiagonal = campo.adicionarVizinho(vizinhoDiagonal);
        assertTrue(resultadoDiagonal);
    }

    @Test
    void testeNaoVizinho() {
        Campo vizinho = new Campo(1, 1);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertFalse(resultado);
    }

    @Test
    void testeValorPadraoAtributoMarcado() {
        assertFalse(campo.isMarcado());
    }

    @Test
    void testeAlternarMarcacao() {
        campo.alternarMarcacao();
        assertTrue(campo.isMarcado());
    }

    @Test
    void testeAlternarMarcacaoDuasChamadas() {
        campo.alternarMarcacao();
        campo.alternarMarcacao();
        assertFalse(campo.isMarcado());
    }

    @Test
    void testeAbrirNaoMinadoNaoMarcado() {
        assertTrue(campo.abrir());
    }

    @Test
    void testeAbrirNaoMinadoMarcado() {
        campo.alternarMarcacao();
        assertFalse(campo.abrir());
    }

    @Test
    void testeAbrirMinadoMarcado() {
        campo.alternarMarcacao();
        campo.minar();
        assertFalse(campo.abrir());
    }

    @Test
    void testeAbrirMinadoNaoMarcado() {
        campo.minar();
        assertThrows(ExplosaoException.class, () -> {
            campo.abrir();
        });
    }

    @Test
    void testeAbrirComVizinhos1() {

        Campo campo11 = new Campo(1, 1);
        Campo campo22 = new Campo(2, 2);

        campo22.adicionarVizinho(campo11);

        campo.adicionarVizinho(campo22);
        campo.abrir();

        assertTrue(campo22.isAberto() && campo11.isAberto());
    }

    @Test
    void testeAbrirComVizinhos2() {

        Campo campo11 = new Campo(1, 1);
        Campo campo12 = new Campo(1, 2);
        campo12.minar();

        Campo campo22 = new Campo(2, 2);

        campo22.adicionarVizinho(campo11);
        campo22.adicionarVizinho(campo12);

        campo.adicionarVizinho(campo22);
        campo.abrir();

        assertTrue(campo22.isAberto() && campo11.isFechado());
    }
}
