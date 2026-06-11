// ============================================================
//  layout.js  —  Sidebar + shell renderer for authed pages
// ============================================================
import { getUser, removeToken, removeUser, isLoggedIn } from './api.js';
import { icons } from './utils.js';

export function requireAuth() {
  if (!isLoggedIn()) {
    window.location.href = '../pages/login.html';
    return false;
  }
  return true;
}

export function renderShell(activePage) {
  const user = getUser();
  const initials = user?.nome
    ? user.nome.split(' ').map(w => w[0]).slice(0, 2).join('').toUpperCase()
    : 'U';

  const navItems = [
    { id: 'produtos',      label: 'Equipamentos',   icon: icons.tools,    href: 'produtos.html' },
    { id: 'movimentacoes', label: 'Movimentações',  icon: icons.activity, href: 'movimentacoes.html' },
    { id: 'historico',     label: 'Auditoria',      icon: icons.history,  href: 'historico.html' },
  ];

  const navHTML = navItems.map(n => `
    <a href="${n.href}" class="nav-item ${activePage === n.id ? 'active' : ''}">
      ${n.icon}
      <span>${n.label}</span>
    </a>
  `).join('');

  const shell = document.createElement('div');
  shell.className = 'app-shell';
  shell.innerHTML = `
    <aside class="sidebar">
      <div class="sidebar-logo">
        ${icons.wrench}
        <span class="logo-text">Ferr<em>amentei</em></span>
      </div>
      <div class="sidebar-section">Menu</div>
      <nav class="sidebar-nav">${navHTML}</nav>
      <div class="sidebar-footer">
        <div class="user-chip">
          <div class="user-avatar">${initials}</div>
          <div class="user-info">
            <div class="user-name">${user?.nome || 'Usuário'}</div>
            <div class="user-role">${user?.role || 'ALMOXARIFADO'}</div>
          </div>
          <button class="btn-logout" id="btn-logout" title="Sair">${icons.logout}</button>
        </div>
      </div>
    </aside>
    <div class="main" id="main-area"></div>
  `;

  document.body.appendChild(shell);

  document.getElementById('btn-logout').addEventListener('click', () => {
    removeToken();
    removeUser();
    window.location.href = '../pages/login.html';
  });

  return document.getElementById('main-area');
}
