// ============================================================
//  api.js  —  Ferramentei API Client
//  Base URL: http://localhost:8080
// ============================================================

const BASE_URL = 'http://localhost:8080';

// ── Token helpers ────────────────────────────────────────────
export function getToken() { return localStorage.getItem('ferr_token'); }
export function setToken(t) { localStorage.setItem('ferr_token', t); }
export function removeToken() { localStorage.removeItem('ferr_token'); }

export function getUser() {
  try { return JSON.parse(localStorage.getItem('ferr_user')); } catch { return null; }
}
export function setUser(u) { localStorage.setItem('ferr_user', JSON.stringify(u)); }
export function removeUser() { localStorage.removeItem('ferr_user'); }

export function isLoggedIn() { return !!getToken(); }

// ── Core fetch ───────────────────────────────────────────────
async function req(method, path, body = null, params = null) {
  let url = BASE_URL + path;
  if (params) {
    const q = new URLSearchParams();
    Object.entries(params).forEach(([k, v]) => {
      if (v !== null && v !== undefined && v !== '') q.append(k, v);
    });
    const qs = q.toString();
    if (qs) url += '?' + qs;
  }

  const headers = { 'Content-Type': 'application/json' };
  const token = getToken();
  if (token) headers['Authorization'] = 'Bearer ' + token;

  const opts = { method, headers };
  if (body) opts.body = JSON.stringify(body);

  const res = await fetch(url, opts);

  if (res.status === 204 || res.status === 200 && res.headers.get('content-length') === '0') {
    return { ok: true };
  }

  const data = await res.json().catch(() => ({}));

  if (!res.ok) {
    const msg = data.mensagem || data.message || `Erro ${res.status}`;
    const err = new Error(msg);
    err.status = res.status;
    err.data = data;
    throw err;
  }

  return data;
}

// ── Auth ─────────────────────────────────────────────────────
export const Auth = {
  login: (email, senha) => req('POST', '/auth/login', { email, senha }),
  cadastrar: (nome, email, senha) => req('POST', '/auth/cadastro', { nome, email, senha }),
};

// ── Produtos ─────────────────────────────────────────────────
export const Produtos = {
  listar: (page = 0, size = 10, sort = 'nome,ASC') =>
    req('GET', '/produtos', null, { page, size, sort }),

  buscarPorId: (id) => req('GET', `/produtos/${id}`),

  criar: (dto) => req('POST', '/produtos', dto),

  atualizar: (id, dto) => req('PUT', `/produtos/${id}`, dto),

  deletar: (id) => req('DELETE', `/produtos/${id}`),
};

// ── Movimentações ─────────────────────────────────────────────
export const Movimentacoes = {
  listar: (page = 0, size = 10) =>
    req('GET', '/movimentacoes', null, { page, size, sort: 'dataMovimentacao,DESC' }),

  registrar: (dto) => req('POST', '/movimentacoes', dto),
};

// ── Histórico de Auditoria ────────────────────────────────────
export const Historico = {
  listar: (page = 0, size = 10) =>
    req('GET', '/historico', null, { page, size, sort: 'dataAcao,DESC' }),
};
