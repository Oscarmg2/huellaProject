import React from "react";
import { useAuth0 } from "@auth0/auth0-react";
import Quiz from "./Quiz";


const Navbar = () => {
  const { isAuthenticated, loginWithRedirect, logout } = useAuth0();

  return (
    <div>
      <nav className="navbar">
        <h1>Huella de Carbono</h1>
        {!isAuthenticated ? (
          <button className="nav-button" onClick={() => loginWithRedirect()}>
            Iniciar Sesión
          </button>
        ) : (
          <button
            className="nav-button"
            onClick={() => logout({ returnTo: window.location.origin })}
          >
            Cerrar Sesión
          </button>
        )}
      </nav>

      <div className="content">
        
        {isAuthenticated ? (
          <Quiz/>
        ) : (
          <p> Autentificate por favor! </p>          
        )}
        
      </div>
    </div>
  );
};

export default Navbar;
