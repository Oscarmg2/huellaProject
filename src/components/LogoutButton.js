// import React from "react";
// import { useAuth0 } from "@auth0/auth0-react";
// import { Button } from "reactstrap";
// //Componente button
// export const LogoutButton = () => {
//     const { logout } = useAuth0();
//     return <Button onClick={()=>logout({returnTo: window.location.origin})} color="primary" className="logout-button">Cerrar Sesión</Button> 
// };

import React from "react";
import { useAuth0 } from "@auth0/auth0-react";

const Logout = () => {
  const { logout } = useAuth0();

  return (
    <div>
      <button
        className="nav-button"
        onClick={() => logout({ returnTo: window.location.origin })}
      >
        Cerrar Sesión
      </button>
    </div>
  );
};

export default Logout;
