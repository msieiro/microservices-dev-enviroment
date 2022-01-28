import AccountRepository from '@/repositories/AccountRepository';
import store from './store';

const user = store.state.user;

function login(credentials) {
  return AccountRepository.login(credentials).then((response) => {
    _saveToken(response.token);
    _authenticate();
  });
}

function logout() {
  return new Promise((res) => {
    _removeToken();
    user.id = null;
    user.displayName = '';
    user.name = '';
    user.authorities = [];
    user.logged = false;
    user.token = null;
    res(true);
  });
}

function getUser() {
  return user;
}

function getToken() {
  return localStorage.getItem('token');
}

function _saveUserData(data) {
  user.id = data.id;
  user.displayName = data.displayName;
  user.name = data.name;
  user.email = data.email;
  user.logged = true;
  user.authorities.push(data.role);
  user.token = getToken();
}

function _saveToken(token) {
  user.token = token;
  return localStorage.setItem('token', token);
}

function _removeToken() {
  localStorage.removeItem('token');
}

function _authenticate() {
  return AccountRepository.getUser().then((response) => {
    _saveUserData(response);
    return user;
  });
}

function isAuthenticationChecked() {
  return new Promise((res) => {
    if (getToken()) {
      _authenticate()
        .catch(() => logout())
        .finally(() => res(true));
    } else {
      res(true);
    }
  });
}

export default {
  getUser,
  login,
  logout,
  getToken,
  isAuthenticationChecked,
};
