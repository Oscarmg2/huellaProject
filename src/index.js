import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { Auth0Provider} from '@auth0/auth0-react';
import AuthHandler from './components/AuthHandler';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <Auth0Provider
    domain="dev-pnjf3u5au7pkeqgg.us.auth0.com"            // Reemplaza por tu dominio de Auth0
    clientId="6oKjuofOxvIhZzbLOSweMZ3xSZ4kX9Fp"         // Reemplaza por tu clientId
    redirectUri={ window.location.origin }
  >
    <AuthHandler />
    <App />
  </Auth0Provider>
  
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
