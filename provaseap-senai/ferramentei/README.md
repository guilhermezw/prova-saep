# Ferramentei — Frontend

Interface web para controle e inventário de ferramentas e equipamentos.

## Estrutura de Arquivos

```
ferramentei/
├── index.html              ← Dashboard principal
├── css/
│   └── style.css           ← Design system completo
├── js/
│   ├── api.js              ← Cliente HTTP da API REST
│   ├── utils.js            ← Helpers: toast, badges, paginação, ícones
│   └── layout.js           ← Shell + sidebar das páginas autenticadas
└── pages/
    ├── login.html          ← Tela de login
    ├── cadastro.html       ← Tela de cadastro de usuário
    ├── produtos.html       ← CRUD de equipamentos
    ├── movimentacoes.html  ← Registro e histórico de movimentações
    └── historico.html      ← Auditoria do sistema (somente leitura)
```

## Como usar

### 1. Configure a URL da API

Edite o arquivo `js/api.js` e altere a constante `BASE_URL`:

```js
const BASE_URL = 'http://localhost:8080';  // ← ajuste para seu servidor
```

### 2. Abra no navegador

Não é necessário servidor web local para as páginas HTML — basta abrir via browser. Porém, por usar ES Modules (`type="module"`), você precisa servir os arquivos via HTTP (não `file://`).

Opção rápida com Python:
```bash
cd ferramentei
python3 -m http.server 3000
```
Depois acesse: `http://localhost:3000`

Ou com Node.js / npx:
```bash
npx serve ferramentei
```

### 3. Faça login ou cadastre uma conta

Acesse `pages/login.html` para entrar.  
Caso seja o primeiro acesso, crie uma conta em `pages/cadastro.html`.

## Funcionalidades

| Página         | Funcionalidade                                      |
|----------------|-----------------------------------------------------|
| Dashboard      | Resumo de equipamentos e últimas movimentações      |
| Equipamentos   | Listagem, criação, edição, exclusão, detalhes       |
| Movimentações  | Registro de entradas/saídas/manutenções             |
| Auditoria      | Histórico de criações, edições e exclusões          |

## Autenticação

O token JWT é armazenado em `localStorage` com a chave `ferr_token`.  
Todas as requisições enviam o header `Authorization: Bearer <token>` automaticamente.

## Requisitos da API

A API deve estar rodando em `http://localhost:8080` (ou outro endereço configurado no `api.js`), expondo os endpoints definidos no `api-docs.json` (OpenAPI 3.1.0).

Endpoints utilizados:
- `POST /auth/login`
- `POST /auth/cadastro`
- `GET/POST /produtos`
- `GET/PUT/DELETE /produtos/{id}`
- `GET/POST /movimentacoes`
- `GET /historico`
