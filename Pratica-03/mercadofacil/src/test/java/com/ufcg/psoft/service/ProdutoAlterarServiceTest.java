public class ProdutoAlterarServiceTest {
    @BeforeEach
    void setup() {
        Mockito.when(produtoRepository.find(10L))
                .thenReturn(Produto.builder()
                        .id(10L)
                        .codigoBarra("7899137500104")
                        .nome("Produto Dez")
                        .fabricante("Empresa Dez")
                        .preco(450.00)
                        .build());
        produto = produtoRepository.find(10L);
        Mockito.when(produtoRepository.update(produto))
                .thenReturn(Produto.builder()
                        .id(10L)
                        .codigoBarra("7899137500104")
                        .nome("Nome Produto Alterado")
                        .fabricante("Nome Fabricante Alterado")
                        .preco(500.00)
                        .build());
    }

    @SpringBootTest
    @DisplayName("Testes para a alteração do Produto")
    public class ProdutoAlterarServiceTest {
        @Autowired
        ProdutoAlterarService driver;
        @MockBean
        ProdutoRepository<Produto, Long> produtoRepository;
        Produto produto;
    }

}
