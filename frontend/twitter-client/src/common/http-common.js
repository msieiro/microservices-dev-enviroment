import auth from '@/common/auth';
import axios from 'axios';
import { cacheAdapterEnhancer } from 'axios-extensions';
import properties from '../properties';

axios.defaults.paramsSerializer = function (params) {
  let result = '';
  for (const param in params) {
    if (params[param] == null || params[param] == '') {
      continue;
    }
    if (result != '') {
      result += '&';
    }
    result = result + param + '=' + params[param];
  }
  return result;
};

const HTTP = axios.create({
  baseURL: properties.SERVER_URL,
  // allowing use of cache with 'cache' param (not enabled by default)
  headers: { 'Cache-Control': 'no-cache' },
  adapter: cacheAdapterEnhancer(axios.defaults.adapter, {
    enabledByDefault: false,
  }),
});

const onUnauthorized = () => {
  /* Vue.notify({
    title: 'authorization.access_denied.title',
    text: 'authorization.access_denied.body',
    type: 'error'
  }); */
  auth.logout();
};
/* const onFailure = (title, message) => {
  Vue.notify({
    title: title,
    text: message,
    type: 'error',
    ignoreDuplicates: true
  });
}; */
const onResponseSuccess = (response) => response;

/* const errorTitles = {
  400: 'errors.badRequestError',
  401: 'errors.authorization.auth_required.title',
  500: 'errors.internalServerError'
}; */

// si el servidor nos devuelve un 401 o 403,
// estamos intentando acceder a un recurso sin
// los permisos correctos
const onResponseFailure = (err) => {
  const status = err.response.status;
  if (status === 401 || status === 403) {
    onUnauthorized();
  } else if (status !== 404) {
    /*const headerError = err.response.headers['x-app-error'];
    const errorParams = err.response.headers["x-app-params"] || "{}";
    const title = errorTitles[status] || 'errors.generic';
    const message = headerError ? headerError : 'defaultServerErrorMessage';
     onFailure(title, message); */
  }
  return Promise.reject(err);
};

// en cada request hay que añadir el token de autenticación
// si es que lo tenemos
const onRequest = (config) => {
  const user = auth.getUser();
  if (user.token) {
    config.headers.Authorization = `Bearer ${user.token}`;
  }
  return config;
};

HTTP.interceptors.response.use(onResponseSuccess, onResponseFailure);
HTTP.interceptors.request.use(onRequest);

export { HTTP };
