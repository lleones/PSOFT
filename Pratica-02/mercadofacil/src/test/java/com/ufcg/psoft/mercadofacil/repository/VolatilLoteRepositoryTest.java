package com.ufcg.psoft.mercadofacil.repository;

import com.ufcg.psoft.mercadofacil.model.Lote;
import com.ufcg.psoft.mercadofacil.model.Produto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VolatilLoteRepositoryTest {

    @Autowired
    VolatilLoteRepository driver;

    Lote lote;
    Lote resultado;
    Produto produto;

    @BeforeEach
    void setup() {
        produto = Produto.builder()
                .id(1L)
                .nome("Produto Base")
                .codigoBarra("123456789")
                .fabricante("Fabricante Base")
                .preco(125.36)
                .build();
        lote = Lote.builder()
                .id(1L)
                .numeroDeItens(100)
                .produto(produto)
                .build();
    }

    @AfterEach
    void tearDown() {
        produto = null;
        driver.deleteAll();
    }

    @Test
    @DisplayName("Adicionar o primeiro Lote no repositorio de dados")
    void salvarPrimeiroLote() {
        resultado = driver.save(lote);

        assertEquals(driver.findAll().size(), 1);
        assertEquals(resultado.getId().longValue(), lote.getId().longValue());
        assertEquals(resultado.getProduto(), produto);
    }

    @Test
    @DisplayName("Adicionar o segundo Lote (ou posterior) no repositorio de dados")
    void salvarSegundoLoteOuPosterior() {
        Produto produtoExtra = Produto.builder()
                .id(2L)
                .nome("Produto Extra")
                .codigoBarra("987654321")
                .fabricante("Fabricante Extra")
                .preco(125.36)
                .build();
        Lote loteExtra = Lote.builder()
                .id(2L)
                .numeroDeItens(100)
                .produto(produtoExtra)
                .build();
        driver.save(lote);

        resultado = driver.save(loteExtra);

        assertEquals(driver.findAll().size(), 2);
        assertEquals(resultado.getId().longValue(), loteExtra.getId().longValue());
        assertEquals(resultado.getProduto(), produtoExtra);

    }

    @Test
    @DisplayName("Encontra lote armazenado no repositório")
    void encontraLoteEmRepository() {
        driver.save(lote);
        assertEquals(lote.getId(), driver.find(lote.getId()));
    }

    @Test
    @DisplayName("Tenta encontrar lote não cadastrado")
    void entrontraLoteNaoCadastrado() {
        driver.save(lote);
        assertEquals(null, driver.find((long) 2));
    }

    @Test
    @DisplayName("Encontra todos os lotes cadastrados")
    void encontraTodosLotesCadastrados() {
        driver.save(lote);
        assertEquals(1, driver.findAll().size());
    }

    @Test
    @DisplayName("Encontra todos os lotes em vazio")
    void encontraTodosLotesEmVazio() {
        assertEquals(0, driver.findAll().size());
    }

    @Test
    @DisplayName("Atualiza lote cadastrado")
    void atualizaLoteCadastrado() {
        driver.save(lote);
        lote.setNumeroDeItens(200);
        driver.update(lote);
        assertEquals(200, driver.find(lote.getId()).getNumeroDeItens());
    }

    @Test
    @DisplayName("Tenta atualizar lote não cadastrado")
    void atualizaLoteNaoCadastrado() {
        driver.save(lote);
        lote.setNumeroDeItens(200);
        lote.setId((long) 2);
        driver.update(lote);
        assertEquals(100, driver.find(lote.getId()).getNumeroDeItens());
    }

    @Test
    @DisplayName("Deleta lote cadastrado")
    void deletaLoteCadastrado() {
        driver.save(lote);
        driver.delete(lote);
        assertEquals(0, driver.findAll().size());
    }

    @Test
    @DisplayName("Tenta deletar lote não cadastrado")
    void deletaLoteNaoCadastrado() {
        driver.save(lote);
        lote.setId((long) 2);
        driver.delete(lote);
        assertEquals(1, driver.findAll().size());
    }

    @Test
    @DisplayName("Deleta todos os lotes cadastrados")
    void deletaTodosLotesCadastrados() {
        driver.save(lote);
        driver.deleteAll();
        assertEquals(0, driver.findAll().size());
    }

    @Test
    @DisplayName("Deleta todos os lotes em vazio")
    void deletaTodosLotesEmVazio() {
        driver.deleteAll();
        assertEquals(0, driver.findAll().size());
    }

}
